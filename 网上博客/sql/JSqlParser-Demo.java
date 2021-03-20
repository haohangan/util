package org.example.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;

import java.util.*;

/**
 * Created by guihao on 2021-03-19 10:55
 * 需要注意对子查询的处理，很多地方都可能出现子查询
 **/
public class SimpleDemo {

    static Map<String, TableFilterRule> map = new HashMap<>();

    public static void main(String[] args) throws JSQLParserException {
        map.put("tab1", new TableFilterRule("region_code", Arrays.asList("330000", "330100")));
        map.put("punish_base", new TableFilterRule("city_code", Arrays.asList("330000", "330100")));


        String originalSql1 = "SELECT city_code as cityCode,city_name as cityName,sum(simple_case_cnt) as simpleCaseCnt,sum(normal_case_cnt) as\n" +
                "        normalCaseCnt\n" +
                "        FROM punish_base\n" +
                "        WHERE stats_day=(select max(stats_day) from punish_base)\n" +
                "        and YEARWEEK(date_format(case_source_date,'%Y-%m-%d')) = YEARWEEK(now()) and delete_flag = 0 GROUP BY city_code;";
        String originalSql2 = "with u as (select id, sum(amount) as num from pay where pay_time >= 1493568000 and pay_time < 1494172800 group by id) select u.id, pinfo.sid, u.num from u join pinfo on u.id=pinfo.id;";

        String originalSql3 = "select a from tableb where c= 1";

        String originalSql4 = "SELECT * FROM t1 CROSS JOIN t2;";

        String originalSql5 = "SELECT id,name FROM t1 \n" +
                "UNION \n" +
                "SELECT uid AS id,uname AS name FROM t2;";


        String originalSql6 = "SELECT \n" +
                "id,`name`,id AS tid,\n" +
                "(SELECT MAX(id) FROM t1)\n" +
                " FROM t1\n" +
                " WHERE id > 0 AND id < 100\n" +
                " AND LENGTH(`name`) > 0\n" +
                " AND (SELECT MAX(id) FROM t1) > 0";

        String originalSql7 = "SELECT \n" +
                "id,`name`,id AS tid,\n" +
                "(SELECT MAX(id) FROM t1)\n" +
                " FROM t1\n" +
                " CROSS JOIN t2\n" +
                " WHERE id > 0 AND id < 100\n" +
                " AND LENGTH(`name`) > 0\n" +
                " AND (SELECT MAX(id) FROM t1) > 0";

        String originalSql8 = "select aa,b,c from tab1 left join tab2 on a = b where c = d";

        Statement statement = CCJSqlParserUtil.parse(originalSql1);
        if (statement instanceof Select) {
            Select select = (Select) statement;
            selectBodyHandler(select.getSelectBody());
            System.out.println("处理后的sql：" + select.getSelectBody().toString());
        } else {
            //非select语句不做处理
        }
    }

    /**
     * 处理selectBody
     * 处理两种情况：PlainSelectBody和SetOperationList
     *
     * @param selectBody
     */
    static void selectBodyHandler(SelectBody selectBody) throws JSQLParserException {
        if (selectBody instanceof PlainSelect) {
            plainSelectHandler((PlainSelect) selectBody);
        } else if (selectBody instanceof SetOperationList) {
            SetOperationList sol = (SetOperationList) selectBody;
            for (SelectBody sb : sol.getSelects()) {
                selectBodyHandler(sb);
            }
        } else {
            //其他情况，不做处理
        }
    }

    /**
     * 子查询处理
     *
     * @param subSelect 子查询
     */
    static void subSelectHandler(SubSelect subSelect) throws JSQLParserException {
        selectBodyHandler(subSelect.getSelectBody());
    }

    /**
     * 处理plainSelect
     *
     * @param plainSelect
     */
    static void plainSelectHandler(PlainSelect plainSelect) throws JSQLParserException {
//        List<SelectItem> selectItems = plainSelect.getSelectItems();
        FromItem fromItem = plainSelect.getFromItem();
//        List<Join> joins = plainSelect.getJoins();
        Expression whereExpression = plainSelect.getWhere();
        //按照查询类型，处理sql
        if (fromItem instanceof Table) {//查询表
            finalSelectHandler(plainSelect);
        } else if (fromItem instanceof SubSelect) {//子查询
            subSelectHandler((SubSelect) fromItem);
        } else if (fromItem == null) {//无from，单独处理查询列
            handlerSelectItems(plainSelect);
        } else {
            //不处理
        }
    }

    /**
     * 处理查询列中的子查询
     *
     * @param plainSelect
     */
    private static void handlerSelectItems(PlainSelect plainSelect) throws JSQLParserException {
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        for (SelectItem selectItem : selectItems) {
            if (selectItem instanceof SelectExpressionItem) {
                Expression expression = ((SelectExpressionItem) selectItem).getExpression();
                if (expression instanceof SubSelect) {//处理子查询
                    subSelectHandler((SubSelect) expression);
                } else if (expression instanceof Function) { //Function函数不处理
                } else {
                    //不处理
                }
            }
        }
    }

    /**
     * 最终处理方法
     *
     * @param plainSelect
     */
    private static void finalSelectHandler(PlainSelect plainSelect) throws JSQLParserException {
        List<Expression> addWhereExpr = new ArrayList<>();

        List<Join> joins = plainSelect.getJoins();//处理join
        if (joins != null && !joins.isEmpty()) {
            for (Join join : joins) {
                FromItem rightItem = join.getRightItem();
                if (rightItem instanceof Table) {
                    Table rightTable = (Table) rightItem;//表名
                    String rightAlias = rightTable.getAlias() != null ? rightTable.getAlias().getName() : rightTable.getName();//别名

                } else if (rightItem instanceof SubSelect) {
                    subSelectHandler((SubSelect) rightItem);
                } else {
                    //不处理
                }
            }
        }

        //处理主表
        FromItem mainFrom = plainSelect.getFromItem();
        Table table = (Table) mainFrom;//表名
        String alias = table.getAlias() != null ? table.getAlias().getName() : table.getName();//别名
        if (map.get(table.getName()) != null) {
            Expression where = plainSelect.getWhere();
            Expression expr = CCJSqlParserUtil.parseCondExpression(map.get(table.getName()).toExpressionStr(alias));
            if (where == null) {
                where = expr;//为空则直接替换
            } else {
                where = new AndExpression(where, expr);//将新的过来条件拼接上去
            }
            plainSelect.setWhere(where);
        }
    }


    static Expression createExpr(Table table) throws JSQLParserException {
        String alias = table.getAlias() != null ? table.getAlias().getName() : table.getName();//别名
        if (map.get(table.getName()) != null) {
            Expression expr = CCJSqlParserUtil.parseCondExpression(map.get(table.getName()).toExpressionStr(alias));
            return expr;
        }
        return null;
    }

    /**
     * 获取表的别名
     *
     * @param fromItem table
     * @return
     */
    static String getAlias(FromItem fromItem) {
        Table table = (Table) fromItem;//表名
        String alias = table.getAlias() != null ? table.getAlias().getName() : table.getName();//别名
        return alias;
    }

}
