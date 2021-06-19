package com.mnhyim.moviecatalog.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mnhyim.core.domain.model.Movie
import com.mnhyim.moviecatalog.R
import com.mnhyim.moviecatalog.databinding.ActivityDetailBinding
import com.mnhyim.moviecatalog.ui.viewmodels.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val tag: String = this::class.java.simpleName
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_ITEM) as Movie
        Log.d(tag, "${movie.title} - ${movie.isFavorite}")

        populateMovieDetail(movie)
    }

    private fun populateMovieDetail(movie: Movie) {
        title = movie.title

        with(binding) {
            tvDetailName.text = movie.title
            tvDetailRelease.text = movie.releaseDate
            tvDetailLanguage.text = movie.originalLanguage.uppercase()
            tvDetailScore.text = movie.voteAverage.toString()
            tvDetailDesc.text = movie.overview
            Glide.with(baseContext)
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .into(imgDetailPoster)

            Glide.with(baseContext)
                .load("https://image.tmdb.org/t/p/original/${movie.backdropPath}")
                .apply(RequestOptions().override(350, 350))
                .into(imgBg)

            setFavoriteButtonIcon(movie.isFavorite)
            progressBar.visibility = View.GONE

            fabFavorite.setOnClickListener {
                val status = !movie.isFavorite
                detailViewModel.setFavorite(movie, status)
                if (status) {
                    setFavoriteButtonIcon(status)
                    Toast.makeText(
                        this@DetailActivity,
                        "Movie added succesfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    setFavoriteButtonIcon(status)
                    Toast.makeText(
                        this@DetailActivity,
                        "Movie removed succesfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun setFavoriteButtonIcon(status: Boolean) {
        if (status) {
            binding.fabFavorite.setImageResource(R.drawable.ic_remove)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_add)
        }
    }
}