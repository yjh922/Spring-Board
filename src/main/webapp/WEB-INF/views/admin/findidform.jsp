<%@page import="org.sp.board.domain.Admin"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
//System.out.println(request.getAttribute("dto"));
	Admin dto=(Admin) request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<%@ include file="./inc/head_link.jsp" %>
<script type="text/javascript">
function findId(){
	$("form").attr({
		action:"/admin/findId",
		method:"post"
	});
	$("form").submit();
}
$(function(){
	$("#bt_findid").click(function(){
		//console.log("클릭");
		findId();
	});
	$("#bt_login").click(function(){
		location.href="/admin/loginform";
	});
});

</script>
</head>
<body>

<h3>아이디 찾기</h3>

<div class="container">
  <form>
    <label for="fname">이름</label>
    <input type="text" name="name" placeholder="이름 입력">

    <label for="lname">전화번호</label>
    <input type="text" name="phone" placeholder="전화번호 입력">

    <input type="button" value="아이디 찾기" id="bt_findid">
    
    <%if(dto==null){ %>
    	<input type="text"  placeholder="일치하는 아이디 없습니다.">
    <%}else{ %>
    	<input type="text"  value="<%=dto.getId()%>">
    
	<%} %>
	<input type="button" value="로그인" id="bt_login">
  </form>
 
</div>

</body>
</html>

