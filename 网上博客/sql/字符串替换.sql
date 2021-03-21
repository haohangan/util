package org.example.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo1 {

    public static void main(String[] args) throws JSQLParserException {
//        String sql1 = "SELECT a,b,? FROM tab1 where a = ?";
//        List<Integer> list = findQuestionMark(sql1);
//        StringBuilder sb = new StringBuilder(sql1);
//        for (int i  = 0;i< list.size();i++) {
//            sb.replace(list.get(i),list.get(i) + 1 + i,"?"+i);
//        }
//        System.out.println(sb.toString());

        //反向替换
        String sql2 = "SELECT a,b,?0 FROM tab1 where a = ?1 and b = ?100";
        List<ReplaceToken> list2 = findQuestionMarkNum(sql2);
        StringBuilder sb2 = new StringBuilder(sql2);
        int cut = 0;
        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.get(i));
            ReplaceToken rt = list2.get(i);
            sb2.replace(rt.getStart() - cut,rt.getEnd() - cut,"?");
            cut = cut + rt.getToken().length() - 1;
        }
        System.out.println(sb2.toString());

        //重写sql
//        String sql2 = "VALUES ROW(1,-2,3), ROW(5,7,9), ROW(4,6,8);";
//        Statement stmt = CCJSqlParserUtil.parse(sql1);
//        MyStatementVisitor visitor = new MyStatementVisitor();
//        stmt.accept(visitor);
    }

    static void demo() {
        String str = "*";
        StringBuilder sb = new StringBuilder("18698587234");
        sb.replace(3, 4, str);
        System.err.println("========" + sb.toString());
    }


    /**
     * 找到所有问号的位置
     *
     * @param text
     */
    static List<Integer> findQuestionMark(String text) {
        List<Integer> list = new ArrayList<>();
        Pattern p = Pattern.compile("\\?");
        Matcher findMatcher = p.matcher(text);
        while (findMatcher.find()) {
            list.add(findMatcher.start());
        }
        return list;
    }

    /**
     * 找到所有的位置
     *
     * @param text
     */
    static List<ReplaceToken> findQuestionMarkNum(String text) {
        List<ReplaceToken> list = new ArrayList<>();
        Pattern p = Pattern.compile("\\?[0-9]+");
        Matcher findMatcher = p.matcher(text);
        while (findMatcher.find()) {
            ReplaceToken rt = new ReplaceToken(findMatcher.group(), findMatcher.start(), findMatcher.end());
            list.add(rt);
        }
        return list;
    }
}
