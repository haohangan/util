public class SqlParser {
    public static void main(String[] args) throws SQLSyntaxErrorException {
        String sql = "select  name ,id from acct where id =10";
        String dbType = "mysql";
        System.out.println("原始SQL 为 ： "+sql);
        SQLSelectStatement statement = (SQLSelectStatement) parser(sql, dbType);
        SQLSelect select = statement.getSelect();
        SQLSelectQueryBlock query = (SQLSelectQueryBlock) select.getQuery();
        SQLExprTableSource tableSource = (SQLExprTableSource) query.getFrom();
        String tableName = tableSource.getExpr().toString();
        System.out.println("获取的表名为  tableName ：" + tableName);
        //修改表名为acct_1
        tableSource.setExpr("acct_1");
        System.out.println("修改表名后的SQL 为 ： [" + statement.toString() +"]");
    }
    public static SQLStatement parser(String sql,String dbType) throws SQLSyntaxErrorException {
        List<SQLStatement> list = SQLUtils.parseStatements(sql, dbType);
        if (list.size() > 1) {
            throw new SQLSyntaxErrorException("MultiQueries is not supported,use single query instead ");
        }
        return list.get(0);
    }
}


public static void main(String[] args) {
        String sql = "select (select name from tab1 where id = 1 and age = ?) AS col1,col2,clo3 from tab2 where city_code in (?,?,?) and delete_flag = 0";
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
//        System.out.println(stmtList);
//        SQLStatement ss = stmtList.get(0);
//        System.out.println(ss);
        List<Object> params = new ArrayList<>();
        params.add(17);
        params.add("330000");
        params.add("330100");
        params.add("330200");

        StringBuilder out = new StringBuilder();
        ExportParameterVisitor visitor = new MySqlExportParameterVisitor(params, out, true);
        for (SQLStatement stmt : stmtList) {
            stmt.accept(visitor);
        }
        String paramteredSql = out.toString();
        System.out.println(paramteredSql);

        List<Object> parameters = visitor.getParameters();
        for (Object param : parameters) {
            System.out.println(param);
        }
    }
