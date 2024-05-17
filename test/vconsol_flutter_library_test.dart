import 'package:flutter_test/flutter_test.dart';
import 'package:vconsol_flutter_library/vconsol_flutter_library.dart';
import 'package:vconsol_flutter_library/vconsol_flutter_library_platform_interface.dart';
import 'package:vconsol_flutter_library/vconsol_flutter_library_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockVconsolFlutterLibraryPlatform
    with MockPlatformInterfaceMixin
    implements VconsolFlutterLibraryPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final VconsolFlutterLibraryPlatform initialPlatform = VconsolFlutterLibraryPlatform.instance;

  test('$MethodChannelVconsolFlutterLibrary is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelVconsolFlutterLibrary>());
  });

  test('getPlatformVersion', () async {
    VconsolFlutterLibrary vconsolFlutterLibraryPlugin = VconsolFlutterLibrary();
    MockVconsolFlutterLibraryPlatform fakePlatform = MockVconsolFlutterLibraryPlatform();
    VconsolFlutterLibraryPlatform.instance = fakePlatform;

    expect(await vconsolFlutterLibraryPlugin.getPlatformVersion(), '42');
  });
}
