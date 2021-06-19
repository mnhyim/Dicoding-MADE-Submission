package com.mnhyim.moviecatalog.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mnhyim.core.domain.model.Movie
import com.mnhyim.core.ui.MoviesAdapter
import com.mnhyim.moviecatalog.databinding.ActivityFavoriteBinding
import com.mnhyim.moviecatalog.favorite.di.favoriteModule
import com.mnhyim.moviecatalog.ui.activities.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadKoinModules(favoriteModule)

        val moviesAdapter = MoviesAdapter()

        favoriteViewModel.movies.observe(this) {
            Log.d(TAG, "$it")
            if (it != null) {
                moviesAdapter.setMovies(it)
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }

        moviesAdapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ITEM, movie)
                startActivity(intent)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}