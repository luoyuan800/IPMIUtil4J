/*
 * StringUtilis.java
 * Date: 7/14/2015
 * Time: 3:44 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package utils;

public class StringUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty() && str.trim().length() != 0;
    }
}
