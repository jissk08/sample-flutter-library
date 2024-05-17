import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'dart:async';

import 'package:vconsol_flutter_library/conference_events.dart';
import 'package:vconsol_flutter_library/vconsol_flutter_library.dart';
import 'package:http/http.dart' as http;
import 'package:vconsol_flutter_library/view/camera_preview.dart';
import 'package:vconsol_flutter_library/view/remote_view.dart';
import 'package:vconsol_flutter_library_example/entity/join_data.dart';
import 'package:vconsol_flutter_library_example/entity/meeting_data_from_server.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> implements ConferenceEvents {
  var _vconsolFlutterLibraryPlugin;

  @override
  void initState() {
    super.initState();
    _vconsolFlutterLibraryPlugin = VconsolFlutterLibrary(this);
    initMeeting();
  }

  Future<void> initMeeting() async {
    MeetingDataFromServer? meetingParams =
        await joinMeeting(meetingId: "5542541739", password: "032253");
    _vconsolFlutterLibraryPlugin.joinMeeting(meetingParams?.toJson());
  }

  @override
  Widget build(BuildContext context) {
    double screenHeight = MediaQuery.of(context).size.height;
    double previewHeight = screenHeight / 5;
    double previewWidth = previewHeight * 0.56;
    return MaterialApp(
      home: Scaffold(
        body: Stack(children: [
          const RemoteView(),
          Positioned(
            bottom: 0,
            left: 0,
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Container(
                height: previewHeight,
                width: previewWidth,
                decoration: BoxDecoration(
                  border: Border.all(
                    color: Colors.white, // Border color
                    width: 2.0, // Border width
                  ),
                ),
                child: const CameraPreview(),
              ),
            ),
          ),
        ]),
      ),
    );
  }

  @override
  void participantJoined() {
    debugPrint("++++++++++++++++++++++++++participant joined event channel ");
  }

  Future<MeetingDataFromServer?> joinMeeting(
      {required String meetingId, required String password}) async {
    var joinApiResponse = await http.post(
        Uri.parse(
            'https://beta.vconsol.com/api/v1/pre-auth/meeting/join/$meetingId'),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(<String, String>{
          'meetingID': meetingId,
          'name': 'jiss',
          'password': password
        }));

    if (joinApiResponse.statusCode == 200) {
      var joinData = JoinData.fromJson(
          jsonDecode(joinApiResponse.body) as Map<String, dynamic>);
      var meetingTokenApiResponse = await http.post(
          Uri.parse(
              'https://beta.vconsol.com/api/v1/pre-auth/meeting/$meetingId/meeting-token'),
          headers: <String, String>{
            'Content-Type': 'application/json; charset=UTF-8',
            'Authorization': 'Bearer ${joinData.token}'
          },
          body: jsonEncode(<String, String>{
            'token': joinData.token!,
          }));
      if (meetingTokenApiResponse.statusCode == 200) {
        return MeetingDataFromServer.fromJson(
            jsonDecode(meetingTokenApiResponse.body) as Map<String, dynamic>);
      } else {
        return null;
      }
    }
    return null;
  }
}
