package com.erfolgi.moviecataloguepro.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
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
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MovieViewModelTest{
    private var viewModel: MovieViewModel? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val movieCatalogueRepository = mockkClass(MovieCatalogueRepository::class)
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)

        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value =dummyMovies

        every{movieCatalogueRepository.getMovies()}.returns(movies)
        val movieEntities = viewModel?.getMovieList()?.value?.data
        verify{(movieCatalogueRepository).getMovies()}
        assertNotNull(movieEntities)
        assertEquals(5,movieEntities?.size)

        viewModel?.getMovieList()?.observeForever(observer)
        verify(observer).onChanged(dummyMovies)


    }
}


