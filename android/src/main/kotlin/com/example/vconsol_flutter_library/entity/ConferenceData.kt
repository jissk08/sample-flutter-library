package com.example.vconsol_flutter_library.entity

import android.os.Parcel
import android.os.Parcelable
import vc.lib.builders.params.IceServers
import vc.lib.entity.Configuration

/*
 *  Copyright © 2022 Techgentsia  Software Technologies Private Limited - All rights reserved.
 *  This software is produced by Techgentsia. This is Proprietary and confidential
 *  Unauthorized redistribution, reproduction, or usage of
 *  this software in whole or in part without the express
 *  written consent of Techgentsia is strictly prohibited.
 *  Author - Clifford P Y  e-mail - <clifford@techgentsia.com> ,  Jun 2022
 */

data class ConferanceData(
    val token: String?,
    val title: String?,
    val time: String?,
    val serverUrl: String?,
    val iceServers: List<IceServers>?,
    val role: String?,  // moderator/ guest
    val type: String?,  //active/ passive
    val audioMuted: Boolean,
    val meetingId: String?,
    val breakout: Boolean,
    val breakoutParentMeetingID: String?,
    val participantCount: Int,
    var description: String?,
    var configuration: Configuration?,
    var authenticated: Boolean,
    var secure: Boolean,
    val mediaServer:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(IceServers),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Configuration::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeString(title)
        parcel.writeString(time)
        parcel.writeString(serverUrl)
        parcel.writeTypedList(iceServers)
        parcel.writeString(role)
        parcel.writeString(type)
        parcel.writeByte(if (audioMuted) 1 else 0)
        parcel.writeString(meetingId)
        parcel.writeByte(if (breakout) 1 else 0)
        parcel.writeString(breakoutParentMeetingID)
        parcel.writeInt(participantCount)
        parcel.writeString(description)
        parcel.writeParcelable(configuration, flags)
        parcel.writeByte(if (authenticated) 1 else 0)
        parcel.writeByte(if (secure) 1 else 0)
        parcel.writeString(mediaServer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConferanceData> {
        override fun createFromParcel(parcel: Parcel): ConferanceData {
            return ConferanceData(parcel)
        }

        override fun newArray(size: Int): Array<ConferanceData?> {
            return arrayOfNulls(size)
        }
    }
}