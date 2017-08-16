package com.sun.clean.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @authur sunjian.
 */
public class CheckUtils
{
    /**
     * 校验集合中每个对象的每个字段是否为空
     *
     * @param checkList 校验的集合
     * @return 有空的对象的集合, 若是没有空的返回null
     */
    public static<T> List<T> checkListEmpty(List<T> checkList) throws IllegalAccessException
    {
        List<T> emptyList = new ArrayList<>();
        for (T object : checkList)
        {
            boolean empty = checkObjectEmpty(object);
            if (empty)
            {
                //含有空对象,添加到返回的集合中
                emptyList.add(object);
            }
        }
        return emptyList.size() == 0 ? null : emptyList;
    }

    /**
     * 校验对象中的每个字段是否为空
     *
     * @param object 校验的对象(不包括集合类的)
     * @return 有空返回true, 不为空返回false
     * @throws IllegalAccessException
     */
    private static<T> boolean checkObjectEmpty(T object) throws IllegalAccessException
    {
        boolean empty = true;
        for (Field f : object.getClass().getDeclaredFields())
        {
            //设置为允许访问
            f.setAccessible(true);
            if (f.get(object) != null)
            {
                empty = false;
            } else
            {
                return true;
            }
        }
        return empty;
    }
}
