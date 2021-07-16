package com.gm.assignment.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat.apply
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.gm.assignment.musicapp.adapter.TrackListAdapter
import com.gm.assignment.musicapp.databinding.ActivityMainBinding
import com.gm.assignment.musicapp.viewmodel.TrackViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TrackViewModel
    lateinit var trackListAdapter: TrackListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        val view = binding.root
        trackListAdapter = TrackListAdapter(this)
        setContentView(view)
        getUserSearchInput()
        createRecyclerView()
    }

    private fun getUserSearchInput() {
       binding.buttonSearch.setOnClickListener {
           loadAPI(binding.editTextArtistName.text.toString())
       }
    }

    private fun loadAPI(userInput: String) {
        viewModel = ViewModelProvider(this).get(TrackViewModel::class.java)
        viewModel.isLoading.observe(this, Observer<Boolean>(){
            if(it == true){
                binding.progressBar.visibility = View.VISIBLE
            } else{
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.getTrackListObserver().observe(this, Observer<TrackListResult>(){
            if(it != null){
                trackListAdapter.trackList = it.results
                Log.d("loadAPI", "loadAPI: ${trackListAdapter.trackList}")
                trackListAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this, applicationContext.getString(R.string.api_error), Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeApiCall(userInput)
    }

    private fun createRecyclerView() {
        binding.recyclerViewArtistInfo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))
            adapter = trackListAdapter
        }
    }
}