import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:vconsol_flutter_library/vconsol_flutter_library_method_channel.dart';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  MethodChannelVconsolFlutterLibrary platform = MethodChannelVconsolFlutterLibrary();
  const MethodChannel channel = MethodChannel('vconsol_flutter_library');

  setUp(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(
      channel,
      (MethodCall methodCall) async {
        return '42';
      },
    );
  });

  tearDown(() {
    TestDefaultBinaryMessengerBinding.instance.defaultBinaryMessenger.setMockMethodCallHandler(channel, null);
  });

  test('getPlatformVersion', () async {
    expect(await platform.getPlatformVersion(), '42');
  });
}
