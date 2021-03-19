 private static void tabRewrite(FromItem fromItem) {
        if (fromItem instanceof Table) {
            tableHandler((Table) fromItem);
        } else if (fromItem instanceof SubJoin) {

        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            SelectBody selectBody = subSelect.getSelectBody();
            selectBodyHandler(selectBody);
        } else if (fromItem == null) {
            //函数不处理，子查询按子查询返回
        } else {
            //其他情况不处理
        }

    }

    static void tableHandler(Table table) {
        String tabName = table.getName();
        if (table.getAlias() != null) {
            tabName = table.getAlias().getName();
        }
        System.out.println(tabName);
    }

    private static void columnRewrite(List<SelectItem> selectItems) {
        Iterator<SelectItem> iterator = selectItems.iterator();
        while (iterator.hasNext()) {
            SelectItem item = iterator.next();
            if (item instanceof AllColumns) {

            } else if (item instanceof AllTableColumns) {

            } else if (item instanceof SelectExpressionItem) {
                SelectExpressionItem sei = (SelectExpressionItem) item;
                Expression expression = sei.getExpression();
                if (expression instanceof Column) {
                    Column column = (Column) expression;
                    System.out.println(column.getColumnName() + "\t");
                    if ("uname".equals(column.getColumnName())) {
                        iterator.remove();//成功删除一列
                    }
                } else {
                    //不处理
                }
            } else {
                //不处理
            }
        }
    }
