

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by  on 2020-09-15 16:00
 * 正则表达式工具类
 **/
public class RegexUtils {
    private RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * "(?<=')(.{7,16})(?=')"
     * 截取以某个字符开始并且以某个字符串结束的并且长度为（minLength,maxLength）之间的字符串
     *
     * @param src       源字符串
     * @param start     起始字符
     * @param end       终止字符
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return
     */
    public static List<String> interceptString(String src, char start, char end, int minLength, int maxLength) {
        StringBuilder sb = new StringBuilder();
        sb.append("(?<=").append(start).append(")(.{").append(minLength).append(",").append(maxLength).append("})(?=").append(end).append(")");
        Pattern p = Pattern.compile(sb.toString());
        // 获取完整的域名
        Matcher matcher = p.matcher(src);
        List<String> matchStr = new ArrayList<>();
        while (matcher.find()) {
            String matchedStr = matcher.group();
            matchStr.add(matchedStr);
        }
        return matchStr;
    }


    /**
     * 截取单引号之间长度不超过length的字符串
     *
     * @param src
     * @param length
     * @return
     */
    public static List<String> quotesStr(String src, int length) {
        char start = '\'';
        char end = '\'';
        List<String> result = interceptString(src, start, end, 1, length);
        return result;
    }
}
