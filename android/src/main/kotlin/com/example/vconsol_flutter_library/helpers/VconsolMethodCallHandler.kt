package com.example.vconsol_flutter_library.helpers

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import com.example.vconsol_flutter_library.utils.Methods.ACCEPT_FROM_WAITING_ROOM
import com.example.vconsol_flutter_library.utils.Methods.EXIT_MEETING
import com.example.vconsol_flutter_library.utils.Methods.HAND_RAISE_DOWN
import com.example.vconsol_flutter_library.utils.Methods.JOIN_MEETING
import com.example.vconsol_flutter_library.utils.Methods.MIC_MUTE_UNMUTE
import com.example.vconsol_flutter_library.utils.Methods.REMOVE_PARTICIPANT
import com.example.vconsol_flutter_library.utils.Methods.START_MEETING_SERVICE
import com.example.vconsol_flutter_library.utils.Methods.TOGGLE_CAMERA
import com.example.vconsol_flutter_library.utils.Methods.VIDEO_MUTE_UNMUTE
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

class VconsolMethodCallHandler(private var methodChannelHelper: MethodChannelHelper) : MethodCallHandler {

    private val TAG = "VconsolMethodCallHandler"
    private lateinit var activity: Activity
    fun setActivity(activity: Activity) {
        this.activity=activity
    }


    @SuppressLint("LongLogTag")
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            JOIN_MEETING -> {
                methodChannelHelper.joinMeeting(call,result)
            }

            START_MEETING_SERVICE -> {

            }

            VIDEO_MUTE_UNMUTE -> {

            }

            MIC_MUTE_UNMUTE -> {

            }

            HAND_RAISE_DOWN -> {

            }

            TOGGLE_CAMERA -> {

            }

            EXIT_MEETING -> {

            }

            ACCEPT_FROM_WAITING_ROOM -> {

            }

            REMOVE_PARTICIPANT -> {

            }
        }
    }




}