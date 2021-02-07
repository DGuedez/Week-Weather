package com.example.weekweather.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.weekweather.application.WeatherApplication
import com.example.weekweather.presentation.factory.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment: Fragment() {

    @LayoutRes
    abstract fun layoutId(): Int

    @Inject
    lateinit var viewModelFactory: ViewModelFactory


    private var mView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(layoutId(), container, false)
        return mView
    }

 abstract fun setUpViewModel()
 abstract fun initialCondition()


    override fun onAttach(context: Context) {
        getComponentInjection(context)
        super.onAttach(context)
    }

     private fun getComponentInjection(context: Context) {
        (context.applicationContext as WeatherApplication)
            .weatherComponent.inject(this)
    }


}