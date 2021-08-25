package com.faranjit.meditory.features.detail.presentation

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faranjit.meditory.R
import com.faranjit.meditory.base.BaseViewModel
import com.faranjit.meditory.features.detail.presentation.DetailActivity.Companion.KEY_DETAIL

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class DetailViewModel : BaseViewModel() {

    companion object {
        private const val MEDIA_URL = "https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3"
    }

    val detail = ObservableField<DetailModel>()
    val playIcon = ObservableInt(R.drawable.ic_play)

    private var detailModel: DetailModel? = null
    private val mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        setDataSource(MEDIA_URL)
    }

    private val detailType = MutableLiveData<DetailType>()
    val detailTypeLiveData: LiveData<DetailType>
        get() = detailType

    init {
        mediaPlayer.prepare()
    }

    /**
     * DetailActivity'i acarken verilen parametrei parse eder,
     * ui'i gunceller.
     */
    fun parseIntent(intent: Intent) {
        if (intent.hasExtra(KEY_DETAIL)) {
            detailModel = intent.getParcelableExtra(KEY_DETAIL)
            detailModel?.let {
                detail.set(it)
                detailType.value = it.type
            }
        }
    }

    fun playOrPauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            playIcon.set(R.drawable.ic_play)
        } else {
            mediaPlayer.start()
            playIcon.set(R.drawable.ic_pause)
        }
    }

    fun clearMediaPlayer() {
        mediaPlayer.reset()
        mediaPlayer.release()
    }
}