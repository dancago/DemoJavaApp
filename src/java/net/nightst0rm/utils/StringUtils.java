/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.nightst0rm.utils;

/**
 *
 * @author buxuqua
 */
public class StringUtils {

    private static final String[] htmlChar = new String[]{"&", "<", ">", "'",
        "\""};
    private static final String[] htmlNameCode = new String[]{"&amp;",
        "&lt;", "&gt;", "&apos;", "&quot;"};

    public static String convertStringToHTMLCode(String strSource) {
        if (isNullOrEmpty(strSource)) {
            return "";
        }
        for (int i = 0; i < htmlChar.length; i++) {
            strSource = strSource.replaceAll(htmlChar[i], htmlNameCode[i]);
        }
        return strSource;
    }

    public static String convertHTMLCodeToString(String strSource) {
        if (isNullOrEmpty(strSource)) {
            return "";
        }
        for (int i = 0; i < htmlNameCode.length; i++) {
            strSource = strSource.replaceAll(htmlNameCode[i], htmlChar[i]);
        }
        return strSource;
    }

    public static boolean isNullOrEmpty(final String s) {
        return (s == null || s.trim().length() == 0);
    }

}
