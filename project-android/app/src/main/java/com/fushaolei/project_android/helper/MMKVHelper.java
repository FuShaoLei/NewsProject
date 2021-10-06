package com.fushaolei.project_android.helper;

import com.tencent.mmkv.MMKV;

public class MMKVHelper {
    private static MMKV kv = MMKV.defaultMMKV();


    /**
     * 判断是否存在user id kv.decodeString("username") != null
     */
    public static boolean isUser() {
        return kv.contains("id") && kv.decodeInt("id") != 0;
    }

    /**
     * 存储user id
     */
    public static void saveUserId(int id) {
        kv.encode("id", id);
    }

    public static int getUserId() {
        return isUser() ? kv.decodeInt("id") : 0;
    }

    /**
     * 删除user id
     */
    public static void deleteUser() {
        if (kv.contains("id")) {
            kv.removeValueForKey("id");
        }
    }

    /**
     * 打印user id
     */
    public static void printUser() {
        if (kv.contains("id")) {
            BaseHelper.Log("kv中的user id为：" + kv.decodeInt("id"));
        } else {
            BaseHelper.Log("无user id信息");
        }
    }

    /**
     * 是否是黑夜模式
     */
    public static boolean isNightMod() {
        return kv.decodeBool("night", false);
    }

    /**
     * 设置黑夜模式
     */
    public static void setNightMod() {
        kv.encode("night", true);
    }

    /**
     * 设置日间模式
     */
    public static void setDayMod() {
        kv.encode("night", false);
    }
}
