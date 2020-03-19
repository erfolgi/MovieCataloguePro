package com.erfolgi.moviecataloguepro.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource

class MovieDetailViewModel(var repository: MovieCatalogueRepository) : ViewModel(){
    val movieId = MutableLiveData<String>()

    fun setMovieId(movieId: String) {
        this.movieId.value = movieId
    }

    var movieLive: LiveData<Resource<MovieEntity>> =  Transformations.switchMap(movieId) { mMovieId ->
        repository.getFavDetailMovie(mMovieId)
    }

    fun setFavorite() {
        val movieResource = movieLive.value
        if (movieResource != null) {
            val movieData = movieResource.data
            if (movieData != null) {
                if(movieData.favorite==1){
                    repository.setFavMovie(movieData, 0)
                }else if(movieData.favorite==0){
                    repository.setFavMovie(movieData, 1)
                }

            }
        }
    }

//    fun getMovieLive(movieId:String): LiveData<MovieModel>? {
//        return repository.getDetailMovie(movieId)
//    }
}