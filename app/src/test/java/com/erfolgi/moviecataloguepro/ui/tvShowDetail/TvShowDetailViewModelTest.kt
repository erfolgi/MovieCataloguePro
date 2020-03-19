package com.erfolgi.moviecataloguepro.ui.tvShowDetail


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource
import com.erfolgi.moviecataloguepro.util.DataDummy
import com.nhaarman.mockitokotlin2.verify
import io.mockk.every
import io.mockk.mockkClass
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowDetailViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private var viewModel: TvShowDetailViewModel? = null
    private val showResponse = DataDummy.generateDummyTvShows()
    private val showId= showResponse[9].id.toString()

    private val movieCatalogueRepository = mockkClass(MovieCatalogueRepository::class)

    @Mock
    private lateinit var observer: Observer<Resource<TVEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(movieCatalogueRepository)
        viewModel!!.setTvId(showId)
    }
    @Test
    fun getTvShow() {
        val dummyTv = Resource.success(DataDummy.generateDummyTvShowDetail())
        val tv = MutableLiveData<Resource<TVEntity>>()
        tv.value=dummyTv
        every{movieCatalogueRepository.getFavDetailTv(showId)}.returns(tv)
        viewModel?.tvLive?.observeForever(observer)
        verify(observer).onChanged(dummyTv)

    }
}