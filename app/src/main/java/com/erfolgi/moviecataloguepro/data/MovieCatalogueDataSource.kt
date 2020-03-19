package com.erfolgi.moviecataloguepro.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource

interface MovieCatalogueDataSource {
//    fun getMovie(): LiveData<ArrayList<MovieModel>>

//    fun getTv(): LiveData<ArrayList<TVModel>>

//    fun getDetailMovie(movieId:String): LiveData<MovieModel>?
//
//    fun getDetailTv(tvId:String): LiveData<TVModel>

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTVs(): LiveData<Resource<PagedList<TVEntity>>>

    fun getFavDetailMovie(movieId:String): LiveData<Resource<MovieEntity>>?

    fun getFavDetailTv(tvId:String): LiveData<Resource<TVEntity>>?

    fun setFavMovie(movie: MovieEntity, state: Int)

    fun setFavTv(tv: TVEntity, state: Int)

    fun getFavMovies ():LiveData<PagedList<MovieEntity>>

    fun getFavTvs ():LiveData<PagedList<TVEntity>>
}