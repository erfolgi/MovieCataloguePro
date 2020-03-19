package com.erfolgi.moviecataloguepro.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import io.mockk.every
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TvShowViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvShowViewModel? = null
    private val movieCatalogueRepository = mockkClass(MovieCatalogueRepository::class)

    @Mock
    private lateinit var pagedList: PagedList<TVEntity>
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVEntity>>>


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }
    @Test
    fun getTvShow() {
        val dummyTvs = Resource.success(pagedList)
        Mockito.`when`(dummyTvs.data?.size).thenReturn(5)

        val tvs = MutableLiveData<Resource<PagedList<TVEntity>>>()
        tvs.value =dummyTvs

        every {movieCatalogueRepository.getTVs()}.returns(tvs)
        val tvEntities = viewModel?.getTvList()?.value?.data
        verify{movieCatalogueRepository.getTVs()}
        assertNotNull(tvEntities)
        assertEquals(5,tvEntities?.size)

        viewModel?.getTvList()?.observeForever(observer)
        verify(observer).onChanged(dummyTvs)

    }
}