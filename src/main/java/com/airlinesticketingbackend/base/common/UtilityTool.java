package com.airlinesticketingbackend.base.common;

import com.airlinesticketingbackend.base.exception.ProvideExceptionHandler;
import org.apache.commons.lang3.StringUtils;

public class UtilityTool {

    public static String splintRedundantExpression(String value, String regex) {
        String[] split = value.split(regex, 4);

        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            sb.append(s);
        }

        return sb.toString();
    }

    public static String maskRequestParam(String text, int start, int end, char maskChar)
            throws ProvideExceptionHandler {

        if (text == null || text.equals(""))
            return "";

        if (start < 0)
            start = 0;

        if (end > text.length())
            end = text.length();

        if (start > end)
            throw new ProvideExceptionHandler("End index must not be greater than start index");

        int maskLength = end - start;
        if (maskLength == 0)
            return text;

        String strMaskString = StringUtils.repeat(maskChar, maskLength);

        return StringUtils.overlay(text, strMaskString, start, end);
    }

}
