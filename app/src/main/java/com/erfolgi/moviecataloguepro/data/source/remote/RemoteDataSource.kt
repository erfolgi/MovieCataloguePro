package com.erfolgi.moviecataloguepro.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.erfolgi.moviecataloguepro.data.source.ApiResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailMovieResponse.DetailMovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailTvResponse.DetailTvResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.movieResponse.MovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.tvResponse.TvResponse
import com.erfolgi.moviecataloguepro.util.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteDataSource (var apiCall2: APICall?) {

    private val apiCall= APIClient.getClient(APICall::class.java)

    fun getMovies(): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieResponse>>()
        val apiCall= APIClient.getClient(APICall::class.java)
        val call = apiCall.getMovie()
            call.enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>
                ) {
                   // response.body()?.let { movieInterface?.onLoadSuccess(it) }

                    if(response.body()!=null){
                        val movieResponse:MovieResponse = response.body()!!
                        resultMovie.value= ApiResponse.success(movieResponse)
                        EspressoIdlingResource.decrement()
                    }
                }
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                   // movieInterface?.onLoadFailed()
                }
            })
        return resultMovie
    }

    fun getTvs(): LiveData<ApiResponse<TvResponse>>{
        EspressoIdlingResource.increment()
        val resultTV = MutableLiveData<ApiResponse<TvResponse>>()
        val call = apiCall.getTv()
        call.enqueue(object: Callback<TvResponse>{
            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.i("RemoteRepo Tv","$t")
               // tvInterface.onLoadFailed()
            }
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
               // response.body()?.let { tvInterface.onLoadSuccess(it) }
                if (response.body()!=null){
                    val tvResponse:TvResponse = response.body()!!
                    resultTV.value= ApiResponse.success(tvResponse)
                    EspressoIdlingResource.decrement()
                }

            }
        })
        return resultTV
    }

    fun getDetailMovie(movieId:String):LiveData<ApiResponse<DetailMovieResponse>>{
        val call = apiCall.getDetailMovie(movieId)
        val resultDetailMovie = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        call.enqueue(object: Callback<DetailMovieResponse>{
            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                Log.i("RemoteRepo DetailMovie","$t")

            }
            override fun onResponse(call: Call<DetailMovieResponse>, response: Response<DetailMovieResponse>) {
                //response.body()?.let { detailMovieInterface.onLoadSuccess(it) }
                if(response.body()!=null){
                    val data :DetailMovieResponse = response.body()!!
                    resultDetailMovie.value=ApiResponse.success(data)
                }

            }
        })
        return resultDetailMovie
    }
    fun getDetailTv(tvId:String):LiveData<ApiResponse<DetailTvResponse>>{
        val call = apiCall.getDetailTv(tvId)
        val resultDetailTv = MutableLiveData<ApiResponse<DetailTvResponse>>()
        call.enqueue(object: Callback<DetailTvResponse>{
            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                Log.i("RemoteRepo DetailTv","$t")
//                detailTvInterface.onLoadFailed()
            }
            override fun onResponse(call: Call<DetailTvResponse>, response: Response<DetailTvResponse>) {
//                response.body()?.let { detailTvInterface.onLoadSuccess(it) }
                if(response.body()!=null){
                    val data :DetailTvResponse = response.body()!!
                    resultDetailTv.value=ApiResponse.success(data)
                }
            }
        })
        return resultDetailTv
    }

    companion object{
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(apiCall: APICall): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(apiCall)
            }
            return INSTANCE as RemoteDataSource
        }
    }
}
