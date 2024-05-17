package com.example.vconsol_flutter_library.views.remoteview

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import io.flutter.plugin.platform.PlatformView
import org.webrtc.EglBase
import org.webrtc.RendererCommon
import vc.lib.core.lib.MediaContainer
import vc.lib.core.video.VConsolVideoView


class RemoteVideoView(
    var context: Context?,
    id: Int,
    creationParams: Map<String?, Any?>?,
) : PlatformView {

    companion object {
        const val TAG = "RemoteVideoView"
    }


    private var videoView: VConsolVideoView = VConsolVideoView(context)
    private var linearLayout :LinearLayout = LinearLayout(context)

    init {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, // Width
            ViewGroup.LayoutParams.WRAP_CONTENT // Height
        )
        linearLayout.layoutParams = layoutParams
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity=Gravity.CENTER
        linearLayout.setBackgroundColor(Color.parseColor("#242d38"))
    }
    override fun getView(): View? {
        return linearLayout
    }


    override fun dispose() {
        
    }

    fun setEglBase(eglBase: EglBase, container: MediaContainer) {
        initializeVideo(eglBase, container)
    }

    private fun initializeVideo(eglBase: EglBase, container: MediaContainer) {
        videoView.apply {
            if (!this.initlized) {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post {
                    initlize(eglBase, RendererCommon.ScalingType.SCALE_ASPECT_FIT)
                }
            }
        }
        container.proxyVideoSink.let {
            it?.setTargetVideoSink(videoView)
        }
        linearLayout.addView(videoView)
    }




}