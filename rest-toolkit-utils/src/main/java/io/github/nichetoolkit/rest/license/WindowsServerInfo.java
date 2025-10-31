package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.error.license.LicenseErrorException;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * <code>WindowsServerInfo</code>
 * <p>The windows server info class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.license.LicenseServerInfo
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class WindowsServerInfo extends LicenseServerInfo {

    @Override
    protected String getCpuSerial() throws LicenseErrorException {
        StringBuilder serial = new StringBuilder();
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                serial.append(line);
            }
            input.close();
            file.delete();
        } catch (Exception exception) {
            log.error("It is failed to obtain windows server cpu serial information, error: {}",exception.getMessage(), exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_CPU_ERROR, "cpu serial", exception);
        }
        return serial.toString().trim();
    }

    @Override
    protected String getBoardSerial() throws LicenseErrorException {
        StringBuilder serial = new StringBuilder();
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n" + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n" + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process process = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                serial.append(line);
            }
            input.close();
        } catch (Exception exception) {
            log.error("It is failed to obtain windows server board serial information, error: {}", exception.getMessage(), exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_BOARD_ERROR, "board serial", exception);
        }
        return serial.toString().trim();
    }




}
