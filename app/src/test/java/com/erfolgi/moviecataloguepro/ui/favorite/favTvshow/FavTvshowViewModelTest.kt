package com.erfolgi.moviecataloguepro.ui.favorite.favTvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import io.mockk.mockkClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavTvshowViewModelTest {
    private lateinit var viewModel: FavTvshowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var repository: MovieCatalogueRepository= mockkClass(MovieCatalogueRepository::class)

    @Mock
    private lateinit var observer: Observer<PagedList<TVEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVEntity>
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavTvshowViewModel(repository)
    }

    @Test
    fun getFavTvs() {
        val dummyTv = pagedList
        Mockito.`when`(dummyTv.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<TVEntity>>()
        tv.value = dummyTv

        Mockito.`when`(repository.getFavTvs()).thenReturn(tv)
        val tvEntity = viewModel.getFavTvs().value
        Mockito.verify<MovieCatalogueRepository>(repository).getFavTvs()

        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)

        viewModel.getFavTvs().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }
}