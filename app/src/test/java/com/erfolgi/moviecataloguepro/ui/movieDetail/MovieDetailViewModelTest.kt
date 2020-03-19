package com.erfolgi.moviecataloguepro.ui.movieDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
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
class MovieDetailViewModelTest{
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: MovieDetailViewModel? = null

    private val movieResponse = DataDummy.generateDummyMovies()
    private val movieId= movieResponse[9].id.toString()

    private val movieCatalogueRepository = mockkClass(MovieCatalogueRepository::class)

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(movieCatalogueRepository)
        viewModel!!.setMovieId(movieId)
    }
    @Test
    fun getMovieDetail() {
        val dummyMovie = Resource.success(DataDummy.generateDummyMovieDetail())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value=dummyMovie
        every{movieCatalogueRepository.getFavDetailMovie(movieId)}.returns(movie)
        viewModel!!.movieLive.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}