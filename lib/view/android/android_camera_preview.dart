import 'package:flutter/cupertino.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

class AndroidCameraPreview extends StatefulWidget {
  const AndroidCameraPreview({super.key});

  @override
  State<AndroidCameraPreview> createState() => _AndroidCameraPreviewState();
}

class _AndroidCameraPreviewState extends State<AndroidCameraPreview> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: PlatformViewLink(
        viewType: "CameraPreview",
        surfaceFactory: (
            BuildContext context,
            PlatformViewController controller,
            ) {
          return AndroidViewSurface(
            controller: controller as AndroidViewController,
            hitTestBehavior: PlatformViewHitTestBehavior.opaque,
            gestureRecognizers: const {},
          );
        },
        onCreatePlatformView:
            (PlatformViewCreationParams params) {
          final AndroidViewController controller =
          PlatformViewsService.initExpensiveAndroidView(
            id: params.id,
            viewType: "CameraPreview",
            layoutDirection: TextDirection.ltr,
            creationParamsCodec: const StandardMessageCodec(),
            onFocus: () => params.onFocusChanged(true),
          );
          controller.addOnPlatformViewCreatedListener(
            params.onPlatformViewCreated,
          );
          return controller;
        },
      ),
    );
  }
}
