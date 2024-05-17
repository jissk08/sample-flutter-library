package com.example.vconsol_flutter_library.views.remoteview

import android.content.Context
import android.os.Handler
import android.os.Looper
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory
import org.webrtc.EglBase
import vc.lib.core.lib.MediaContainer

class RemoteVideoViewFactory: PlatformViewFactory(StandardMessageCodec.INSTANCE) {

    companion object{
        const val TAG = "RemoteVideoViewFactory"
    }

    private var remoteVideoView:RemoteVideoView?=null

    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        remoteVideoView=RemoteVideoView(context,viewId,creationParams)
        return remoteVideoView!!
    }

    fun setEglBase(eglBase: EglBase?, container: MediaContainer?) {
        Handler(Looper.getMainLooper()).postDelayed({
            remoteVideoView?.setEglBase(eglBase!!,container!!)
        }, 1000)

    }
}