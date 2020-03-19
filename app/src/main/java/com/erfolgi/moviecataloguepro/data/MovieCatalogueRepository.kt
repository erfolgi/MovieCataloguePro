package com.erfolgi.moviecataloguepro.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.erfolgi.moviecataloguepro.data.source.ApiResponse
import com.erfolgi.moviecataloguepro.data.source.local.LocalDataSource
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.remote.RemoteDataSource
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailMovieResponse.DetailMovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailTvResponse.DetailTvResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.movieResponse.MovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.tvResponse.TvResponse
import com.erfolgi.moviecataloguepro.data.source.vo.Resource
import com.erfolgi.moviecataloguepro.util.AppExecutors
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


open class MovieCatalogueRepository(var remoteData: RemoteDataSource,
                                    private val localDataSource: LocalDataSource,
                                    private val appExecutors: AppExecutors
) :
    MovieCatalogueDataSource {

    companion object{
        var INSTANCE : MovieCatalogueRepository? =null

        fun getInstance(remoteData: RemoteDataSource,localDataSource: LocalDataSource,appExecutors: AppExecutors): MovieCatalogueRepository? {
            if (INSTANCE == null) {
                synchronized(MovieCatalogueRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            MovieCatalogueRepository(remoteData,localDataSource,appExecutors)
                    }
                }
            }
            return INSTANCE
        }
    }

    /************************
     *      GET MOVIES      *
     ************************/
    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getListMovie(), config).build()
            }
            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteData.getMovies()
            public override fun saveCallResult(data: MovieResponse) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data.results!!) {
                    val formatDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    val date:String? = formatDate.format(toGMTFormat(response?.releaseDate.toString())!!)

                    val movieData = MovieEntity(response?.overview,
                        response?.originalLanguage,
                        response?.originalTitle,
                        response?.video,
                        response?.title,
                        response?.posterPath,
                        response?.backdropPath,
                        date,
                        response?.popularity,
                        response?.voteAverage,
                        response?.id,
                        response?.adult,
                        response?.voteCount,
                        0)
                    movieList.add(movieData)
                }
                localDataSource.insertFavMovie(movieList)
            }
        }.asLiveData()
    }

    /************************
     *      GET TVSHOW      *
     ************************/
    override fun getTVs(): LiveData<Resource<PagedList<TVEntity>>> {
        return object : NetworkBoundResource<PagedList<TVEntity>, TvResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TVEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getListTv(), config).build()
            }
            override fun shouldFetch(data: PagedList<TVEntity>?): Boolean =
                data == null || data.isEmpty()
            public override fun createCall(): LiveData<ApiResponse<TvResponse>> =
                remoteData.getTvs()
            public override fun saveCallResult(data: TvResponse) {
                val tvList = ArrayList<TVEntity>()
                for (result in data.results!!) {
                    val formatDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                    val date:String? = formatDate.format(toGMTFormat(result?.firstAirDate.toString())!!)

                    val tvData = TVEntity(date,
                        result?.overview,
                        result?.originalLanguage,
                        result?.posterPath,
                        result?.backdropPath,
                        result?.originalName,
                        result?.popularity,
                        result?.voteAverage,
                        result?.name,
                        result?.id,
                        result?.voteCount,
                        0)
                    tvList.add(tvData)
                }
                localDataSource.insertFavTv(tvList)
            }
        }.asLiveData()
    }

    /************************
     *  GET DETAIL MOVIE    *
     ************************/
    override fun getFavDetailMovie(movieId: String): LiveData<Resource<MovieEntity>>? {
        return object : NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getFavMoviebyId(movieId)
            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null
            public override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> =
                remoteData.getDetailMovie(movieId)
            public override fun saveCallResult(data: DetailMovieResponse) {
                val movieData = ArrayList<MovieEntity>()

                val formatDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val date:String? = formatDate.format(toGMTFormat(data.releaseDate.toString())!!)

                val movie = MovieEntity(data.overview,
                    data.originalLanguage,
                    data.originalTitle,
                    data.video,
                    data.title,
                    data.posterPath,
                    data.backdropPath,
                    date,
                    data.popularity,
                    data.voteAverage,
                    data.id,
                    data.adult,
                    data.voteCount,
                    0)

                movieData.add(movie)

                localDataSource.insertFavMovie(movieData)
            }
        }.asLiveData()
    }

    /*********************
     *  GET DETAIL TV    *
     *********************/
    override fun getFavDetailTv(tvId: String): LiveData<Resource<TVEntity>>? {
        return object : NetworkBoundResource<TVEntity, DetailTvResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<TVEntity> =
                localDataSource.getFavTvbyId(tvId)
            override fun shouldFetch(data: TVEntity?): Boolean =
                data == null
            public override fun createCall(): LiveData<ApiResponse<DetailTvResponse>> =
                remoteData.getDetailTv(tvId)
            public override fun saveCallResult(data: DetailTvResponse) {
                val tvData = ArrayList<TVEntity>()
                val formatDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val date:String? = if(toGMTFormat(data.firstAirDate.toString())==null){
                    null
                }else{
                    formatDate.format(toGMTFormat(data.firstAirDate.toString())!!)
                }
                tvData.add(
                    TVEntity(date,
                        data.overview,
                        data.originalLanguage,
                        data.posterPath,
                        data.backdropPath,
                        data.originalName,
                        data.popularity,
                        data.voteAverage,
                        data.name,
                        data.id,
                        data.voteCount,0))
                localDataSource.insertFavTv(tvData)
            }
        }.asLiveData()
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
    /*********************
     *     L O C A L     *
     *********************/
    override fun setFavMovie(movie: MovieEntity, state: Int)  =
        appExecutors.diskIO().execute { localDataSource.setFavMovie(movie, state) }

    override fun setFavTv(tv: TVEntity, state: Int)   =
        appExecutors.diskIO().execute { localDataSource.setFavTv(tv, state) }

    override fun getFavMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()
    }

    override fun getFavTvs(): LiveData<PagedList<TVEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()
    }
}