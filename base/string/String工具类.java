package com.bees.policysupport.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by guihao on 2020-08-19 11:06
 * 字符串处理的工具类
 **/
public class DssStrUtil {
    private DssStrUtil(){}


    /**
     * 常用的替换方法
     * @param src
     * @return
     */
    public static String strCoverUp(String src){
        return strCoverUp(src,3,4,'*');
    }

    /**
     * 将字符串的某一部分替换成另一种字符，起到掩盖原始字符的效果
     * @param src 原始字符串
     * @param start 替换的起始位置 从0开始
     * @param length 替换的长度
     * @param coverStr 替换的目标字符
     * @return
     */
    private static String strCoverUp(String src,int start,int length,char coverStr){
        if(StringUtils.isBlank(src)){
            return src;
        }
        int srcLen = src.length();
        if(start >= srcLen){//替换的起始位置超过字符串长度，则原样返回
            return src;
        }
        int end = start + length;
        if(end > srcLen){//替换的长度超出了原始字符串长度，相当于后缀全部替换
            end = srcLen;
        }
        StringBuilder result = new StringBuilder(srcLen);

        result.append(src.subSequence(0,start));
        for(int i = start;i < end;i++){
            result.append(coverStr);
        }
        if(end < srcLen){
            result.append(src.subSequence(end,srcLen));
        }
        return result.toString();
    }





}
