package com.example.aplikasiwisata.content.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasiwisata.R
import com.example.aplikasiwisata.base.service.NetworkState
import com.example.aplikasiwisata.base.ui.BaseActivity
import com.example.aplikasiwisata.base.viewmodel.EventListViewModel
import kotlinx.android.synthetic.main.activity_event.*


@Suppress("DEPRECATION")
class EventActivity :BaseActivity() {
 private lateinit var eventAdapter: EventAdapter
 private lateinit var viewModel:EventListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        viewModel = ViewModelProviders.of(this).get(EventListViewModel::class.java)
        setUpAdapter()
        initState()
    }


    private fun  setUpAdapter(){
       eventAdapter = EventAdapter { viewModel.retry() }
       eventAdapter.setLayout(this,R.layout.event_item_layout)
       eventAdapter.setListner {  }
        val manager = GridLayoutManager(this,2)
       event_rv.layoutManager = manager
       event_rv.adapter=eventAdapter
       viewModel.eventList.observe(this, Observer {
           eventAdapter.submitList(it)
       })
    }

    private fun initState() {
        txt_error.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->
            progress_bar.visibility =
                if (viewModel.listIsEmpty() && state == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility =
                if (viewModel.listIsEmpty() && state == NetworkState.ERROR) View.VISIBLE else View.GONE
            if (!viewModel.listIsEmpty()) {
                eventAdapter.setNetworkState(state ?: NetworkState.DONE)
            }
        })
    }



}
