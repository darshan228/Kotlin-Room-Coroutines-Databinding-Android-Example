package com.darshan.roomcoroutine.ui.detail.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.darshan.roomcoroutine.R
import com.darshan.roomcoroutine.data.api.ApiHelper
import com.darshan.roomcoroutine.data.api.RetrofitBuilder
import com.darshan.roomcoroutine.data.local.DatabaseBuilder
import com.darshan.roomcoroutine.data.local.DatabaseHelperImpl
import com.darshan.roomcoroutine.data.local.entity.RestImage
import com.darshan.roomcoroutine.databinding.ActivityDetailBinding
import com.darshan.roomcoroutine.ui.base.ViewModelFactory
import com.darshan.roomcoroutine.ui.detail.adaper.ViewPagerAdapter
import com.darshan.roomcoroutine.ui.detail.viewmodel.DetailViewModel
import com.darshan.roomcoroutine.utils.Status


class DetailActivity : AppCompatActivity() {

    private lateinit var view: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        view = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        view.lifecycleOwner = this
        view.viewModel = viewModel
        view.executePendingBindings()
        setupUI()
        setupObservers()
        viewModel.fetchRestaurantDetails(intent.getIntExtra("ID", 0))
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this, ViewModelFactory(
                ApiHelper(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(DetailViewModel::class.java)
    }

    private fun setupUI() {
        adapter = ViewPagerAdapter(arrayListOf())
        view.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        view.viewPager.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getRestImages().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { restImages -> retrieveList(restImages) }
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun retrieveList(restImages: List<RestImage>) {
        adapter.apply {
            addRestImages(restImages)
            notifyDataSetChanged()
        }
    }

}
