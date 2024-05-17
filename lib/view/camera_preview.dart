import 'package:flutter/cupertino.dart';
import 'dart:io' show Platform;

import 'package:vconsol_flutter_library/view/android/android_camera_preview.dart';
import 'package:vconsol_flutter_library/view/ios/ios_camera_preview.dart';

class CameraPreview extends StatefulWidget {
  const CameraPreview({super.key});

  @override
  State<CameraPreview> createState() => _CameraPreviewState();
}

class _CameraPreviewState extends State<CameraPreview> {
  @override
  Widget build(BuildContext context) {
    return Platform.isAndroid? const AndroidCameraPreview() : const IosCameraPreview();
  }
}
