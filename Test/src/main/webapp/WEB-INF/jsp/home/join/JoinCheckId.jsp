<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#checkForm{
		margin:80px 0 0 40px;
	}
	#checkIdD{
		background-color: gary;
		border: solid 1px;
	}
</style>
<title>Id 중복체크</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-language" content="ko">
<base target="_self" >
<link rel="stylesheet" href="<c:url value='/css/default.css'/>" type="text/css">
<script type="text/javascript" src="<c:url value='/js/showModalDialogCallee.js'/>"></script>
<script type="text/javascript">

function fnCheckId(){
	var idForm = document.forms["checkForm"]
	if(idForm.checkId.value==""){
		alert("중복체크할 아이디를 입력해주세요.");
		idForm.focus();
		return;
	}
	if(fnCheckNotKorean(idForm.checkId.value)){
		idForm.submit();
	}else{
		alert("아이디로 한글을 사용할 수 없습니다.");
		return;
	}
}
function fnCheckNotKorean(koreanStr){
	for(var i=0; i<koreanStr.length;i++){
		var koreanChar = koreanStr.charCodeAt(i);
		if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) { 
        }else{
            return false;
        }
    }
    return true;
}
function fnReturnId(){
	var retVal="";
	var idForm = document.forms["checkForm"]
	if(idForm.usedCnt.value == 0){
		retVal = idForm.resultId.value;
		setReturnValue(retVal);
		window.returnValue = retVal;
		window.close();
	}else if(idForm.usedCnt.value == 1){
		alert("이미 사용중인 아이디입니다.")
		return;
	}else{
		alert("중복체크를 해주세요.");
		return;
	}
}
function fnClose(){
	var retVal="";
	window.returnValue = retVal;
	window.close();
}

</script>
<title>ID 중복 확인</title>
</head>
<body>

	<form name="checkForm" id="checkForm" action ="<c:url value='/home/join/JoinCheckId.do'/>">
	    <input type="submit" id="invisible" class="invisible"/>
		    <table border="0" cellspacing="0" cellpadding="0" width="300" id="checkIdD">
		        <tr><td height="20" colspan="2"></td></tr>
		        <tr>
		            <td colspan="2" ><img alt="아이디중복확인" src="<c:url value='/images/tit_icon.gif'/>" width="16" height="16" hspace="3" align="middle">
		            &nbsp;아이디 중복확인</td>
		        </tr>
		        <tr><td height="20" colspan="2"></td></tr>
		        <tr>     
		            <td>사용할아이디 : &nbsp;</td>
		            <td>
		                <input type="hidden" name="resultId" value="<c:out value="${checkId}"/>" />
		                <input type="hidden" name="usedCnt" value="<c:out value="${usedCnt}"/>" />
		                <input type="text" name="checkId" title="선택여부" value="<c:out value="${checkId}"/>" maxlength="20"  />
		            </td>
		        </tr>
		        <tr><td height="10" colspan="2"></td></tr>
		        <tr>     
		            <td colspan="2">결과&nbsp;&nbsp;:&nbsp;
		                <c:choose>
		                <c:when test="${usedCnt eq -1}">
		                    &nbsp; 중복체크 하세요.
		                </c:when>
		                <c:when test="${usedCnt eq 0}">
		                    ${checkId} 는 사용가능한 아이디입니다.
		                </c:when>
		                <c:otherwise>
		                    ${checkId} 는 사용할수 없는 아이디입니다.
		                </c:otherwise>
		                </c:choose>
		            </td>
		        </tr>
		        <tr><td height="15" colspan="2"></td></tr>
		    </table>
		    <!-- 버튼 시작(상세지정 style로 div에 지정) -->
		    <div class="buttons" style="padding-top:10px;padding-bottom:10px;">
		        <a href="#LINK" onclick="javascript:fnCheckId(); return false;"><spring:message code="button.inquire" /></a>
		        <a href="#LINK" onclick="javascript:fnReturnId(); return false;"><spring:message code="button.use" /></a>
		        <a href="#LINK" onclick="javascript:fnClose(); return false;"><spring:message code="button.close" /></a>
		    </div>
    </form> 
</body>
</html>