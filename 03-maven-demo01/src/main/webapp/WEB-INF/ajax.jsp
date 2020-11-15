<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>您好Springboot</title>
<script src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	// 让js页面加载完成后，执行js
	$(function(){
		// $.get("/find",function(res){
		// 	for(let user of res){
		// 		// console.log(user);
		// 		let id=user.id;
		// 		let name=user.name;
		// 		let sex=user.sex;
		// 		let age=user.age;
		// 		 let  tr="<tr align='center'><td>"+id+"</td><td>"+name+"</td>"+age+"<td></td><td>"+sex+"</td></tr>";
		// 		   $("#t").append(tr);
				
		// 	}
		// }),
		// 原生的$.ajax
		  $.ajax({
			  type:"get",
			  data:"id=13&name=狗",
			  url:"/find",
			  success: (res) => {
			  	alert("请求成功");
				for(let user of res){
						// console.log(user);
						let id=user.id;
						let name=user.name;
						let sex=user.sex;
						let age=user.age;
						 let  tr="<tr align='center'><td>"+id+"</td><td>"+name+"</td>"+age+"<td></td><td>"+sex+"</td></tr>";
						   $("#t").append(tr);
						
					}
				                },
		      error: () => {
			 alert("请求失败");
		                   },
						   cache:false,//默认是true,网页数据缓存
						   async:false//默认是true，异步请求，false是同步请求
						   
	})
	
	});
</script>
</head>
<body>
	<table  id="t" border="1px" width="65%" align="center">
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th></th>
			
		</tr>
	</table>

</body>
</html>