package com.erfolgi.moviecataloguepro.ui.movie


import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.ui.movieDetail.MovieDetailActivity
import com.erfolgi.moviecataloguepro.ui.movieDetail.MovieDetailActivity.Companion.EXTRA_MOVIE_ID


open class MovieAdapter internal constructor() : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {
//    private var mMovies :ArrayList<MovieEntity> =ArrayList<MovieEntity>()
//
//    fun setListMovies(listCourses: ArrayList<MovieModel>?) {
//        if (listCourses==null) return
//        this.mMovies.clear()
//        this.mMovies=listCourses
//        notifyDataSetChanged()
//    }
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

//    override fun getItemCount(): Int {
//        return mMovies.size
//    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//        val course = getItem(position)
//        if (course != null) {
//            holder.bind(course)
//        }
        if(getItem(position)==null){
            return
        }
        val mMovies:MovieEntity = getItem(position)!!
        holder.mLoading.visibility= VISIBLE
        holder.mTitle.text= mMovies.title
        holder.mYear.text=String.format("("+mMovies.releaseDate+")")
        holder.mOverview.text=mMovies.overview
        holder.mTitle.text=mMovies.title
        holder.mRating.rating= (mMovies.voteAverage?.div(2))?.toFloat() ?: 0.0F
        Log.i("movieAdapter","http://image.tmdb.org/t/p/w185"+mMovies.posterPath )
        Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w185"+mMovies.posterPath).
            listener(object : RequestListener<Drawable> {
                override fun onResourceReady(resource: Drawable?,model: Any?,target: Target<Drawable>?,dataSource: DataSource?,isFirstResource: Boolean
                ): Boolean {
                    holder.mLoading.visibility=GONE
                    return false
                }

                override fun onLoadFailed(e: GlideException?,model: Any?,target: Target<Drawable>?,isFirstResource: Boolean
                ): Boolean {
                    holder.mLoading.visibility=GONE
                    return false
                }
            }).
            error(R.drawable.ic_broken_image_black_24dp)
            .into(holder.mPoster)


        holder.itemView.setOnClickListener {
            val i = Intent( holder.itemView.context,MovieDetailActivity::class.java)
            i.putExtra(EXTRA_MOVIE_ID,mMovies.id.toString())
            Log.i("check extra","intent set extra ${mMovies.id}")
            holder.itemView.context.startActivity(i)
        }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById(R.id.movie_title)
        val mYear: TextView = itemView.findViewById(R.id.movie_year)
        val mOverview: TextView = itemView.findViewById(R.id.movie_overview)
        val mPoster: ImageView = itemView.findViewById(R.id.movie_poster) //movie_poster
        val mLoading: ProgressBar = itemView.findViewById(R.id.movie_poster_loading)
        val mRating: RatingBar = itemView.findViewById(R.id.movie_rating)
    }
}