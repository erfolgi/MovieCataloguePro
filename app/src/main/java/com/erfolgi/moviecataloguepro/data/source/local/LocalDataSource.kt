package com.erfolgi.moviecataloguepro.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.local.room.AppDao

open class LocalDataSource  private constructor(private val mAcademyDao: AppDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun getListMovie(): DataSource.Factory<Int, MovieEntity> {
        return mAcademyDao.getMovieList()
    }

    fun getListTv(): DataSource.Factory<Int, TVEntity> {
        return mAcademyDao.getTvList()
    }

    fun getFavMovie(): DataSource.Factory<Int, MovieEntity> {
        return mAcademyDao.getMovieFavList()
    }
    fun getFavTv(): DataSource.Factory<Int, TVEntity> {
        return mAcademyDao.getTvFavList()
    }

    fun getFavMoviebyId(id:String): LiveData<MovieEntity> {
        return mAcademyDao.getMoviebyId(id)
    }
    fun getFavTvbyId(id:String): LiveData<TVEntity> {
        return mAcademyDao.getTvbyId(id)
    }

    fun insertFavMovie(movies: List<MovieEntity>) = mAcademyDao.insertMovie(movies)

    fun insertFavTv(tvs: List<TVEntity>) = mAcademyDao.insertTv(tvs)

    fun deleteFavMovie(movie:MovieEntity) = mAcademyDao.deleteMovie(movie)

    fun deleteFavTv(tv:TVEntity) = mAcademyDao.deleteTv(tv)

    fun setFavMovie(movie: MovieEntity, newState: Int) {
        movie.favorite = newState
        mAcademyDao.updateMovie(movie)
    }
    fun setFavTv(tv: TVEntity, newState: Int) {
        tv.favorite = newState
        mAcademyDao.updateTv(tv)
    }

}