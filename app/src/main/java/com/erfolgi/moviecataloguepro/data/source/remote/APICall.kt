package com.erfolgi.moviecataloguepro.data.source.remote

import com.erfolgi.moviecataloguepro.data.source.remote.response.detailMovieResponse.DetailMovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailTvResponse.DetailTvResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.movieResponse.MovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.tvResponse.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APICall {

    @GET("movie/now_playing?api_key=bb83bd5cf496014b1bf123c7b88809ad&language=en-US")
    fun getMovie(): Call<MovieResponse>

    @GET("tv/airing_today?api_key=bb83bd5cf496014b1bf123c7b88809ad&language=en-US")
    fun getTv(): Call<TvResponse>

    @GET("movie/{movieId}?api_key=bb83bd5cf496014b1bf123c7b88809ad&language=en-US")
    fun getDetailMovie(@Path(value = "movieId", encoded = true) movieId:String): Call<DetailMovieResponse>

    @GET("tv/{tvId}?api_key=bb83bd5cf496014b1bf123c7b88809ad&language=en-US")
    fun getDetailTv(@Path(value = "tvId", encoded = true) tvId:String): Call<DetailTvResponse>

}