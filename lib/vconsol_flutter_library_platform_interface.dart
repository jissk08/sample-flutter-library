import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'vconsol_flutter_library_method_channel.dart';

abstract class VconsolFlutterLibraryPlatform extends PlatformInterface {
  /// Constructs a VconsolFlutterLibraryPlatform.
  VconsolFlutterLibraryPlatform() : super(token: _token);

  static final Object _token = Object();

  static VconsolFlutterLibraryPlatform _instance = MethodChannelVconsolFlutterLibrary();

  /// The default instance of [VconsolFlutterLibraryPlatform] to use.
  ///
  /// Defaults to [MethodChannelVconsolFlutterLibrary].
  static VconsolFlutterLibraryPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [VconsolFlutterLibraryPlatform] when
  /// they register themselves.
  static set instance(VconsolFlutterLibraryPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  joinMeeting(Map<String, dynamic> meetingParams){
    throw UnimplementedError('joinMeeting() has not been implemented.');
  }
}
