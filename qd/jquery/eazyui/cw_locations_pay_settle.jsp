<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="easyui-layout" data-options="fit:true,border:false">
<script type="text/javascript">
    var CLP_CINEMA_CODE = '${clsvo.CLP_CINEMA_CODE}';
    var CLP_SHOW_DATE = '${clsvo.CLP_SHOW_DATE}';
	$(function() {
		$.messager.progress('close');
	});
	
	function deleteSettle(id,type){
		/* alert('id:'+id+' type:'+type); */
		if(id==null || id == ''){
			$.messager.alert('提示', '流水号为空，无法删除', 'info');
			return;
		}
		var paytype;
		/* if(type=='系统录入'){
			paytype = 'S';
		}else if(type=='银行流水'){
			paytype = 'B';
		}else{
			$.messager.alert('提示', '流水类型无法解释，联系管理员！', 'info');
			return;
		} */
		if(type=='S'){
			paytype = '系统录入';
		}else if(type=='B'){
			paytype = '银行流水';
		}else{
			$.messager.alert('提示', '流水类型无法解释，联系管理员！', 'info');
			return;
		} 
		$.messager.confirm('询问', '您是否要删除当前选中的：'+paytype+' 流水号：'+id, function(b) {
			if (b) {
				$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				
				$.getJSON('${pageContext.request.contextPath}/finamgr/cw_locations_pay_delete.do', {
					paytype : type,
					id : id,
					CLP_CINEMA_CODE:CLP_CINEMA_CODE,
					CLP_SHOW_DATE:CLP_SHOW_DATE
				}, function(result) {
					if (result.success) {
						$('#settleGrid').datagrid('loadData',{total:0,rows:[]});
						$('#settleGrid').datagrid('loadData',result.obj);
					}
					$.messager.alert('提示', result.msg, 'info');
					$.messager.progress('close');
				});
	       }});
	}
	
	function formatDelete(val,row){
		var str = '';
	    if(row.cINEMA_NAME!='合计'){
	    	/* str += $.formatString('<a style="padding-left:16px;background:url({2}) no-repeat 0 -1px" onclick="deleteSettle(\'{0}\',\'{1}\');">删除</a>', row.fLOW_ID,row.pAYTYPE, '${pageContext.request.contextPath}/style/images/extjs_icons/delete.png'); */
	        var url = '${pageContext.request.contextPath}/style/images/extjs_icons/delete.png';
	        var type = row.pAYTYPE;
	        var paytype;
	        if(type=='系统录入'){
				paytype = "'S'";
			}else if(type=='银行流水'){
				paytype = "'B'";
			}else{
			}
	    	str = str + '<a href="javascript:void(0);" style="padding-left:16px;background:url('+url+') no-repeat 0 -1px" onclick="deleteSettle('+row.fLOW_ID+','+paytype+');">删除</a>' ;
	    }
	    return str;
	}
	
</script>
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form"></form>
		<table id="settleGrid" class="easyui-datagrid" style="width:430px;height:700px"
        data-options="fitColumns:true,singleSelect:true,showFooter:true,
		        rowStyler: function(index,row){
				if (row.cINEMA_NAME == '合计'){
					return 'font-weight:bold;';
				}}">
			<thead>
				<tr>
				    <th data-options="field:'fLOW_ID',width:100" formatter="formatDelete">操作</th>
					<th data-options="field:'cINEMA_NAME',width:100">影城名称</th>
					<th data-options="field:'cMC_MONTH',width:100">收款月份</th>
					<th data-options="field:'pAY_TIME',width:100">收款日期</th>
					<th data-options="field:'tOTAL',width:100">金额</th>
					<th data-options="field:'pAYTYPE',width:100">来源</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="pro" varStatus="status">
				<tr>
				    <td>
				      <%-- <c:if test="${pro.CINEMA_NAME!='合计' }">
				        <a onclick="deleteSettle('${pro.FLOW_ID}','${pro.PAYTYPE}');" href="javascript:void(0);" >刪除</a>
				      </c:if> --%>
				      ${pro.FLOW_ID}
				    </td>
					<td>${pro.CINEMA_NAME}</td>
					<td>${pro.CMC_MONTH}</td>
					<td>${pro.PAY_TIME}</td>
					<td>${pro.TOTAL}</td>
					<td>${pro.PAYTYPE}</td>
				</tr>
			</c:forEach>	
			</tbody>
		</table>
	</div>
</div>

