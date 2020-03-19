package com.erfolgi.moviecataloguepro.ui.favorite.favMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity

class FavMovieViewModel(var repository: MovieCatalogueRepository) : ViewModel() {
    fun getFavMovies(): LiveData<PagedList<MovieEntity>> = repository.getFavMovies()
}