package com.anice.imdb.view.activity.main_page

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.anice.imdb.BR
import com.anice.imdb.R
import com.anice.imdb.databinding.ActivityMainBinding
import com.anice.imdb.view.activity.base.BaseActivity
import com.anice.imdb.view.adapter.MainListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "TodoActivity"

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .apply(RequestOptions().error(R.drawable.placeholder_image))
        .into(view)
}

@BindingAdapter("imageUrlDetail")
fun loaddetailImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .apply(RequestOptions().error(R.drawable.placeholder_image))
        .into(view)
}

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), OnItemClickListener {

    override val layoutRes = R.layout.activity_main
    override val bindingVariable = BR.viewModel
    override val viewModelClass = MainViewModel::class.java
    lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        // register observer
        observeDataFailureEvent()
        observeListDataSuccessEvent()
        observeDetailDataSuccessEvent()


        viewModel.getMainlistData()



        rvMovies.setLayoutManager(GridLayoutManager(this, 2))


//
    }

    private fun observeDataFailureEvent() {

        viewModel.dataFailureEvent.observe(this, Observer {
            Toast.makeText(this, "Error: $it", Toast.LENGTH_SHORT).show()
        })
    }

    private fun observeListDataSuccessEvent() {

        viewModel.mainlistData.observe(this, Observer {
            adapter = MainListAdapter(it.Search, this)
            rvMovies.adapter = adapter
        })
    }

    private fun observeDetailDataSuccessEvent() {

        viewModel.detailsData.observe(this, Observer {
//            adapter = MainListAdapter(it.Search, this)
//            rvMovies.adapter = adapter
        })
    }

    override fun onItemClick(imdbID: String) {
        fL.visibility = View.VISIBLE
        viewModel.getDetailsData(imdbID)
    }

    override fun onBackPressed() {
        if (fL.visibility == View.VISIBLE)
            fL.visibility = View.GONE
        else
            super.onBackPressed()
    }
}