package com.example.vconsol_flutter_library.views.cameraview

import android.content.Context
import android.os.Handler
import android.os.Looper
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import org.webrtc.EglBase
import vc.lib.core.lib.MediaContainer

class CameraPreviewFactory: PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    companion object {
        const val TAG = "CameraPreviewFactory"
    }

    private  var cameraPreview: CameraPreview?=null
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        cameraPreview=CameraPreview(context,viewId,creationParams)
        return cameraPreview!!
    }

    fun setEglBase(eglBase: EglBase?, container: MediaContainer?){
        Handler(Looper.getMainLooper()).postDelayed({
            cameraPreview?.setEglBase(eglBase!!,container!!)
        }, 1000)
    }
}