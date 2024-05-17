package com.example.vconsol_flutter_library.helpers

import androidx.camera.view.PreviewView
import com.example.vconsol_flutter_library.utils.Events
import com.example.vconsol_flutter_library.views.cameraview.CameraPreviewFactory
import com.example.vconsol_flutter_library.views.remoteview.RemoteVideoViewFactory
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import org.webrtc.EglBase
import vc.lib.OnConferenceEvents
import vc.lib.core.audio.AppRTCAudioManager
import vc.lib.core.breakout.data.BreakoutRoomEnd
import vc.lib.core.breakout.data.BreakoutRoomInfo
import vc.lib.core.lib.ChatType
import vc.lib.core.lib.InternalReconnectionStatus
import vc.lib.core.lib.MediaContainer
import vc.lib.core.stat.NetworkStatMessage
import vc.lib.core.video.VideoType
import vc.lib.entity.Chat
import vc.lib.entity.ConnectionData
import vc.lib.entity.ContentPermissionData
import vc.lib.entity.ContentPermissionResponseData
import vc.lib.entity.CowatchData
import vc.lib.entity.LockedData
import vc.lib.entity.Mode
import vc.lib.entity.MultiBroadcastListItem
import vc.lib.entity.MultiBroadcastStatus
import vc.lib.entity.MuteData
import vc.lib.entity.MuteMeData
import vc.lib.entity.Participant
import vc.lib.entity.PendingData
import vc.lib.entity.RecordData
import vc.lib.entity.RevokeContentPermission
import vc.lib.entity.RtmpDataFromServer
import vc.lib.entity.SmartAppEventData
import vc.lib.entity.Webinar
import vc.lib.utils.VCLogger

class VconsolEventChannelHandler(
    private var binaryMessenger: BinaryMessenger,
    private var cameraPreviewFactory: CameraPreviewFactory,
    private var remoteVideoViewFactory: RemoteVideoViewFactory
) : OnConferenceEvents {

    private val TAG = "VconsolEventChannelHandler"
    var onAudioList: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_AUDIO_LIST) { onAudioList = it } }
    var onBreakoutJoinInvitationBy: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_BREAKOUT_JOIN_INVITATION_BY) {
            onBreakoutJoinInvitationBy = it
        }
    }
    var onBreakoutRecall: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_BREAKOUT_RECALL) { onBreakoutRecall = it } }
    var onBreakoutRoomEnd: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_BREAKOUTROOM_END) { onBreakoutRoomEnd = it } }
    var onChat: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_CHAT) { onChat = it } }
    var onCoHostToken: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_COHOST_TOKEN) { onCoHostToken = it } }
    var onConferenceInfo: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_CONFERENCE_INFO) { onConferenceInfo = it } }
    var onConferenceInfoUpdate: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CONFERENCE_INFO_UPDATE) {
            onConferenceInfoUpdate = it
        }
    }
    var onConferenceLocked: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CONFERENCE_LOCKED) {
            onConferenceLocked = it
        }
    }
    var onConnectionSuccess: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CONNECTION_SUCCESS) {
            onConnectionSuccess = it
        }
    }
    var onContent: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_CONTENT) { onContent = it } }
    var onContentPermissionResult: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CONTENT_PERMISSION_RESULT) {
            onContentPermissionResult = it
        }
    }
    var onContentRequest: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_CONTENT_REQUEST) { onContentRequest = it } }
    var onContentSettingsChanged: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CONTENT_SETTINGS_CHANGED) {
            onContentSettingsChanged = it
        }
    }
    var onContentShareRequest: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CONTENT_SHARE_REQUEST) {
            onContentShareRequest = it
        }
    }
    var onCowatchEvents: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_COWATCH_EVENTS) { onCowatchEvents = it } }
    var onCustomConferenceEvent: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CUSTOM_CONFERENCE_EVENT) {
            onCustomConferenceEvent = it
        }
    }
    var onCustomConferenceEvents: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_CUSTOM_CONFERENCE_EVENTS) {
            onCustomConferenceEvents = it
        }
    }
    var onExited: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_EXITED) { onExited = it } }
    var onInternalReconnection: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_INTERNAL_RECONNECTION) {
            onInternalReconnection = it
        }
    }
    var onKickedOut: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_KICKEDOUT) { onKickedOut = it } }
    var onLayoutChanged: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_LAYOUT_CHANGED) { onLayoutChanged = it } }
    var onLibraryInitlized: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_LIBRARY_INITLIZED) {
            onLibraryInitlized = it
        }
    }
    var onModeChange: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_MODE_CHANGE) { onModeChange = it } }
    var onMultiBroadcastList: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_MULTIBROADCAST_LIST) {
            onMultiBroadcastList = it
        }
    }
    var onMultiBroadcastStatus: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_MULTIBROADCAST_STATUS) {
            onMultiBroadcastStatus = it
        }
    }
    var onMuteAll: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_MUTEALL) { onMuteAll = it } }
    var onMuteFromRemote: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_MUTE_FROM_REMOTE) { onMuteFromRemote = it } }
    var onParticipantStatus: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_PARTICIPANT_STATUS) {
            onParticipantStatus = it
        }
    }
    var onParticipants: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_PARTICIPANTS) { onParticipants = it } }
    var onPendingAccept: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_PENDING_ACCEPT) { onPendingAccept = it } }
    var onQualityUpdate: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_QUALITY_UPDATE) { onQualityUpdate = it } }
    var onRecord: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_RECORD) { onRecord = it } }
    var onRevokeContentPermission: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_REVOKE_CONTENT_PERMISSION) {
            onRevokeContentPermission = it
        }
    }
    var onRtmp: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_RTMP) { onRtmp = it } }
    var onSmartAppEvent: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_SMART_APP_EVENT) { onSmartAppEvent = it } }
    var onUserPinned: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_USER_PINNED) { onUserPinned = it } }
    var onUserSpeaks: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_USER_SPEAKS) { onUserSpeaks = it } }
    var onWebinarStarted: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_WEBINAR_STARTED) { onWebinarStarted = it } }
    var onWebinarStoped: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_WEBINAR_STOPED) { onWebinarStoped = it } }
    var onWhiteBoardInit: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.ON_WHITE_BOARD_INIT) { onWhiteBoardInit = it } }
    var onWhiteBoardOnChange: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_WHITE_BOARD_ONCHANGE) {
            onWhiteBoardOnChange = it
        }
    }
    var onWhiteBoardVolatileChange: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.ON_WHITE_BOARD_VOLATILE_CHANGE) {
            onWhiteBoardVolatileChange = it
        }
    }
    var reconnectAndJoinToBreakoutRoom: EventChannel.EventSink? = null.apply {
        initializeEventChannel(Events.RECONNECT_AND_JOIN_TO_BREAKOUTROOM) {
            reconnectAndJoinToBreakoutRoom = it
        }
    }
    var reconnectMeeting: EventChannel.EventSink? =
        null.apply { initializeEventChannel(Events.RECONNECT_MEETING) { reconnectMeeting = it } }


    private fun initializeEventChannel(event: String, eventSink: (EventChannel.EventSink) -> Unit) {
        EventChannel(
            binaryMessenger, event
        ).setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(args: Any?, events: EventChannel.EventSink?) {
                if (events != null) {
                    eventSink.invoke(events)
                }
            }
            override fun onCancel(args: Any?) {}
        })
    }

    override fun onAudioList(
        currentDevice: AppRTCAudioManager.AudioDevice?,
        availableAudioDevices: Set<AppRTCAudioManager.AudioDevice>
    ) {

    }

    override fun onBreakoutJoinInvitationBy(participant: Participant) {

    }

    override fun onBreakoutRecall(breakoutRoomEnd: BreakoutRoomEnd) {

    }

    override fun onBreakoutRoomEnd(breakoutRoomEnd: BreakoutRoomEnd) {

    }

    override fun onChat(type: ChatType, chat: Chat) {

    }

    override fun onCoHostToken(token: String) {

    }

    override fun onConferenceInfo(message: String?) {

    }

    override fun onConferenceInfoUpdate(conferenceInfo: String?) {

    }

    override fun onConferenceLocked(locked: LockedData) {

    }

    override fun onConnectionSuccess(connectionData: ConnectionData) {
        
    }

    override fun onContent(started: Boolean, participant: Participant?) {

    }

    override fun onContentPermissionResult(permissionData: ContentPermissionResponseData?) {

    }

    override fun onContentRequest(participants: List<Participant>) {

    }

    override fun onContentSettingsChanged(data: ContentPermissionData) {

    }

    override fun onContentShareRequest(participants: List<Participant?>?) {

    }

    override fun onCowatchEvents(cowatchData: CowatchData?) {

    }

    override fun onCustomConferenceEvent(type: String?, data: String?) {

    }

    override fun onCustomConferenceEvents(type: String?, data: String?) {

    }

    override fun onExited() {

    }

    override fun onInternalReconnection(status: InternalReconnectionStatus, message: String) {

    }

    override fun onKickedOut() {

    }

    override fun onLayoutChanged(layoutName: String, changedBy: Participant) {

    }

    override fun onLibraryInitlized(eglBase: EglBase?) {

    }

    override fun onModeChange(mode: Mode) {

    }

    override fun onMultiBroadcastList(multiBroadcastList: List<MultiBroadcastListItem>) {

    }

    override fun onMultiBroadcastStatus(multiBroadcastStatus: MultiBroadcastStatus) {

    }

    override fun onMuteAll(muteData: MuteData) {

    }

    override fun onMuteFromRemote(muteData: MuteMeData?) {

    }

    override fun onParticipantStatus(participant: Participant?) {

    }

    override fun onParticipants(
        active: List<Participant>, passive: List<Participant>, waiting: List<Participant>
    ) {

    }

    override fun onPendingAccept(pendingData: PendingData?) {

    }

    override fun onQualityUpdate(message: String, code: NetworkStatMessage) {

    }

    override fun onRecord(started: Boolean, record: RecordData) {

    }

    override fun onRevokeContentPermission(data: RevokeContentPermission) {

    }

    override fun onRtmp(started: Boolean, rtmp: RtmpDataFromServer) {

    }

    override fun onSmartAppEvent(smartAppEventData: SmartAppEventData) {

    }

    override fun onUserPinned(pinids: ArrayList<String>) {

    }

    override fun onUserSpeaks(participant: Participant?) {

    }

    override fun onVideo(
        id: String,
        type: VideoType,
        eglBase: EglBase?,
        previewView: PreviewView?,
        container: MediaContainer?
    ) {
        when (type) {
            VideoType.CAMERA_2_PREVIEW -> {
                VCLogger.printi(TAG, "onVideo CAMERA_2_PREVIEW")
                cameraPreviewFactory.setEglBase(eglBase, container)
            }

            VideoType.CAMERA_X_PREVIEW -> {
                cameraPreviewFactory.setEglBase(eglBase, container)
                VCLogger.printi(TAG, "onVideo CAMERA_X_PREVIEW")
            }

            VideoType.CONTENT -> {
                VCLogger.printi(TAG, "onVideo CONTENT  $id")
            }

            VideoType.SFU_VIDEO -> {
                VCLogger.printi(TAG, "onVideo SFU_VIDEO")
            }

            VideoType.MCU_VIDEO -> {
                remoteVideoViewFactory.setEglBase(eglBase, container)
            }

            else -> {

            }

        }
    }

    override fun onWebinarStarted(webinar: Webinar) {

    }

    override fun onWebinarStoped(webinar: Webinar) {

    }

    override fun onWhiteBoardInit(data: String) {

    }

    override fun onWhiteBoardOnChange(data: String) {

    }

    override fun onWhiteBoardVolatileChange(data: String) {

    }

    override fun reconnectAndJoinToBreakoutRoom(roomInfo: BreakoutRoomInfo) {

    }

    override fun reconnectMeeting() {

    }


}