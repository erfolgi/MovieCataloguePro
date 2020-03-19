package com.erfolgi.moviecataloguepro.ui.favorite.favMovie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
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
import com.erfolgi.moviecataloguepro.viewmodel.ViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class FavMovieFragment : Fragment() {
    private lateinit var rv: RecyclerView
    private lateinit var load: ProgressBar
    private lateinit var vm: FavMovieViewModel
    private lateinit var failed : TextView
    lateinit var mAdapter: FavMovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv=view.findViewById(R.id.rv_movie)
        load = view.findViewById(R.id.load_movie)
        failed = view.findViewById(R.id.failed_movie)
        failed.setOnClickListener {
            load.visibility= View.VISIBLE
            failed.visibility= View.GONE
            loadData()
        }
        load.visibility= View.VISIBLE
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity!= null){
            vm = obtainViewModel(activity as FragmentActivity)
            mAdapter= FavMovieAdapter()

            loadData()

            rv.layoutManager = LinearLayoutManager(context)
            rv.setHasFixedSize(true)
            rv.adapter = mAdapter
        }

    }
    private fun loadData(){
        vm.getFavMovies().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                load.visibility = View.GONE
                mAdapter.submitList(it)
                mAdapter.notifyDataSetChanged()
            }else{
                load.visibility= View.GONE
                failed.visibility= View.VISIBLE
            }
        })
    }

    companion object {
        fun newInstance(): Fragment {
            return FavMovieFragment()
        }

        private fun obtainViewModel(activity: FragmentActivity): FavMovieViewModel {
            // Use a Factory to inject dependencies into the ViewModel
            val factory = ViewModelFactory.getInstance(activity)
            return ViewModelProviders.of(activity, factory).get(FavMovieViewModel::class.java)
        }
    }

}
