package com.moeiny.reza.vehiclestest.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.cognizanttest.AndroidApplication
import com.moeiny.reza.cognizanttest.ui.info.InfoViewModel


import java.lang.IllegalArgumentException

/**
 * A  class that create a model class with base of NewsViewModel.
 */
class MyViewModelFactory(private val application: AndroidApplication) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass == InfoViewModel::class.java )
            InfoViewModel(application.infoRepository) as T
        else
            throw IllegalArgumentException()
    }
}
