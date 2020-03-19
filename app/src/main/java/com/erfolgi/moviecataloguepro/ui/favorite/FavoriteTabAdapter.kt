package com.erfolgi.moviecataloguepro.ui.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.erfolgi.moviecataloguepro.ui.favorite.favMovie.FavMovieFragment
import com.erfolgi.moviecataloguepro.ui.favorite.favTvshow.FavTvshowFragment

class FavoriteTabAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return FavMovieFragment()
            }
            1 -> {
                return FavTvshowFragment()
            }
        }
        return FavMovieFragment()
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Movie"

            1 -> return "TV Show"
        }
        return null
    }

    companion object {
        private val FRAGMENT_COUNT = 2
    }
}