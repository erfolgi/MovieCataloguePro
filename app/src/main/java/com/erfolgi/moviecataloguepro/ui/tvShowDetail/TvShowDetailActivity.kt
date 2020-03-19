package com.erfolgi.moviecataloguepro.ui.tvShowDetail

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.util.Log
import android.view.View.*
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.erfolgi.moviecataloguepro.R
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.vo.Status
import com.erfolgi.moviecataloguepro.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tv_show_detail.*
import kotlinx.android.synthetic.main.content_detail_tv_show.*

class TvShowDetailActivity : AppCompatActivity() {
    companion object{
        var EXTRA_TV_ID="EXTRA_TV_ID"
    }
    lateinit var loading : CircularProgressDrawable
    lateinit var vm : TvShowDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        loading=CircularProgressDrawable(this)
        loading.strokeWidth = 5f
        loading.centerRadius = 30f
        loading.start()
        vm= obtainViewModel(this)
        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getString(EXTRA_TV_ID)
            if (tvId != null) {
                failed_tv_detail.setOnClickListener {
                    tv_show_detail_poster.visibility= VISIBLE
                    failed_tv_detail.visibility=GONE
                    loadData(tvId)
                }
                loadData(tvId)
            }
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
            onBackPressed()
        }

        val textFade = ObjectAnimator.ofPropertyValuesHolder(
            tv_swipeuptext,
            PropertyValuesHolder.ofFloat( "alpha",0.0f)
        )
        textFade.duration = 2000
        textFade.repeatCount = ObjectAnimator.INFINITE
        textFade.repeatMode = ObjectAnimator.REVERSE
        textFade.start()

        val imageFade = ObjectAnimator.ofPropertyValuesHolder(
            tv_swipeupimage,
            PropertyValuesHolder.ofFloat( "alpha",0.0f)
        )
        imageFade.duration = 2000
        imageFade.repeatCount = ObjectAnimator.INFINITE
        imageFade.repeatMode = ObjectAnimator.REVERSE
        imageFade.start()
    }
    private fun loadData(tvId:String?){
        vm.setTvId(tvId.toString())
        vm.tvLive.observe(this, Observer {
            if(it!=null) {
                when (it.status) {
                    Status.LOADING->{}
                    Status.SUCCESS-> populateTvShow(it.data)
                    Status.ERROR->{
                        tv_show_detail_poster.visibility= INVISIBLE
                        failed_tv_detail.visibility=VISIBLE
                    }
                }
            }else{
                tv_show_detail_poster.visibility= INVISIBLE
                failed_tv_detail.visibility=VISIBLE
            }
        })
    }
    private fun populateTvShow(tvShowEntity: TVEntity?) {
        Log.e("detailtv", tvShowEntity.toString())
        tv_swipeupimage.visibility=VISIBLE
        tv_swipeuptext.visibility=VISIBLE
        tv_show_detail_content.visibility =VISIBLE

        tv_show_detail_title.text=tvShowEntity?.name

        tv_show_detail_language.text=tvShowEntity?.originalLanguage
        tv_show_detail_date.text=tvShowEntity?.firstAirDate

        tv_show_detail_overview.text=tvShowEntity?.overview

        tv_show_detail_popularity.text= tvShowEntity?.popularity.toString()
        tv_show_detail_vote.text= tvShowEntity?.voteAverage.toString()
        tv_detail_rating.rating=(tvShowEntity?.voteAverage?.div(2.0))?.toFloat() ?: 0.0F
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w780/"+tvShowEntity?.posterPath)
            .apply(RequestOptions.placeholderOf(loading).error(R.drawable.ic_broken_image_black_24dp))
            .into(tv_show_detail_poster)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w780/"+tvShowEntity?.backdropPath)
            .apply(RequestOptions.placeholderOf(loading).error(R.drawable.ic_broken_image_black_24dp))
            .into(tv_detail_backdrop)
        //floatingActionButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_full_sad));
        if(tvShowEntity?.favorite==1){
            fab_tv.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_on))
        }else if(tvShowEntity?.favorite==0){
            fab_tv.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_off))
        }
        fab_tv.setOnClickListener {
            if(tvShowEntity?.favorite==0){
                Toast.makeText(this,"Saved to Favorite",Toast.LENGTH_LONG).show()
            }else if(tvShowEntity?.favorite==1){
                Toast.makeText(this,"Removed from Favorite",Toast.LENGTH_LONG).show()
            }
            vm.setFavorite()
        }

        //setSupportActionBar(tv_show_detail_toolbar)
        //supportActionBar?.title =tvShowEntity?.name
    }

    private fun obtainViewModel(activity: AppCompatActivity): TvShowDetailViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(this)
        return ViewModelProviders.of(activity, factory).get(TvShowDetailViewModel::class.java)
    }
}
