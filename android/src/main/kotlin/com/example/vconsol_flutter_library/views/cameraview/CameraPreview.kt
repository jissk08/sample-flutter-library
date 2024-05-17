package com.example.vconsol_flutter_library.views.cameraview

import android.content.Context
import android.util.Log
import android.view.View
import io.flutter.plugin.platform.PlatformView
import org.webrtc.EglBase
import org.webrtc.RendererCommon
import vc.lib.core.lib.MediaContainer
import vc.lib.core.video.VConsolVideoView

class CameraPreview(
    context: Context?,
    id: Int,
    creationParams: Map<String?, Any?>?,
):PlatformView {

    companion object {
        const val TAG = "CameraPreview"
    }

    private var videoView: VConsolVideoView = VConsolVideoView(context)

    fun setEglBase(eglBase: EglBase, container: MediaContainer) {
        initializeVideo(eglBase, container)
    }

    private fun initializeVideo(eglBase: EglBase, container: MediaContainer) {
        Log.d(TAG, "initialize CameraPreview++++++++++++++++++++++++++++")
        videoView?.apply {
            this.setZOrderMediaOverlay(true)
            this.setZOrderOnTop(true)
            if (!this.initlized){
                Log.d(TAG, "initializeVideo: if not ")
                initlize(eglBase,RendererCommon.ScalingType.SCALE_ASPECT_FILL)
            }
        }
        container.proxyVideoSink.let {
            it?.setTargetVideoSink(videoView)
        }
        print("Id of NativeViewPreview : ${videoView?.id}")
    }

    override fun getView(): View? {
        return videoView
    }

    override fun dispose() {

    }

}