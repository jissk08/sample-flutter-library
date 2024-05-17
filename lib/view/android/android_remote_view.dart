import 'package:flutter/cupertino.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';

class AndroidRemoteView extends StatefulWidget {
  const AndroidRemoteView({super.key});

  @override
  State<AndroidRemoteView> createState() => _AndroidRemoteViewState();
}

class _AndroidRemoteViewState extends State<AndroidRemoteView> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: PlatformViewLink(
        viewType: "RemoteVideoView",
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
            viewType: "RemoteVideoView",
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
