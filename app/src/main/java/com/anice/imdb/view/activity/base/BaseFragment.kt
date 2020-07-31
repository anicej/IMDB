package com.anice.imdb.view.activity.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.anice.imdb.viewmodel.base.BaseViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


abstract class BaseFragment<VM : BaseViewModel, VDB : ViewDataBinding> : Fragment(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    protected lateinit var viewModel: VM

    protected lateinit var dataBinding: VDB

    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val bindingVariable: Int

    abstract val viewModelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(activity!!, layoutRes)
        dataBinding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()

        // observe title event
        observeTitleEvent()
        // observe api progress event
        observeApiProgressEvent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun observeTitleEvent() {
        (viewModel as BaseViewModel).title.observe(this, Observer {
        })
    }

    private fun observeApiProgressEvent() {
        (viewModel as BaseViewModel).apiRequestInProgress.observe(this, Observer {
            if (it) {
                // show progress bar
            } else {
                // hide progress bar
            }
        })
    }


    override fun supportFragmentInjector() = dispatchingAndroidInjector
}