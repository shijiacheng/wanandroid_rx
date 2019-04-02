package com.shijc.wanandroidrx.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.utils
 * @Description:
 * @date 2019/4/2 上午 9:20
 */
public class CollectionUtils {

    /**
     * 判断数组是否为NULL或者为空
     * @param objs	需要判断的对象数组
     * @return		如果被检测的对象数组为NULL或者为空则返回true，否则返回false
     */
    public static boolean isEmpty(final Object[] objs){
        return objs == null || objs.length <= 0;
    }

    /**
     * 判断列表容器是否为NULL或者没有元素
     * @param list	需要判断的列表容器
     * @return		如果被检测的列表容器为NULL或者为空，则返回true，否则返回false
     */
    public static <E> boolean isEmpty(final List<E> list){
        return list == null || list.size() <= 0;
    }

    /**
     * 判断Map容器是否为NULL或者没有元素
     * @param <K, V>
     * @param map	需要判断的Map容器
     * @return		如果被检测的Map为NULL或者为空，则返回true，否则返回false
     */
    public static <K, V> boolean isEmpty(final Map<K, V> map){
        return map == null || map.size() <= 0;
    }

    /**
     * 判断Map容器是否为NULL或者没有元素
     * @param map	需要判断的Map容器
     * @return		如果被检测的Map为NULL或者为空，则返回true，否则返回false
     */
    public static boolean isEmpty(final LinkedHashMap<String, String> map){
        return map == null || map.size() <= 0;
    }


}
