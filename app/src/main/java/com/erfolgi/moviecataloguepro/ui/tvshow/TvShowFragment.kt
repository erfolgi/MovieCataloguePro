package com.erfolgi.moviecataloguepro.ui.tvshow

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
import com.erfolgi.moviecataloguepro.data.source.vo.Status
import com.erfolgi.moviecataloguepro.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var rv: RecyclerView
    private lateinit var vm: TvShowViewModel
    private var mAdapter: TvShowAdapter?=null
    private lateinit var load: ProgressBar
    private lateinit var failed : TextView
    lateinit var mLinearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv=view.findViewById(R.id.rv_tv_show)
        load = view.findViewById(R.id.load_tv)
        failed = view.findViewById(R.id.failed_tv)
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
        if(savedInstanceState== null){
            vm = obtainViewModel(activity as FragmentActivity)

            if(mAdapter==null){
                mAdapter= TvShowAdapter()
            }else{
                mAdapter?.notifyDataSetChanged()
            }

            loadData()

            mLinearLayoutManager=LinearLayoutManager(context)
            rv.layoutManager = mLinearLayoutManager
            rv.setHasFixedSize(true)

            if(rv.adapter==null){
                rv.adapter = mAdapter
            }
        }
    }

    private fun loadData(){
        vm.getTvList().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                when (it.status) {
                    Status.LOADING -> load.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        load.visibility = View.GONE
                        mAdapter?.submitList(it.data)
                        mAdapter?.notifyDataSetChanged()
                    }
                    Status.ERROR -> {
                        load.visibility = View.GONE
                        failed.visibility= View.VISIBLE
                    }
                }
//                load.visibility= View.GONE
//                mAdapter?.setListShows(it)
//                mAdapter?.notifyDataSetChanged()
            }else{
                load.visibility= View.GONE
                failed.visibility= View.VISIBLE
            }
        })
    }

    companion object {
        fun newInstance(): Fragment {
            return TvShowFragment()
        }

        private fun obtainViewModel(activity: FragmentActivity): TvShowViewModel {
            // Use a Factory to inject dependencies into the ViewModel
            val factory = ViewModelFactory.getInstance(activity)
            return ViewModelProviders.of(activity, factory).get(TvShowViewModel::class.java)
        }
    }
}