package com.example.vconsol_flutter_library.utils

import com.example.vconsol_flutter_library.entity.ConferanceData


class Utilities {

    companion object{
        fun getConferencedata(arguments:Map<String, String>?):ConferanceData{
            return ConferanceData(
                token = arguments?.get("token"),
                title = arguments?.get("title"),
                time = System.nanoTime().toString(),
                role = arguments?.get("role"),
                serverUrl = arguments?.get("server"),
                iceServers = null,
                type = arguments?.get("type"),
                audioMuted = false,
                meetingId = arguments?.get("meetingID"),
                breakout = false,
                breakoutParentMeetingID = arguments?.get("breakoutParentMeetingID"),
                participantCount = 10,
                description = arguments?.get("description"),
                configuration = null,
                authenticated = false,
                secure = false,
                mediaServer = arguments?.get("mediaServer"),
            )
        }

    }
}