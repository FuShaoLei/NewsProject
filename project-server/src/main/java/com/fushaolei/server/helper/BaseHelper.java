package com.fushaolei.server.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseHelper {
    /**
     * 以时间戳命名文件名
     *
     * @param text
     * @return
     */
    public static String getFileName(String text) {
        return System.currentTimeMillis() + getFileSuffix(text);
    }

    /**
     * 获取文件的后缀名
     *
     * @param text
     * @return
     */
    public static String getFileSuffix(String text) {
        text = text.split("\\.")[1];
        text = "." + text;
        return text;
    }

    /**
     * 获取格式如 2021-02-06 的字符串
     * @return
     */
    public static String getDateTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }
}
