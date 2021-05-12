package com.darshan.roomcoroutine.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.darshan.roomcoroutine.R
import com.darshan.roomcoroutine.data.api.ApiHelper
import com.darshan.roomcoroutine.data.api.RetrofitBuilder
import com.darshan.roomcoroutine.data.local.DatabaseBuilder
import com.darshan.roomcoroutine.data.local.DatabaseHelperImpl
import com.darshan.roomcoroutine.data.local.entity.Restaurant
import com.darshan.roomcoroutine.databinding.ActivityMainBinding
import com.darshan.roomcoroutine.ui.base.ViewModelFactory
import com.darshan.roomcoroutine.ui.detail.view.DetailActivity
import com.darshan.roomcoroutine.ui.main.adapter.MainAdapter
import com.darshan.roomcoroutine.ui.main.viewmodel.MainViewModel
import com.darshan.roomcoroutine.utils.ItemClickListener
import com.darshan.roomcoroutine.utils.Status


class MainActivity : AppCompatActivity() {

    private lateinit var view: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setToolBar()
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setToolBar() {
        setSupportActionBar(view.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        view.tvTitle.text = "Restaurant List"
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        view.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), object : ItemClickListener {
            override fun onListItemClicked(restaurant: Restaurant) {
                startActivity(
                    Intent(
                        this@MainActivity,
                        DetailActivity::class.java
                    ).apply { putExtra("ID", restaurant.id) })
            }
        })
        view.recyclerView.addItemDecoration(
            DividerItemDecoration(
                view.recyclerView.context,
                (view.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        view.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getRestaurants().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        view.recyclerView.visibility = View.VISIBLE
                        view.progressBar.visibility = View.GONE
                        resource.data?.let { restaurants -> retrieveList(restaurants) }
                    }
                    Status.ERROR -> {
                        view.recyclerView.visibility = View.VISIBLE
                        view.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        view.progressBar.visibility = View.VISIBLE
                        view.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(restaurants: List<Restaurant>) {
        adapter.apply {
            addRestaurants(restaurants)
            notifyDataSetChanged()
        }
    }
}
