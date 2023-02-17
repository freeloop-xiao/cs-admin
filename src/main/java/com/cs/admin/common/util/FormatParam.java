package com.cs.admin.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 格式化参数名
 *
 * @author : free loop
 * @since : 2019-07-06
 */
public class FormatParam {

    public static final String MOBILE_PATTERN = "[1][3,4,5,6,7,8,9][0-9]{9}";

    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");


    public static String toUnderline(String str) {
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转换成下划线参数对应表字段
     *
     * @param source 源参数
     * @return map
     */
    public static Map<Object, Object> toUnderline(Map<Object, Object> source) {
        Map<Object, Object> dest = new HashMap<>(8);
        for (Map.Entry<Object, Object> entry : source.entrySet()) {
            dest.put(toUnderline((String) entry.getKey()), entry.getValue());
        }
        return dest;
    }
}
