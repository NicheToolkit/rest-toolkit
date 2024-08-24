package io.github.nichetoolkit.rest.util;


import java.util.HashMap;
import java.util.Map;


/**
 * <p>NameUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class NameUtils {
    public static final String UUID = "uuid";
    public static final String NAME = "name";
    public static final String EXT = "ext";

    public static Map<String, String> parseFileName(String fileNameString) {
        Map<String, String> fileNameMap = new HashMap<>();
        String[] result = new String[2];
        if (fileNameString.contains(".")) {
            String[] stringTemp = fileNameString.split("\\.", -1);
            /* 最后一个必是扩展名 */
            result[1] = stringTemp[stringTemp.length - 1];
            if (stringTemp.length > 2) {
                StringBuilder nameBuffer = new StringBuilder();
                for (int i = 0; i < stringTemp.length - 1; i++) {
                    nameBuffer.append(".").append(stringTemp[i]);
                }
                result[0] = nameBuffer.substring(1);
            } else {
                result[0] = stringTemp[0];
            }
        } else {
            result[0] = fileNameString;
            result[1] = null;
        }
        fileNameMap.put(NAME, result[0]);
        fileNameMap.put(EXT, result[1]);
        return fileNameMap;
    }

    public static String buildFileName(String name, String ext) {
        return name + "." + ext;
    }

    public static String buildFileName(Map<String, String> fileNameMap) {
        String name = fileNameMap.get(NAME);
        String ext = fileNameMap.get(EXT);
        return buildFileName(name, ext);
    }

    @SuppressWarnings("UnnecessaryParentheses")
    public static Map<String, String> parseAliasName(String fileNameString) {
        Map<String, String> aliasNameMap = new HashMap<>();
        Map<String, String> nameMap = parseFileName(fileNameString);
        String fileName = nameMap.get(NAME);
        String[] result = new String[2];
        if (fileName.contains("_")) {
            String[] stringTemp = fileName.split("_", -1);
            /* 第一个必是也必须是ID */
            result[0] = stringTemp[0];
            if (stringTemp.length > 2) {
                StringBuilder nameBuffer = new StringBuilder();
                for (int i = 1; i < stringTemp.length; i++) {
                    nameBuffer.append("_").append(stringTemp[i]);
                }
                result[1] = nameBuffer.substring(1);
            } else {
                result[1] = stringTemp[1];
            }
        } else {
            result[0] = null;
            result[1] = fileName;

        }
        aliasNameMap.put(UUID, result[0]);
        aliasNameMap.put(NAME, result[1]);
        aliasNameMap.put(EXT, nameMap.get(EXT));
        return aliasNameMap;
    }

    public static String buildAliasName(String name, String ext) {
        String uuid = java.util.UUID.randomUUID().toString().replaceAll("-","");
        return uuid + "_" + name + "." + ext;
    }

    public static Map<String, String> parseSliceName(String sliceNameString) {
        Map<String, String> sliceMap = new HashMap<>();
        String[] result = new String[2];
        if (sliceNameString.contains("_")) {
            String[] stringTemp = sliceNameString.split("_", -1);
            /* 最后一个必是也必须是uuid */
            result[1] = stringTemp[stringTemp.length - 1];
            if (stringTemp.length > 2) {
                StringBuilder nameBuffer = new StringBuilder();
                for (int i = 0; i < stringTemp.length - 1; i++) {
                    nameBuffer.append("_").append(stringTemp[i]);
                }
                result[0] = nameBuffer.substring(1);
            } else {
                result[0] = stringTemp[0];
                result[1] = null;
            }
        }
        sliceMap.put(UUID, result[0]);
        sliceMap.put(NAME, result[1]);
        return sliceMap;
    }

    public static String parseCamelName(String baseString) {
        StringBuilder caseBuilder = new StringBuilder();
        boolean isUpper = false;
        for (int i = 0; i < baseString.length(); i++) {
            char character = baseString.charAt(i);
            switch (character) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    isUpper = caseBuilder.length() > 0;
                    break;
                default:
                    if (isUpper) {
                        character = Character.toUpperCase(character);
                        isUpper = false;
                    }
                    caseBuilder.append(character);
                    break;
            }
        }
        caseBuilder.setCharAt(0, Character.toUpperCase(caseBuilder.charAt(0)));
        return caseBuilder.toString();
    }


}
