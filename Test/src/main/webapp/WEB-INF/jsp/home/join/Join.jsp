<%--
  Class Name : EgovMberSbscrb.jsp
  Description : 일반회원가입신청 JSP  
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.03.22  JJY          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 공통서비스 개발팀 JJY
    since    : 2009.03.22
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#main-content{
		align-content: center;
		margin: 120px 0 0 80px;
	}
	#title{
		border-left: 8px solid;	
	}
	.type{
		display: block;
		float: left;
		margin: 80px 2px 0 10px;
		border: 1px solid;
		border-color: gray;
		padding: 40px;
	}
	#content_field1{
		margin-left: 100px;
	}
	.im{
		clear:both;
		display: block;
	}
	.joinBtn{
	
		padding: 10px;
		margin-left: 55px;
	}
	#joinBtn1{
		background-color: #4374D9;
		border-radius: 3px;
		color: white;
	}
	#joinBtn2{
		background-color: #FFA648;
		border-radius: 3px;
		color: white;
	}

</style>
<link rel="stylesheet" href="<c:url value='/'/>css/default.css" type="text/css" >
<title>회원 가입</title>
</head>
<body>

<!-- wrap start -->
<div id="wrap"> 
   <div id="main-content">
	   <div id="title"><h2>회원가입</h2></div>
	    <div id="bodywrap">
	    	<div id="middle_content">
				<div id="content_field1" class="type"><!--contents start-->
				<img alt="일반회원가입" src="/images/home/Nuser.png" class="im">
				<a href="/home/join/JoinMberRegistView.do"> <button class="joinBtn" id="joinBtn1">일반 회원가입</button></a>
				</div>
				<div id="content_field2" class="type">
				<img  alt="기업회원가입" src="/images/home/Guser.png" class="im">
				<a href="#"> <button class="joinBtn" id="joinBtn2">기업 회원가입</button></a>
	            </div><!-- contents end -->
	        </div>
	    </div>
    </div>
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //wrap end -->

</body>
</html>