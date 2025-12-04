import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegistryExample {
    public static void main(String[] args) {
        // ❌ Reads a registry value from Windows Registry
        String installPath = Advapi32Util.registryGetStringValue(
            WinReg.HKEY_LOCAL_MACHINE,
            "SOFTWARE\\MyApp",
            "InstallPath"
        );

        System.out.println("Install path: " + installPath);

        // ❌ Writes a registry value
        Advapi32Util.registrySetStringValue(
            WinReg.HKEY_CURRENT_USER,
            "SOFTWARE\\MyApp",
            "LastRun",
            "2025-05-28"
        );
    }
}
