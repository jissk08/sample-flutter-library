package com.example.vconsol_flutter_library

import android.app.Activity
import android.os.Build
import com.example.vconsol_flutter_library.helpers.MethodChannelHelper
import com.example.vconsol_flutter_library.helpers.VconsolEventChannelHandler
import com.example.vconsol_flutter_library.helpers.VconsolMethodCallHandler
import com.example.vconsol_flutter_library.utils.Methods.METHOD_CHANNEL
import com.example.vconsol_flutter_library.views.cameraview.CameraPreview
import com.example.vconsol_flutter_library.views.cameraview.CameraPreviewFactory
import com.example.vconsol_flutter_library.views.remoteview.RemoteVideoView
import com.example.vconsol_flutter_library.views.remoteview.RemoteVideoViewFactory
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.embedding.engine.plugins.lifecycle.HiddenLifecycleReference
import io.flutter.plugin.common.MethodChannel
import org.koin.core.error.KoinAppAlreadyStartedException
import vc.lib.VC
import vc.lib.builders.VCLibrayParam
import vc.lib.builders.params.ApiParams
import vc.lib.utils.VCLogger


/** VconsolFlutterLibraryPlugin */
class VconsolFlutterLibraryPlugin : FlutterPlugin, ActivityAware {


    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var vconsolMethodCallHandler: VconsolMethodCallHandler
    private lateinit var vconsolEventChannelHandler: VconsolEventChannelHandler
    private lateinit var methodChannelHelper: MethodChannelHelper
    private lateinit var activity: Activity
    private val TAG = "VconsolFlutterLibraryPlugin"
    private var libraryInitialized = false
    private var isBelowApi29 = false



    private lateinit var cameraPreviewFactory: CameraPreviewFactory
    private lateinit var remoteVideoViewFactory: RemoteVideoViewFactory

    companion object {
        lateinit var vc: VC
    }


    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        registerViewFactories(flutterPluginBinding)
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, METHOD_CHANNEL)
        vconsolEventChannelHandler= VconsolEventChannelHandler(flutterPluginBinding.binaryMessenger,cameraPreviewFactory,remoteVideoViewFactory)
        methodChannelHelper=MethodChannelHelper(vconsolEventChannelHandler)
        vconsolMethodCallHandler = VconsolMethodCallHandler(methodChannelHelper)
        channel.setMethodCallHandler(vconsolMethodCallHandler)

    }

    private fun registerViewFactories(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        cameraPreviewFactory= CameraPreviewFactory()
        remoteVideoViewFactory= RemoteVideoViewFactory()
        flutterPluginBinding.platformViewRegistry.registerViewFactory(
            CameraPreview.TAG,
            cameraPreviewFactory
        )
        flutterPluginBinding.platformViewRegistry.registerViewFactory(
            RemoteVideoView.TAG,
            remoteVideoViewFactory
        )
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activity = binding.activity
        vconsolMethodCallHandler.setActivity(binding.activity)
        methodChannelHelper.setActivity(binding.activity)
        initializeLibrary()
    }

    override fun onDetachedFromActivityForConfigChanges() {

    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {

    }

    override fun onDetachedFromActivity() {

    }


    private fun initializeLibrary() {
        var vcParams = VCLibrayParam(
            activity = activity,
            serviceIconIntValue = 0,
            reconnectInternally = true,
            apiParams = ApiParams("http://test/")
        )
        if (!libraryInitialized) {
            try {
                vc = VC(vcParams)
                vc.initVCLibrary(false)
                vc.initVCLibrary(false)
                libraryInitialized = true
            } catch (e: KoinAppAlreadyStartedException) {
                vc.initVCLibrary(true)
            }
        }
        VCLogger.print(TAG, "VC   : " + vc.toString())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity.registerActivityLifecycleCallbacks(vc)
        } else {
            isBelowApi29 = true
        }
    }




}
