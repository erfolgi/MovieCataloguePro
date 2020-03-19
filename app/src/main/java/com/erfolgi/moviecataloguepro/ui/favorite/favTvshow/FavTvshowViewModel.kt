package com.erfolgi.moviecataloguepro.ui.favorite.favTvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity

class FavTvshowViewModel (var repository: MovieCatalogueRepository) : ViewModel()  {
    fun getFavTvs(): LiveData<PagedList<TVEntity>> = repository.getFavTvs()
}