package com.erfolgi.moviecataloguepro.ui.tvshow

import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.erfolgi.moviecataloguepro.R
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.ui.tvShowDetail.TvShowDetailActivity
import com.erfolgi.moviecataloguepro.ui.tvShowDetail.TvShowDetailActivity.Companion.EXTRA_TV_ID

class TvShowAdapter internal constructor() : PagedListAdapter<TVEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
//    var mTvShows :MutableList<TVModel> =ArrayList()
//
//    fun setListShows(listCourses: ArrayList<TVModel>?) {
//        if (listCourses == null) return
//        this.mTvShows.clear()
//        this.mTvShows=(listCourses)
//    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVEntity>() {
            override fun areItemsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TVEntity, newItem: TVEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        return TvShowViewHolder(view)
    }

//    override fun getItemCount(): Int {
//        return mTvShows.size
//    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        if(getItem(position)==null){
            return
        }
        val mTvShows: TVEntity = getItem(position)!!

        holder.mTitle.text=mTvShows.name
        holder.mYear.text=String.format("("+mTvShows.firstAirDate+")")
        holder.mOverview.text=mTvShows.overview
//        holder.mTitle.text=mTvShows.title

        holder.mRating.rating= (mTvShows.voteAverage?.div(2.0))?.toFloat() ?: 0.0F
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w185"+mTvShows.posterPath).
            listener(object : RequestListener<Drawable> {
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    holder.mLoading.visibility= View.GONE
                    return false
                }

                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                ): Boolean {
                    holder.mLoading.visibility= View.GONE
                    return false
                }
            }).
            error(R.drawable.ic_broken_image_black_24dp)
            .into(holder.mPoster)
        holder.itemView.setOnClickListener {
            val i = Intent(holder.itemView.context, TvShowDetailActivity::class.java)
            i.putExtra(EXTRA_TV_ID,mTvShows.id.toString())
            holder.itemView.context.startActivity(i)
        }
    }


    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById(R.id.tv_title) //TvShow_poster
        val mYear: TextView = itemView.findViewById(R.id.tv_season)
        val mOverview: TextView = itemView.findViewById(R.id.tv_overview)
        val mPoster: ImageView = itemView.findViewById(R.id.tv_poster) //TvShow_poster
        val mLoading: ProgressBar = itemView.findViewById(R.id.tv_poster_loading)
        val mRating: RatingBar = itemView.findViewById(R.id.tv_rating)
    }
}