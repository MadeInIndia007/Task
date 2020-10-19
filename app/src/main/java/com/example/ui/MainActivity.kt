package com.example.ui

import android.content.BroadcastReceiver
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.adapter.ProfilesAdapter
import com.example.model.Model
import com.example.room.Enitity
import com.example.viewModel.MainViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CardStackListener, ProfilesAdapter.getData {

    val adapter = ProfilesAdapter(this)


    private lateinit var layoutManager: CardStackLayoutManager
    private var mNetworkReceiver: BroadcastReceiver? = null


    companion object {
        lateinit var mainViewModel: MainViewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)
        layoutManager = CardStackLayoutManager(this, this).apply {
            setSwipeableMethod(SwipeableMethod.Manual)
            setOverlayInterpolator(LinearInterpolator())
            setDirections(Direction.HORIZONTAL)

        }

        stack_view.layoutManager = layoutManager
        stack_view.adapter = adapter
        stack_view.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initView()
        initControl()



    }

    override fun onStart() {
        super.onStart()
        updateUi(false)
        mainViewModel.getData()
    }

    private fun initControl() {
        tv_fav.setOnClickListener {
            startActivity(Intent(this, FavActivity::class.java).apply {
            })


        }

    }

    fun updateUi(isEnable: Boolean) {
        progress_bar.visibility = if (isEnable) View.GONE else View.VISIBLE

    }

    private fun initView() {
        mainViewModel.onSuccess.observe(this, Observer {
            updateUi(true)
            Log.e("response", it.toString())
            adapter.setProfiles(it.results)
        })

        mainViewModel.error.observe(this, Observer {
            updateUi(true)
        })

    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.e("Disappered", view.toString())
        Log.e("DisapperedPosition", position.toString())
        adapter.getObj(position)

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.e("Dragging", direction.toString())
        Log.e("Dragging Ratio", ratio.toString())

    }

    override fun onCardSwiped(direction: Direction?) {
        Log.e("Swiped", direction.toString())

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.e("CardAppeared", view.toString())
        Log.e("CardApperedPos", position.toString())
    }

    override fun onCardRewound() {

    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mNetworkReceiver)
    }


    override fun abc(result: Model.Result) {
        val userEnity = Enitity().apply {
            results = result
        }
        mainViewModel.insert(userEnity)


    }
}
