package com.mnhyim.moviecatalog.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnhyim.core.domain.model.Movie
import com.mnhyim.core.ui.MoviesAdapter
import com.mnhyim.core.utils.Resource
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.databinding.ActivityCatalogBinding
import com.mnhyim.moviecatalog.ui.viewmodels.CatalogViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName
    private val catalogViewModel: CatalogViewModel by viewModel()
    private lateinit var binding: ActivityCatalogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.elevation = 0f

        val moviesAdapter = MoviesAdapter()

        catalogViewModel.movies.observe(this) {
            Log.d(TAG, "$it")
            if (it != null) {
                when (it) {
                    is Resource.Success -> {
                        Log.d(TAG, "Success: ${it.data}")
                        setLoading(false)
                        moviesAdapter.setMovies(it.data)
                    }
                    is Resource.Loading -> {
                        Log.d(TAG, "Loading")
                        setLoading(true)
                    }
                    is Resource.Error -> {
                        Log.d(TAG, "Error: ${it.message}")
                        setLoading(false)
                        Toast.makeText(this, "Error! : ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        moviesAdapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(this@CatalogActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ITEM, movie)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_toFavorite -> {
                val uri = Uri.parse("moviecatalog://favorite")

                val mIntent = Intent(
                    Intent.ACTION_VIEW,
                    uri
                )

                startActivity(mIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setLoading(status: Boolean) {
        if (status) {
            binding.progressBar.visibility = View.VISIBLE

        } else {
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}