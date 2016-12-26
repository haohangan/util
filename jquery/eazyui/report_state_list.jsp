<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/WEB-INF/hq.tld" prefix="hq" %>
<!DOCTYPE html>
<html>
<head>
<title>影城上报状态</title>
<jsp:include page="../inc.jsp"></jsp:include>

<script type="text/javascript">
	var dataGrid;
	var columns=[[]];
	var url = '${pageContext.request.contextPath}/statistics/report_state_list.do';
	var time = 0;
	$(function() {
		dataGrid = $('#dataGrid').datagrid({
			url : '',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'LOC_LOCATION_CD',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'pM_MOVIE_IN',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			frozenColumns : [ [ {
				field : 'LOC_LOCATION_CD',
				title : '编码',
				width : 100,
				sortable : false    
			},{
                field : 'LOC_NAME',
                title : '影院名称',
                width : 150,
                sortable : false                				
            },{
                field : 'NUM',
                title : '上报天数',
                width : 70,
                sortable : false                				
            },{
                field : 'HLCS_SORT_NAME',
                title : '区域',
                width : 100,
                sortable : false                				
            }
			] ],
			columns : columns,
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
					
				$(this).datagrid('tooltip');
			}
		});
	});
	
	function flush(){
		return $('#dataGrid').datagrid({
			url : url,
			fit : true,
			fitColumns : true,
			border : false,
			pagination : false,
			idField : 'LOC_LOCATION_CD',
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'pM_MOVIE_IN',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			striped : true,
			rownumbers : true,
			singleSelect : true,
			frozenColumns : [ [ {
				field : 'LOC_LOCATION_CD',
				title : '编码',
				width : 100,
				sortable : false    
			},{
                field : 'LOC_NAME',
                title : '影院名称',
                width : 150,
                sortable : false                				
            },{
                field : 'NUM',
                title : '上报天数',
                width : 70,
                sortable : false                				
            },{
                field : 'HLCS_SORT_NAME',
                title : '区域',
                width : 100,
                sortable : false                				
            }
			] ],
			columns : columns,
			toolbar : '#toolbar',
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
					
				$(this).datagrid('tooltip');
			}
		});
	}
	
	var templete = "{field : '$field',title : '$title',width : 50,sortable : false}";
	
	function month(){
		var array = $("#month").val().split("-");
		var year = array[0];
		var month = array[1]; 
		var mdate = new Date();
		mdate.setFullYear(year);
		mdate.setMonth(month-1);
		mdate.setDate(32);
		var days = 32-mdate.getDate();
		var i = 0;
		var str = '[[';
		for(i;i<days;i++){
			var title = '';
			if(i<9){
				title = '0'+(i+1);
			}else{
				title = i+1;
			}
			var field = year+'-'+month+'-'+title;
			
			var temp =  templete.replace("$field", field).replace("$title", title);
			str += temp;
			if(i !== (days-1)){
				str += ',';
			}
		}
		str += ']]';
		console.info(str);
		/* 
		var obj = $.parseJSON(JSON.stringify(str));//'[{ "name": "John" }]' */
		console.info(JSON.stringify(str)[0][0][title]);
	    /* $('#dataGrid').datagrid({
	        columns: [jQuery.parseJSON(str)]
	    }); */
	}
	
	function searchFun() {
		if(time===0){
			dataGrid.datagrid('options').url = url;
			time++;//只设置一次url
		}
		if($("#month").val()===null || $("#month").val()===''){
			$("#month")[0].focus(); 
			return;
		}
		month();
		/* dataGrid.datagrid('load', $.serializeObject($('#searchForm'))); */
	}
	
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	
</script>

</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 75px; overflow: hidden;">
			<form id="searchForm">
			<div class="query" >
				 <table>
                    <tr>
		               <td class="fb">所属区域</td>
		               <td><select id="area" name="area" class="easyui-combobox" data-options="width:192,height:29,panelHeight:'200',
								filter: function(q, row){
									return row.text.indexOf(q) != -1;
								}">
								<option value=""></option>
								<c:forEach items="${areas}" var="area">
									<option value="${area.HLCS_SORT_CD}">${area.HLCS_SORT_NAME}</option>
								</c:forEach>
						 	</select>
	                   </td>
	                   <td class="fb">营业月份</td>
		               <td><input onClick="WdatePicker({dateFmt:'yyyy-MM'})" type="text" style="width:180px;" name="month" id="month"></td>
                       <td class="fb">影院名称</td>
		               <td><%-- <select id="cinemaCode" name="cinemaCode" class="easyui-combobox" data-options="width:192,height:29,panelHeight:'200',
								filter: function(q, row){
									return row.text.indexOf(q) != -1;
								}">
								<option value=""></option>
								<c:forEach items="${locations}" var="cinema">
									<option value="${cinema.PM_MOVIE_CODE}">${cinema.PM_MOVIE_NAME}</option>
								</c:forEach>
						 	</select> --%></td>
                    </tr>	
				 </table>
			</div>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="dataGrid"></table>
		</div>
	</div>
   <div id="toolbar" style="display: none;">
		   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_add',plain:true" onclick="searchFun();">查询</a>
		   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'brick_delete',plain:true" onclick="cleanFun();">清空条件</a>
	</div>
</body>
</html>