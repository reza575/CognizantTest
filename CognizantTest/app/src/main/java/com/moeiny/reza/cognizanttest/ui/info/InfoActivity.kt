package com.moeiny.reza.cognizanttest.ui.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.cognizanttest.AndroidApplication
import com.moeiny.reza.cognizanttest.R
import com.moeiny.reza.cognizanttest.databinding.ActivityMainBinding
import com.moeiny.reza.vehiclestest.core.viewmodel.MyViewModelFactory

class InfoActivity : AppCompatActivity() {
    lateinit var viewModel: InfoViewModel
    lateinit var mBinding: ActivityMainBinding

    private val adapter: InfoAdapter by lazy {
        InfoAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * function setUpVies: Assign parameters and values
         */
        setUpViews()
        /**
         * SetUp all the livedata parameters to start their job(Observing data)
         */
        setupLiveData()
    }

    private fun setUpViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, MyViewModelFactory( application as AndroidApplication)).get(
            InfoViewModel::class.java )
        viewModel.getAllInfo()
        mBinding.recyclerView.adapter = adapter
    }

    private fun setupLiveData(){
        /**
         * observing the title of API to update actionbar title after any changing
         */
        viewModel.titleLiveData.observe(this, Observer {
            getSupportActionBar()?.setTitle(it)
        })

        /**
         * observing the list of rows of API to update data on recycler view after any changing
         */
        viewModel.infoLiveData.observe(this, Observer { infoList->
            mBinding.loadingPanel.visibility = if(infoList.isNotEmpty()) View.GONE else View.VISIBLE
            adapter.infoList = infoList
        })

        /**
         * observing data fetching from API to update loading state
         */
        viewModel.loadingLiveData.observe(this, Observer {
            mBinding.loadingPanel.visibility = if(it) View.VISIBLE else View.GONE
        })

        /**
         * observing for any error during data fetching from API to update error state
         */
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this,"error on loading Data", Toast.LENGTH_SHORT).show()
        })
    }
}