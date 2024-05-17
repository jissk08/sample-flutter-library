import 'package:flutter/cupertino.dart';
import 'dart:io' show Platform;

import 'package:vconsol_flutter_library/view/android/android_remote_view.dart';
import 'package:vconsol_flutter_library/view/ios/ios_remote_view.dart';

class RemoteView extends StatefulWidget {
  const RemoteView({super.key});

  @override
  State<RemoteView> createState() => _RemoteViewState();
}

class _RemoteViewState extends State<RemoteView> {
  @override
  Widget build(BuildContext context) {
    return Platform.isAndroid
        ? const AndroidRemoteView()
        : const IosRemoteView();
  }
}
