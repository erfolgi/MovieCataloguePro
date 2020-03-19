package com.erfolgi.moviecataloguepro.di

import android.content.Context
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.data.source.local.LocalDataSource
import com.erfolgi.moviecataloguepro.data.source.local.room.AppDataBase
import com.erfolgi.moviecataloguepro.data.source.remote.APICall
import com.erfolgi.moviecataloguepro.data.source.remote.APIClient
import com.erfolgi.moviecataloguepro.data.source.remote.RemoteDataSource
import com.erfolgi.moviecataloguepro.util.AppExecutors

open class Injection {
    companion object{
        fun provideRepository(context: Context): MovieCatalogueRepository? {
            val database = AppDataBase.getInstance(context)

            val call= APIClient.getClient(APICall::class.java)
            val remoteRepository = RemoteDataSource.getInstance(call)
            val localDataSource = LocalDataSource.getInstance(database.academyDao())
            val appExecutors = AppExecutors()
            return MovieCatalogueRepository.getInstance(remoteRepository, localDataSource, appExecutors)
        }
    }
}