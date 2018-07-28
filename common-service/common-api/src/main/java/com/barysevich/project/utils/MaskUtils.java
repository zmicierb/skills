package com.barysevich.project.utils;

public class MaskUtils {

    private static final String MASK = "***";

    public static String mask(final String str)
    {
        return StringUtils.hasText(str) ? MASK : str;
    }


    public static String maskAllBefore4LastChars(final String str)
    {
        if (!StringUtils.hasText(str))
        {
            return str;
        }

        if (str.length() <= 4)
        {
            return mask(str);
        }

        return MASK + getLast4Chars(str, str.length());
    }


    private static String getLast4Chars(final String str, final int length)
    {
        return str.substring(length - 4, length);
    }
}
