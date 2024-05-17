package com.example.vconsol_flutter_library.helpers

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.vconsol_flutter_library.VconsolFlutterLibraryPlugin
import com.example.vconsol_flutter_library.utils.CommonUtils
import com.example.vconsol_flutter_library.utils.Utilities
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import vc.lib.builders.ConfernceParam
import vc.lib.core.interfaces.IConferenceFunctions

class MethodChannelHelper(var vconsolEventChannelHandler: VconsolEventChannelHandler) {

    private lateinit var commonUtils: CommonUtils
    private lateinit var conferenceParam: ConfernceParam
    private lateinit var conferenceManager: IConferenceFunctions
    private lateinit var activity: Activity
    fun setActivity(activity: Activity) {
        this.activity=activity
        initialize()
    }

    private fun initialize() {
        commonUtils= CommonUtils(activity,activity as LifecycleOwner,vconsolEventChannelHandler)
    }

    fun joinMeeting(call: MethodCall, result: MethodChannel.Result) {
        val arguments = call.arguments() as Map<String, String>?
        var conferenceData= Utilities.getConferencedata(arguments)
        Log.d("++++++++++++++++++++++++++++++", "joinMeeting: $conferenceData")
        conferenceParam = commonUtils.generateMeetingParameters(conferenceData,"https://beta.vconsol.com")
        Log.d("++++++++++++++++++++++++++++++", "joinMeeting: $conferenceParam")
        conferenceManager = VconsolFlutterLibraryPlugin.vc.joinConference(confernceParam = conferenceParam)!!
    }


}