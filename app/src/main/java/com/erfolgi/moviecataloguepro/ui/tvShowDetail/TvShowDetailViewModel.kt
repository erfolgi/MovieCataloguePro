package com.erfolgi.moviecataloguepro.ui.tvShowDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource

class TvShowDetailViewModel( var repository: MovieCatalogueRepository)  : ViewModel(){
    val tvId = MutableLiveData<String>()

    fun setTvId(tvId: String) {
        this.tvId.value = tvId
    }

    var tvLive: LiveData<Resource<TVEntity>> =  Transformations.switchMap(tvId) { mTvId ->
        repository.getFavDetailTv(mTvId)
    }

    fun setFavorite() {
        val tvResource = tvLive.value
        if (tvResource != null) {
            val tvData = tvResource.data
            if (tvData != null) {
                if(tvData.favorite==1){
                    repository.setFavTv(tvData, 0)
                }else if(tvData.favorite==0){
                    repository.setFavTv(tvData, 1)
                }

            }
        }
    }
}