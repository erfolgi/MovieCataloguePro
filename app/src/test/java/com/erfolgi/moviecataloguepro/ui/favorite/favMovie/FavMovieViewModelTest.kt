package com.erfolgi.moviecataloguepro.ui.favorite.favMovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import io.mockk.mockkClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavMovieViewModelTest {

    private lateinit var viewModel: FavMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var repository: MovieCatalogueRepository = mockkClass(MovieCatalogueRepository::class)

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = FavMovieViewModel(repository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovie = pagedList
        Mockito.`when`(dummyMovie.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        Mockito.`when`(repository.getFavMovies()).thenReturn(movie)
        val movieEntity = viewModel.getFavMovies().value
        Mockito.verify<MovieCatalogueRepository>(repository).getFavMovies()

        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getFavMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovie)
    }
}