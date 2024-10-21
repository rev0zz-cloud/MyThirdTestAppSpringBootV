package ru.arkhipov.MyThirdTestAppSpringBoot.util;

import java.text.SimpleDateFormat;

public class DateTimeUtil {
    public static SimpleDateFormat getCustomFormat() {
        return new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss.SSS'Z'");
    }
}
