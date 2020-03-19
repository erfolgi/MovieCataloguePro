package com.erfolgi.moviecataloguepro.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource

class MovieViewModel(var repository: MovieCatalogueRepository) : ViewModel() {

    fun getMovieList():LiveData<Resource<PagedList<MovieEntity>>> =
        repository.getMovies()
}