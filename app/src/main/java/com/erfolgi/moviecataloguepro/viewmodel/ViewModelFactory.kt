package com.erfolgi.moviecataloguepro.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erfolgi.moviecataloguepro.data.MovieCatalogueRepository
import com.erfolgi.moviecataloguepro.di.Injection
import com.erfolgi.moviecataloguepro.ui.favorite.favMovie.FavMovieViewModel
import com.erfolgi.moviecataloguepro.ui.favorite.favTvshow.FavTvshowViewModel
import com.erfolgi.moviecataloguepro.ui.movie.MovieViewModel
import com.erfolgi.moviecataloguepro.ui.movieDetail.MovieDetailViewModel
import com.erfolgi.moviecataloguepro.ui.tvShowDetail.TvShowDetailViewModel
import com.erfolgi.moviecataloguepro.ui.tvshow.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val repo: MovieCatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(repo) as T
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> TvShowViewModel(repo) as T
            modelClass.isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(repo) as T
            modelClass.isAssignableFrom(TvShowDetailViewModel::class.java) -> TvShowDetailViewModel(repo) as T
            modelClass.isAssignableFrom(FavMovieViewModel::class.java) -> FavMovieViewModel(repo) as T
            modelClass.isAssignableFrom(FavTvshowViewModel::class.java) -> FavTvshowViewModel(repo) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory? {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Injection.provideRepository(context)?.let {
                            ViewModelFactory(
                                it
                            )
                        }
                    }
                }
            }
            return INSTANCE
        }
    }
}