import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'vconsol_flutter_library_platform_interface.dart';

/// An implementation of [VconsolFlutterLibraryPlatform] that uses method channels.
class MethodChannelVconsolFlutterLibrary extends VconsolFlutterLibraryPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('vconsol_flutter_library');

  @override
  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  joinMeeting(Map<String, dynamic> meetingParams) {
    methodChannel.invokeMethod('joinMeeting',meetingParams);
  }
}
