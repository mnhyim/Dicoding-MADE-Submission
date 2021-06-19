package com.mnhyim.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mnhyim.core.databinding.ListItemCatalogBinding
import com.mnhyim.core.domain.model.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private val TAG: String = this::class.java.simpleName
    private val listMovies = ArrayList<Movie>()
    private var onItemClickCallback: OnItemClickCallback? = null

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MoviesViewHolder(private val binding: ListItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Log.d(TAG, "favoriteStatus: ${movie.title} = ${movie.isFavorite}")

                tvListName.text = movie.title
                tvListDate.text = movie.releaseDate
                tvListDesc.text = movie.overview

                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    .into(imgListItem)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }

    fun setMovies(movie: List<Movie>?) {
        if (movie == null) {
            this.listMovies.clear()
            notifyDataSetChanged()

        } else {
            this.listMovies.clear()
            this.listMovies.addAll(movie)
            notifyDataSetChanged()
        }
        Log.d(TAG, "setMovies: ${this.listMovies}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            ListItemCatalogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}