#ifndef FLUTTER_PLUGIN_VCONSOL_FLUTTER_LIBRARY_PLUGIN_H_
#define FLUTTER_PLUGIN_VCONSOL_FLUTTER_LIBRARY_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace vconsol_flutter_library {

class VconsolFlutterLibraryPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  VconsolFlutterLibraryPlugin();

  virtual ~VconsolFlutterLibraryPlugin();

  // Disallow copy and assign.
  VconsolFlutterLibraryPlugin(const VconsolFlutterLibraryPlugin&) = delete;
  VconsolFlutterLibraryPlugin& operator=(const VconsolFlutterLibraryPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace vconsol_flutter_library

#endif  // FLUTTER_PLUGIN_VCONSOL_FLUTTER_LIBRARY_PLUGIN_H_
