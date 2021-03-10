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
	.btn{
		background-color: gray;
		color: white;
		padding: 2px 5px 2px 5px;
		border-radius: 2px;
	}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<link rel="stylesheet" href="<c:url value='/'/>css/default.css" type="text/css" >
<title>회원 신청</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="joinVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/EgovZipPopup.js' />" ></script>
<script type="text/javaScript" language="javascript" defer="defer">

function fnIdCheck(){
    var retVal;
    var url = "<c:url value='/home/join/JoinCheckIdView.do'/>";
    var varParam = new Object();
    varParam.checkId = document.joinVO.usrId.value;
    var openParam = "dialogWidth:600px;dialogHeight:500px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    
    if(retVal) {
        document.joinVO.usrId.value = retVal;
        document.joinVO.id_view.value = retVal;
    }
}
function showModalDialogCallback(retVal) {
	if(retVal) {
        document.joinVO.usrId.value = retVal;
        document.joinVO.id_view.value = retVal;
    }
}

function fnSbscrb(){
	var varForm = document.joinVO;
	var varTel = new Object();
	if(validateJoinVO(varForm)){
		if(document.joinVO.encUsrPw.value != document.joinVO.password2.value){
 			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return;
        }
		if(varForm.areaNo.value != null ||  varForm.middleTelno.value != null ||  varForm.endTelno.value != null){
			varForm.encPhoneNo.value = varForm.areaNo.value + varForm.middleTelno.value +  varForm.endTelno.value
			
		}
        document.joinVO.submit();
    }	
}

</script>
</head>
<body>

<!-- wrap start -->
<div id="wrap"> 
   
   
    <div id="bodywrap">
    	<div id="middle_content">
			<div id="content_field"><!--contents start-->
            
            <form:form commandName="joinVO" action="${pageContext.request.contextPath}/home/join/Join.do" name="joinVO"  method="post" >    
	      
	        <!-- 우편번호검색 -->
	        <input type="hidden" name="zip_url" value="<c:url value='/sym/cmm/EgovCcmZipSearchPopup.do'/>" />
	        
	        <!-- sub title start -->
            <div><h2>일반회원 가입</h2></div>
            <!-- sub title end -->
            
            <!--detail area start -->
            <div class="search_service">
                <div class="search_top_table">
                    <table summary="일반회원 가입정보">
                     <tr> 
			                <td class="td_width">이름
			                    <img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15"/>
			                </td>
			                <td class="td_content">
			                <input name="encUsrNm" type="text" size="20" title="일반회원 이름"
			                        value=<c:if test="${not empty encUsrNm}">"<c:out value="${encUsrNm}"/>" readonly</c:if>
			                              <c:if test="${empty encUsrNm}">""</c:if>
			                        maxlength="60" >
			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">희망아이디
			                    <img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15"/>
			                </td>
			                <td class="td_content">
			                    <input type="text" size="20" maxlength="20" disabled="disabled" id="id_view" name="id_view" >
			                    <form:input path="usrId" size="20" cssClass="txaIpt" readonly="true" maxlength="20" cssStyle="display:none" />
			                    <a href="#LINK" onclick="javascript:fnIdCheck(); return false;">
			                        	<button class="btn">중복확인</button>
			                    </a>
			                    <div><form:errors path="usrId" cssClass="error" /></div>
			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">비밀번호
			                    <img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15"/>
			                </td>
			                <td class="td_content">
			                    <form:password path="encUsrPw" size="20" maxlength="20" />
			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">비밀번호확인
			                    <img src="<c:url value='/'/>images/required.gif" alt="필수항목" title="필수항목" width="15" height="15"/>
			                </td>
			                <td class="td_content">
			                    <input name="password2" type="password" size="20" maxlength="20" title="비밀번호 확인">
			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">생년월일
			                </td>
			                <td class="td_content">
			                	<input type="hidden" name="birthday" id="birthday">
			                    <input type="date" name="birthday" Class="txaIpt" size="5" maxlength="5"  />         
			                </td>
			            </tr>
			            <tr>
			                <td class="td_width">성별&nbsp;&nbsp;</td>
			                <td class="td_content">
			                    
			                         <form:radiobutton path="sexCd" value="M" label="남" checked="checked" />
			                        <form:radiobutton path="sexCd" value="P" label="여"/>

			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">이메일
			                </td>
			                <td class="td_content">
			                    <form:input path="encEmail" cssClass="txaIpt" size="30" maxlength="50" />
			                    <div><form:errors path="encEmail" cssClass="error" /></div>
			                </td>
			            </tr>
			             
			            <%-- <tr> 
			                <td class="td_width">우편번호
			                </td>
			                <td class="td_content">
			                    <input name="zip_view" title="우편번호" type="text" size="20" value="<c:out value='${joinVO.zip}'/>" maxlength="8" readonly="readonly">
			                    <form:hidden path="zip" />
			                        <a href="#LINK" onclick="javascript:fn_egov_ZipSearch(document.joinVO, document.joinVO.zip, document.joinVO.zip_view, document.joinVO.adres);">
			                            <img alt="우편번호 검색" src="<c:url value='/images/img_search.gif'/>">(우편번호 검색)
			                        </a>
			                    <div><form:errors path="zip" cssClass="error" /></div>
			                </td>
			            </tr> --%>
			            <tr>
			                <td class="td_width">주소
			                </td>
			                <td class="td_content">
			                    <form:input path="encHAddress1" cssClass="txaIpt" size="70" maxlength="100" readonly="true" />
			                    <div><form:errors path="encHAddress1" cssClass="error" /></div>
			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">상세주소&nbsp;&nbsp;</td>
			                <td class="td_content">
			                    <form:input path="encHAddress3" cssClass="txaIpt" size="70" maxlength="100" />
			                    <div><form:errors path="encHAddress3" cssClass="error" /></div>
			                </td>
			            </tr>
			            <tr> 
			                <td class="td_width">전화번호
			                </td>
			                <td class="td_content">
			                	<input type="hidden" name="encPhoneNo" id="encPhoneNo">
			                	<select name="areaNo" class="txaIpt">
			                		<option value="" selected>선택</option>
			                		<option value="02" >02</option>
			                		<option value="031" >031</option>
			                		<option value="032" >032</option>
			                		<option value="033" >033</option>
			                		<option value="041" >041</option>
			                		<option value="042" >042</option>
			                		<option value="043" >043</option>
			                		<option value="051" >051</option>
			                		<option value="052" >052</option>
			                		<option value="053" >053</option>
			                		<option value="061" >061</option>
			                		<option value="062" >062</option>
			                		<option value="063" >063</option>
			                	</select>
			                    - <input name="middleTelno" Class="txaIpt" size="5" maxlength="5" />
			                    - <input name="endTelno" Class="txaIpt" size="5" maxlength="5" />
			                   
			                </td>
			            </tr>
			        
			            <tr> 
			                <td class="td_width">핸드폰번호
			                </td>
			                <td class="td_content">
			                    <form:input path="encMobileNo" cssClass="txaIpt" size="20" maxlength="15" type="hidden" />
			                    <div><form:errors path="encMobileNo" cssClass="error" /></div>
			                    <select name="Mtel1" class="txaIpt">
			                		<option value="" selected>선택</option>
			                		<option value="010" >"010"</option>
			                		<option value="011" >011</option>
			                		<option value="016" >016</option>
			                	</select>
			                    - <input name="Mtel2" Class="txaIpt" size="5" maxlength="5" />
			                    - <input name="Mtel3" Class="txaIpt" size="5" maxlength="5" />
			                </td>
			            </tr>
			            <tr>
			                <td class="td_width"> 소속&nbsp;&nbsp;</td>
			                <td class="td_content">
			                        <form:radiobutton path="option1" value="지역아동센터 시설장" label="지역아동센터 시설장" checked="checked"/>
			                        <form:radiobutton path="option1" value="지역아동센터 생활복지사" label="지역아동센터 생활복지사"/>
			                </td>
			            </tr>
			            
			        </table>
                </div>
            </div>
            <!--detail area end -->
            
            <!-- 목록/저장버튼  시작-->
            <table border="0" cellspacing="0" cellpadding="0" align="center"><tr><td>
            <div class="buttons" align="center" style="margin-bottom:100px">
                <a href="#LINK" onclick="javascript:fnSbscrb(); return false;">회원가입<%-- <spring:message code="button.subscribe" /> --%></a>
                <a href="#LINK" onclick="javascript:document.mberManageVO.reset(); return false;">리셋<%-- <spring:message code="button.reset" /> --%></a>
            </div>
            </td></tr></table>

            </form:form>

            </div><!-- contents end -->
        </div>
    </div>
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //wrap end -->

</body>
</html>