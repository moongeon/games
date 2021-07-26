package com.mungeun.games.viewmodel

import android.app.Application
import android.media.MediaPlayer
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.*
import com.mungeun.games.R
import com.mungeun.games.utils.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CarrotViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    val countText = MutableLiveData("5")
    var countCarrot = MutableLiveData("5")
    private lateinit var job: Job
    private var cnt = 5
    //val mediaPlayer = MediaPlayer.create(context, R.raw.bg)
    private var clickCount = 0


    private fun initJob() {
        job = Job()
    }

    fun decreaseCountText() {
        initJob()
        clickCount++
        val coroutineName1 = job.toString().split("{")
        val coroutineName2 = coroutineName1[1].substring(0, 6)
        if (coroutineName2 == "Active" && clickCount > 1) {
            cancelJob()
        } else {
            viewModelScope.launch(job) {
                for (i in cnt downTo 0) {
                    countText.value = cnt.toString()
                    decreaseCount()
                }
            }
        }
    }

    private suspend fun decreaseCount() {
        cnt--
        delay(1000L)
    }

    private fun cancelJob() {
        if (job.isActive || job.isCompleted) job.cancel()
    }


    fun start(){
       // mediaPlayer.start()
        decreaseCountText()

    }
}