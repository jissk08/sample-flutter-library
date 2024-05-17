package com.example.vconsol_flutter_library.utils

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import com.example.vconsol_flutter_library.entity.ConferanceData
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import vc.lib.OnConferenceEvents
import vc.lib.builders.ConfernceParam
import vc.lib.builders.params.IceServers
import vc.lib.builders.params.SignallingParams
import vc.lib.core.video.VideoAPI
import vc.lib.utils.VCLogger
import java.util.*
import kotlin.collections.HashMap


/*
 *  Copyright © 2022 Techgentsia  Software Technologies Private Limited - All rights reserved.
 *  This software is produced by Techgentsia. This is Proprietary and confidential
 *  Unauthorized redistribution, reproduction, or usage of
 *  this software in whole or in part without the express
 *  written consent of Techgentsia is strictly prohibited.
 *  Author - Clifford P Y  e-mail - <clifford@techgentsia.com> ,  Oct 2022
 */
private const val TAG = "CommonUtils"

class CommonUtils(
    private val activity: Activity,
    private val lifeCycleOwner: LifecycleOwner,
    private val conferenceEvents: OnConferenceEvents
) {
    var token =
        "eyJhbGciOiJIUzI1NiIsICJjdHkiOiJKV1QifQ.eyJleHAiOjE2NjgwMjM1MDgsInR5cGUiOiJhY3RpdmUiLCJyb2xlIjoibW9kZXJhdG9yIiwicm9vbUlEIjoiMTIzNiIsIndhaXRpbmdSb29tIjp0cnVlLCJmZWF0dXJlIjp7InJlY29yZGluZyI6dHJ1ZSwicG9zdGVyIjp0cnVlfSwiY29uZmlnIjp7InBvc3RlciI6Imh0dHBzOi8vZDFrOGR2eThrcnY3cGQuY2xvdWRmcm9udC5uZXQvX19BU1NFVF9fMjUxMTY1OGUtNDlkZi00OWQwLWFmOGUtMTY0MDg2ZmFiYjI0LmpwZyIsIndhdGVybWFyayI6Imh0dHBzOi8vYWxwaGEudmNvbnNvbC5jb20vaW1nL2xvZ28ucG5nIiwid2F0ZXJtYXJrUG9zaXRpb24iOiJsZWZ0LXRvcCJ9LCJvcmdhbml6YXRpb24iOnsib3JnYW5pemF0aW9uSUQiOiI4M2NmMjU0ZC0yYjllLTQwZTQtOThiMi04YmVlMmY0YTIyZTUifSwib3duZXIiOnsib3duZXJJRCI6Ijc3MDBlMWMwLTQ3OTMtNDFjYy1hNmNkLWI3N2ZkOTMzMzU0NSJ9LCJzZXJ2ZXIiOnsic2VydmVySUQiOiJhbHBoYSJ9LCJ1c2VyIjp7Im5hbWUiOiJCaW5veS00NyIsInJlcXVlc3RJRCI6IjY3MWYwNDliLTEwODAtNGFlYS1hNmQ4LThhOGJhZTkwNmQ3MSIsImV4dGVybmFsVXNlclBhcmFtcyI6ImV5SnBaQ0k2SWpJMU5qRXpOMlV5TFRkbFlUSXROREl6TlMxaU5ETTNMVFkwTm1GaU9XUTNOR1EyWVNJc0luVnpaWEp1WVcxbElqb2lZVEJoTXpOaE5XWXhNVEkyT0RKbFl5SXNJblpvYjNOMElqb2labVEyWTJOaE5USXVaMmx0Y3k1bmIzWXVhVzRpTENKd2FHOTBieUk2SW1oMGRIQnpPaTh2YzJGdGNHRnlhekV1WjJsdGN5NW5iM1l1YVc0dmRqSXZZWEJwTDJacGJHVXZSRkF2TW1NNE5XWmtNV0UwWkRBNE1tRXlNR1kwWkdRNVpUaGlNR0UyTlRnMk5qSTRNelk0WW1KaU5UYzFZbUkwTkRRNFl6ZzFPRGRrTmprM1pqaGhOelF4TmlKOSJ9LCJtZWRpYVNlcnZlciI6IlYyIn0.75Ouyv2WpPHz6B7vkyBapYY1VyZyur_3JMnaVCXx_sQ"



    fun generateMeetingParameters(conferanceData: ConferanceData?, baseUrl: String): ConfernceParam {
        var signallingParams = SignallingParams(
            token = conferanceData?.token,
            socketUrl = conferanceData?.serverUrl,
            socketPath = "/socket.io/api/v1"
        )
        return ConfernceParam(
            activity = activity,
            cameraAPI = VideoAPI.CAMERA2,
            serviceType = if (conferanceData?.mediaServer == Constants.MEDIA_SERVER_V2) ConfernceParam.Companion.ServiceType.SFU else ConfernceParam.Companion.ServiceType.MCU,
            lifecycleOwner = lifeCycleOwner,
            signallingParams = signallingParams,
            iceServerParams = getIceServers(),
            conferenceEvents = conferenceEvents,
            baseUrl = baseUrl
        );
    }

    /**
     * This returns turn and stun server list.
     * @return List<IceServers>
     */
    private fun getIceServers(): List<IceServers> {
        return listOf(
            IceServers("", "", "stun:turn7.vconsol.com:3478"),
            IceServers(
                "1658430930:9151457093",
                "G2gQNoKt9bW7VqoEaP1FjmH1Q2Q=",
                "turn:turn7.vconsol.com:3478?transport=udp"
            ),
            IceServers(
                "1658430930:9151457093",
                "G2gQNoKt9bW7VqoEaP1FjmH1Q2Q=",
                "turns:turn7.vconsol.com:443?transport=tcp"
            )
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    public fun generateToken(): String {
        var header = HashMap<String, String>()
        header["cty"] = "JWT"
        header["alg"] = "HS256"
        var userID = "83cf254d-2b9e-40e4-98b2-8bee2f4a22e5"// UUID.randomUUID().toString()
        var name = "Clifford-Android"
        var requestID = "671f049b-1080-4aea-a6d8-8a8bae906d71"
        var externalUserID = "external-user-id-1"
        var extenalUserParam = "param1 param2"
        var user = User(userID, name, requestID, externalUserID, extenalUserParam)
        val jwt = Jwts.builder()
            .setHeader(header as Map<String, Any>?)
            .claim("exp", (System.currentTimeMillis() / 1000) + 18000)
            .claim("type", "active")
            .claim("role", "moderator")
            .claim("roomID", "1236")
            .claim("waitingRoom", false)
            .claim("feature", Feature(recording = true, poster = true))
            .claim("user", user)
            .claim("owner", Owner("7700e1c0-4793-41cc-a6cd-b77fd9333545"))
            .claim("organization", Oraganization(userID))
            .claim("server", Server("alpha"))
            .claim("mediaServer", "V2")
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.getDecoder()
                    .decode("Z2RjJV1OeHluOFl+V1V+TTI9eXQsQVh7QVR0IWU/QCY=".toByteArray())
            )
            .compact()
        VCLogger.printi(TAG, "jwt token : $jwt")

        return jwt
    }

}

data class User(
    val userID: String,
    val name: String,
    val requestID: String,
    val externalUserID: String,
    val externalUserParams: String
)

data class Feature(val recording: Boolean, val poster: Boolean)
data class Server(val serverID: String)
data class Oraganization(val organizationID: String)
data class Owner(val ownerID: String)
