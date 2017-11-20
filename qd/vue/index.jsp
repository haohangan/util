<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<!-- <script src="https://unpkg.com/vue"></script> -->
<script src="./static/vue.js"></script> 
</head>
<body>
		<h1>------demo---------</h1>
		<table border="1">
			<thead>
				<tr>
					<td>名称</td>
					<td>效果</td>
					<td>说明</td>				
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>demo1</td>
					<td>
					<div id="demo1">
						{{demosmg}}  --- {{demo2msg}}
					</div>
					</td>
					<td>展示信息</td>
				</tr>
				
				
				<tr>
					<td>demo3</td>
					<td>
					<div id="demo3">
						<span v-bind:title="demo3_msg">
							abcdefg
						</span>
					</div>
					</td>
					<td>v-bind 属性被称为指令</td>
				</tr>
				
				
				<tr>
					<td>demo4</td>
					<td>
						<div id="demo4">
							<p v-if="seen">看得见的</p>
							<p v-if="visible">看不见的</p>
							<p v-show="visible">看不见的</p>
						</div>
					</td>
					<td>v-if指令，条件指令 v-show:display</td>
				</tr>
				
				<!-- <tr>
					<td>dmo6</td>
					<td>
						<div id="demo6">
							<p>{{txt}}</p>
							  <ol>
							    <li v-for="todo in todos">
							      {{ todo.text }}
							    </li>
							  </ol>
						</div>
					</td>
					<td>绑定数组的数据来渲染</td>
				</tr> -->
				
				<tr>
					<td>demo7</td>
					<td>
						<div id="demo7">
							{{txt}}
							<ol>
								<li v-for="todo in todos">
									{{todo.text}}
								</li>
							</ol>
						</div>
					</td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				
			</tbody>
		</table>
		<script type="text/javascript">
				var demo1 = new Vue({
					el:"#demo1",
					data:{
						demosmg:"hello demo1",
						demo2msg:"dmo2"
					}
				});
				
				
				var demo3 = new Vue({
					el:"#demo3",
					data:{
						demo3_msg:"加载于 hello demo3 "+new Date().toLocaleString(),
					}
				});
				
				
				var demo4 = new Vue({
					el:"#demo4",
					data:{
						seen:true,
						visible:false
					}
				});
				
				//demo5,动态修改属性后的效果
				setTimeout(function(){
					demo4.seen = false;
				},1000);
				
				setInterval(() => {
					if(demo4.seen){
						demo4.seen = false;
					}else{
						demo4.seen = true;
					}
					demo7.todos.push({text:"new language"});
				}, 5000);
				
				
				/* var demo5 = new Vue({
					el:"#demo6",
					date:{
						txt:"aaaaaaa",
						todos:[
						      { text: '学习 JavaScript' },
						      { text: '学习 Vue' },
						      { text: '整个牛项目' }
						    ]
					}
				}); */
				
				var demo7 = new Vue({
					el:"#demo7",
					data:{
						txt:"asadasd",
						todos:[
							{text:"JavaScript"},
							{text:"typeScript"},
							{text:"EScript"}
						]
					}
				});
		</script>
</body>
</html>