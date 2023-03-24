package com.njj.blog.common.util.common.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 倪佳俊
 * @date 2023/2/19
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 依次替代字符串中的 {} 通配符
     *
     * @param originalString 原始字符串
     * @param strings 需要插入到originalString的字符串数组（可变长参数）
     * @return 处理后的originalString
     */
    public static String format(String originalString, String...strings){
        if(strings == null || strings.length == 0){
            return originalString;
        }
        for(String string : strings){
            originalString = StringUtils.replaceOnce(originalString,"{}",string);
        }
        return originalString;
    }

    /**
     * 分割字符串
     *
     * @param originalString 原始字符串
     * @param splitLength 分割长度
     * @return 返还被分割后的字符串集合
     */
    public static List<String>  splitString(String originalString, Integer splitLength){
        List<String> splitStrings = new ArrayList<String>();
        if(StringUtils.isEmpty(originalString) || splitLength == 0){
            splitStrings.add(originalString);
            return splitStrings;
        }
        int length = originalString.length();
        int index = splitLength;
        double pageNum = Math.ceil((double) length / splitLength);
        for (int i = 0; i < pageNum; i++) {
            String s = "";
            if(i == pageNum -1){
                s = originalString.substring(i * splitLength, length);
            }else{
                s = originalString.substring(i * splitLength, (i + 1) * splitLength);
            }
        splitStrings.add(s);
        }
        return splitStrings;
    }

    /**
     * 对字符串进行摘要，若原始字符串的长度小于摘要长度，则不对其进行摘要
     *
     * @param originString 原始字符串
     * @param summarySymbols 摘要符号
     * @param summaryLength 摘要长度
     * @return 返还摘要后的原始字符串
     */
    public static String summarizeString(String originString,int summaryLength,String summarySymbols){
        int originLength = originString.length();
        if(originLength > summaryLength){
            String summaryString = StringUtils.substring(originString, 0, summaryLength);
            return summaryString + summarySymbols;
        }
        return originString;
    }
}
