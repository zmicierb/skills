package com.barysevich.project.utils;


import javax.annotation.Nullable;


public class StringUtils
{
    public static boolean hasText(String str)
    {
        if (!hasLength(str))
        {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }


    private static boolean hasLength(String str)
    {
        return (str != null && str.length() > 0);
    }


    /**
     * Приклеивает к месяцу 0, если месяц 1-9.
     *
     * @param month месяц
     * @return строковое представление месяца в формате MM
     */
    public static String normalizeMonth(final int month)
    {
        if (month < 1 || month > 12)
        {
            throw new IllegalArgumentException("month can not be: '" + month + "'");
        }

        if (month <= 9)
        {
            return String.format("0%s", month);
        }

        return String.valueOf(month);
    }


    @Nullable
    public static String trimOrNull(final String s)
    {
        return s != null ? s.trim() : null;
    }

}
