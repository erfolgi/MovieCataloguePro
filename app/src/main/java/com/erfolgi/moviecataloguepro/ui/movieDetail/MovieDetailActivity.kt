package com.erfolgi.moviecataloguepro.ui.movieDetail

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erfolgi.moviecataloguepro.R
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Status
import com.erfolgi.moviecataloguepro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_detail_movie.*


class MovieDetailActivity : AppCompatActivity() {
    companion object{
        var EXTRA_MOVIE_ID="EXTRA_MOVIE_ID"
    }
    lateinit var vm : MovieDetailViewModel
    lateinit var loading :CircularProgressDrawable
    lateinit var imageFade : ObjectAnimator
    lateinit var textFade : ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        loading=CircularProgressDrawable(this)
        loading.strokeWidth = 5f
        loading.centerRadius = 30f
        loading.start()

        Glide.with(this)
            .load(loading)
            .apply(RequestOptions.placeholderOf(loading).error(R.drawable.ic_broken_image_black_24dp))
            .into(movie_detail_poster)

        vm=obtainViewModel(this)

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            loadData(movieId)

            failed_movie_detail.setOnClickListener {
                movie_detail_poster.visibility= View.VISIBLE
                failed_movie_detail.visibility=View.GONE
                loadData(movieId)
            }
        }else{
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
            onBackPressed()
        }
        textFade = ObjectAnimator.ofPropertyValuesHolder(
            movie_swipeuptext,
            PropertyValuesHolder.ofFloat( "alpha",0.0f)
        )
        textFade.duration = 2000
        textFade.repeatCount = ObjectAnimator.INFINITE
        textFade.repeatMode = ObjectAnimator.REVERSE


        imageFade = ObjectAnimator.ofPropertyValuesHolder(
            movie_swipeupimage,
            PropertyValuesHolder.ofFloat( "alpha",0.0f)
        )
        imageFade.duration = 2000
        imageFade.repeatCount = ObjectAnimator.INFINITE
        imageFade.repeatMode = ObjectAnimator.REVERSE

    }

    private fun loadData(movieId:String?){
        vm.setMovieId(movieId.toString())
        vm.movieLive.observe(this, Observer {
            if(it!=null){
                when (it.status) {
                    Status.LOADING->{}
                    Status.SUCCESS-> populateMovie(it.data)
                    Status.ERROR->{
                        movie_detail_poster.visibility= View.INVISIBLE
                        failed_movie_detail.visibility=View.VISIBLE
                    }
                }
//               populateMovie(it)
            }else{
                movie_detail_poster.visibility= View.INVISIBLE
                failed_movie_detail.visibility=View.VISIBLE
            }
        })
    }
    private fun populateMovie(entity: MovieEntity?) {
        movie_swipeupimage.visibility=View.VISIBLE
        movie_swipeuptext.visibility=View.VISIBLE
        movie_detail_content.visibility=View.VISIBLE
        if(entity==null)
            return
        val data : MovieEntity = entity

        movie_detail_title.text = data.title
        movie_detail_date.text=data.releaseDate
        movie_detail_language.text=data.originalLanguage
        movie_detail_overview.text = data.overview
        movie_detail_popularity.text=data.popularity.toString()
        movie_detail_vote.text=data.voteAverage.toString()
        movie_detail_rating.rating=(data.voteAverage?.div(2.0))?.toFloat() ?: 0.0F
        //"http://image.tmdb.org/t/p/w185"  ->> Small
        //"http://image.tmdb.org/t/p/w780/" ->> Large
            Glide.with(this)
        .load("https://image.tmdb.org/t/p/w780/"+data.posterPath)
        .apply(RequestOptions.placeholderOf(loading).error(R.drawable.ic_broken_image_black_24dp))
        .into(movie_detail_poster )

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w780/"+data.backdropPath)
            .apply(RequestOptions.placeholderOf(loading).error(R.drawable.ic_broken_image_black_24dp))
            .into(movie_detail_backdrop)

        imageFade.start()
        textFade.start()

        if(data.favorite==1){
            fab_movie.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_on))
        }else if(data.favorite==0){
            fab_movie.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_off))
        }
        fab_movie.setOnClickListener {
            if(data.favorite==0){
                Toast.makeText(this,"Saved to Favorite",Toast.LENGTH_LONG).show()
            }else if(data.favorite==1){
                Toast.makeText(this,"Removed from Favorite",Toast.LENGTH_LONG).show()
            }
            vm.setFavorite()
        }
        //setSupportActionBar(movie_detail_toolbar)
        //supportActionBar?.title =data.title
    }

    private fun obtainViewModel(activity: AppCompatActivity): MovieDetailViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(this)

        return ViewModelProviders.of(activity, factory).get(MovieDetailViewModel::class.java)
    }
}
