
import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';
import 'package:vconsol_flutter_library/conference_events.dart';
import 'vconsol_flutter_library_platform_interface.dart';

class VconsolFlutterLibrary {

  late ConferenceEvents conferenceEvents;

  static const participantJoinEvent = EventChannel("tst.vconsol/participantJoin");
  late StreamSubscription participantJoinStreamSubscription;


  VconsolFlutterLibrary(this.conferenceEvents){
    listenEventChannel();
  }

  Future<String?> getPlatformVersion() {
    return VconsolFlutterLibraryPlatform.instance.getPlatformVersion();
  }

  joinMeeting(Map<String, dynamic> meetingParams){
    VconsolFlutterLibraryPlatform.instance.joinMeeting(meetingParams);
  }

  void listenEventChannel() {
    participantJoinStreamSubscription =
        participantJoinEvent.receiveBroadcastStream().listen((event) {
          conferenceEvents.participantJoined();
        });
  }

}
