package io.github.nichetoolkit.rest.license;

import io.github.nichetoolkit.rest.error.license.LicenseErrorException;
import io.github.nichetoolkit.rest.error.license.LicenseErrorStatus;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <code>LinuxServerInfo</code>
 * <p>The linux server info class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.license.LicenseServerInfo
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk1.8
 */
@Slf4j
public class LinuxServerInfo extends LicenseServerInfo {

    @Override
    public String localCpuSerial() throws LicenseErrorException {
        String serial = "";
        String CPU_ID_CMD = "dmidecode -t 4 | grep ID -m 1";
        BufferedReader bufferedReader;
        Process process;
        try {
            process = Runtime.getRuntime().exec(new String[]{"sh", "-c", CPU_ID_CMD});
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            int index;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("id");
                if (index >= 0) {
                    serial = line.substring(index + "id".length() + 1).trim();
                    break;
                }
            }
        } catch (Exception exception) {
            log.error("It is failed to obtain linux server cpu serial information, error: {}", exception.getMessage(), exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_CPU_ERROR, "cpu serial", exception);
        }
        return serial.trim();
    }

    @Override
    public String localBoardSerial() throws LicenseErrorException {
        StringBuilder serial = new StringBuilder();
        String maniBord_cmd = "dmidecode -s baseboard-serial-number";
        Process process;
        try {
            process = Runtime.getRuntime().exec(new String[]{"sh", "-c", maniBord_cmd});
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                serial.append(line);
            }
            bufferedReader.close();
        } catch (Exception exception) {
            log.error("It is failed to obtain linux server board serial information, error: {}", exception.getMessage(), exception);
            throw new LicenseErrorException(LicenseErrorStatus.LICENSE_SERVER_BOARD_ERROR, "board serial", exception);
        }
        return serial.toString();
    }


}

