package com.erfolgi.moviecataloguepro.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity


@Dao
interface AppDao {

    @Query("SELECT * FROM movieEntities")
    fun getMovieList(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvEntities")
    fun getTvList(): DataSource.Factory<Int, TVEntity>

    @Query("SELECT * FROM movieEntities where favorite = 1")
    fun getMovieFavList(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvEntities where favorite = 1")
    fun getTvFavList(): DataSource.Factory<Int, TVEntity>


    @Query("SELECT * FROM movieEntities WHERE movieId = :id")
    fun getMoviebyId(id:String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvEntities WHERE tvId = :id")
    fun getTvbyId(id:String): LiveData<TVEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TVEntity>)


    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTv(tv: TVEntity)


    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteTv(tv: TVEntity)

}