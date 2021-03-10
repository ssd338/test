<%--
  Class Name : EgovMainView.jsp
  Description : 메인화면
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31   JJY       경량환경 버전 생성

    author   : 실행환경개발팀 JJY
    since    : 2011.08.31
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import ="egovframework.com.cmm.LoginVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-language" content="ko">
<link href="<c:url value='/'/>css/default.css" rel="stylesheet" type="text/css" >
</head>
<body>
<!-- login status start -->
<div id="login_area"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncTborder" /></div>
<!-- //login status end -->
<!-- wrap start -->
<div id="wrap">
	<!-- header start -->
	<div id="header"><c:import url="/sym/mms/EgovMainMenuHead.do?flag=MAIN" /></div>
	<!-- //header end -->
	<!-- 타이틀이미지, 로그인 시작 -->
	<div id="titlewrap">
		<div class="main_login">
    		<%
               LoginVO loginVO = (LoginVO)session.getAttribute("LoginVO");
               if(loginVO != null){
            %>
    		<ul>
    		    <li><h3>[로그인정보 제공(예시)]</h3></li>
    		    <li><%= loginVO.getName()%>님 로그인하셨습니다.</li>
    		    <li>로그인 :2011-08-30 12:45 (예)</li>
    		    <li>받은 쪽지 : 3건 (예)</li>
    		</ul>
    		<%  } else { %>
            <!-- 메인화면 로그인위치를 사용하는 경우 -->
			<ul>
				<li>
					<input  type="text" class="input_style" maxlength="25" title="아이디를 입력하세요." id="user_id" name="user_id" />
				</li>
				<li>
					<input  type="password" class="input_style" maxlength="25" title="비밀번호를 입력하세요." id="user_password" name="user_password" />
				</li>
			</ul>
			<ul class="btn_area">
				<li><input disabled="disabled" type="checkbox" id="idsave"><label for="idsave">아이디 저장</label></li>
				<li><a href="<c:url value='/uat/uia/actionSecurityLogin.do'/>"><img alt="로그인" src="<c:url value='/'/>images/header/btn_login.gif" /></a></li>
			</ul>
			
	     <%  } %>
		</div>
	</div>
	<!-- //타이틀이미지, 로그인 끝 -->
	<div id="bodywrap">
		<div id="leftcontent_wrap">
			
		</div>
		<!-- 중간 영역 시작 -->
		<div id="middlecontent_wrap">
			
		</div>
		<!-- //중간 영역 끝 -->
		<div id="rightcontent_wrap">
			
	</div>
</div>
<!-- //wrap end -->
</body>
</html>
<%

%>