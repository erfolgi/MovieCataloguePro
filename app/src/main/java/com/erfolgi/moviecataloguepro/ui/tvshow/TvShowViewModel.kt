package com.erfolgi.moviecataloguepro.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource

class TvShowViewModel(var repository: MovieCatalogueRepository) : ViewModel() {

    fun getTvList():LiveData<Resource<PagedList<TVEntity>>> =
        repository.getTVs()
}