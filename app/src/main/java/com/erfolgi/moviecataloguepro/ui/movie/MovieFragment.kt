package com.erfolgi.moviecataloguepro.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erfolgi.moviecataloguepro.R
import com.erfolgi.moviecataloguepro.data.source.vo.Status
import com.erfolgi.moviecataloguepro.viewmodel.ViewModelFactory


class MovieFragment : Fragment(){

    private lateinit var rv: RecyclerView
    private lateinit var load: ProgressBar
    private lateinit var vm: MovieViewModel
    private lateinit var failed : TextView
    lateinit var mAdapter: MovieAdapter

    fun newInstance(): Fragment {
        return MovieFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        rv=view.findViewById(R.id.rv_movie)
        load = view.findViewById(R.id.load_movie)
        failed = view.findViewById(R.id.failed_movie)
        failed.setOnClickListener {
            load.visibility= VISIBLE
            failed.visibility= GONE
            loadData()
        }
        load.visibility= VISIBLE
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity!= null){
            vm = obtainViewModel(activity as FragmentActivity)
            mAdapter= MovieAdapter()

            loadData()

            rv.layoutManager = LinearLayoutManager(context)
            rv.setHasFixedSize(true)
            rv.adapter = mAdapter
        }

    }

    private fun loadData(){
        vm.getMovieList().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                when (it.status) {
                    Status.LOADING -> load.visibility = VISIBLE
                    Status.SUCCESS -> {
                        load.visibility = GONE
                        mAdapter.submitList(it.data)
                        mAdapter.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        load.visibility = View.GONE
                        failed.visibility= VISIBLE
                    }
                }
//                load.visibility=GONE
//                mAdapter.setListMovies(it)
//                mAdapter.notifyDataSetChanged()
            }else{
                load.visibility=GONE
                failed.visibility= VISIBLE
            }
        })
    }

    companion object {
        fun newInstance(): Fragment {
            return MovieFragment()
        }

        private fun obtainViewModel(activity: FragmentActivity): MovieViewModel {
            // Use a Factory to inject dependencies into the ViewModel
            val factory = ViewModelFactory.getInstance(activity)
            return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
        }
    }
}


