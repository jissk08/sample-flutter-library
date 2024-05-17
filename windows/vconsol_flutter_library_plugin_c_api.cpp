#include "include/vconsol_flutter_library/vconsol_flutter_library_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "vconsol_flutter_library_plugin.h"

void VconsolFlutterLibraryPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  vconsol_flutter_library::VconsolFlutterLibraryPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
