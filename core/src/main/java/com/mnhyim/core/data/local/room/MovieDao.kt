package com.mnhyim.core.data.local.room

import androidx.room.*
import com.mnhyim.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: List<MovieEntity>)

    @Delete
    fun delete(movie: MovieEntity)

    @Update
    fun update(movie: MovieEntity)

    @Query("SELECT * FROM movie ORDER BY title ASC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getAllFavoriteMovies(): Flow<List<MovieEntity>>
}