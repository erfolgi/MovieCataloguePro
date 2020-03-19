package com.erfolgi.moviecataloguepro.ui.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.erfolgi.moviecataloguepro.R
import kotlinx.android.synthetic.main.fragment_favorite_tab.view.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_favorite_tab, container, false)
        view.fav_viewpager.adapter = FavoriteTabAdapter(childFragmentManager)
        view.fav_tab.setupWithViewPager(view.fav_viewpager)
        return view

    }
}
