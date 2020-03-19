package com.erfolgi.moviecataloguepro.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.erfolgi.moviecataloguepro.data.TestRepository
import com.erfolgi.moviecataloguepro.data.source.local.LocalDataSource
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.remote.RemoteDataSource
import com.erfolgi.moviecataloguepro.data.source.vo.Resource
import com.erfolgi.moviecataloguepro.util.AppExecutors
import com.erfolgi.moviecataloguepro.util.DataDummy
import com.erfolgi.moviecataloguepro.util.LiveDataTestUtil
import com.erfolgi.moviecataloguepro.util.PagedListUtil
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.text.SimpleDateFormat
import java.util.*

class MovieCatalogueRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val movieResponse = DataDummy.generateDummyMovieResponse()
    private val movieId= movieResponse.results?.get(0)?.id.toString()
    private val movieDetailResponse = DataDummy.generateDummyMovieDetailResponse()


    private val tvResponse = DataDummy.generateDummyTvShowResponse()
    private val tvId = tvResponse.results?.get(0)?.id.toString()
    private val tvDetailResponse = DataDummy.generateDummyTvShowDetailResponse()

    private val local = mockkClass(LocalDataSource::class)
    private val appExecutors = mockkClass(AppExecutors::class)
    @MockK
    var remoteDataSource : RemoteDataSource= mockkClass(RemoteDataSource::class)


    private var movieCatalogueRepo: TestRepository =
        TestRepository(remoteDataSource,local,appExecutors)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        movieCatalogueRepo= TestRepository(remoteDataSource,local,appExecutors)
    }

    @Test
    fun getMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        every { local.getListMovie() }.returns(dataSourceFactory)
        movieCatalogueRepo.getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify{local.getListMovie()}
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.results?.size?.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTv() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        every { local.getListTv()}.returns(dataSourceFactory)
        movieCatalogueRepo.getTVs()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify{(local).getListTv()}
        assertNotNull(tvEntities.data)
        assertEquals(movieResponse.results?.size?.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovieDetail()
        every { local.getFavMoviebyId(movieId)}.returns(dummyEntity)
        val movieEntity = LiveDataTestUtil.getValue(movieCatalogueRepo.getFavDetailMovie(movieId)!!)
        verify{(local).getFavMoviebyId(movieId)}
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data?.id)
        assertNotNull(movieEntity.data?.title)
        assertEquals(movieEntity.data?.title, dummyEntity.value!!.title)
    }

    @Test
    fun getDetailTv() {
        val dummyEntity = MutableLiveData<TVEntity>()
        dummyEntity.value = DataDummy.generateDummyTvShowDetail()
        every { local.getFavTvbyId(tvId)}.returns(dummyEntity)

        val tvEntity = LiveDataTestUtil.getValue(movieCatalogueRepo.getFavDetailTv(tvId)!!)
        //verify{(local).getFavTvbyId(tvId)}
        assertNotNull(tvEntity)
        assertNotNull(tvEntity.data?.id)
        assertNotNull(tvEntity.data?.name)
        assertEquals(tvEntity.data?.name, dummyEntity.value!!.name)
    }

    private fun toGMTFormat(datime:String?): Date? {
        val timezoneID = TimeZone.getDefault().id
        val formatter = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
        formatter.timeZone = TimeZone.getTimeZone(timezoneID)
        return if(datime==null || datime=="null"){
            null
        }else{
            formatter.parse(datime)
        }
    }

}