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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
/* 우편 */
function closeDaumPostcode() {
	var element_layer = document.getElementById('layer');
    element_layer.style.display = 'none';	 				 // iframe을 넣은 element를 안보이게 한다.
}
function getPost() {
	var element_layer = document.getElementById('layer');
    new daum.Postcode({
        oncomplete: function(data) {         
            var addr = ''; // 주소 변수         
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
                document.getElementById("doroAddrYn").value = "Y"					// 도로인지 구주소인지
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
                document.getElementById("doroAddrYn").value = "N"
            }
            
            data.zonecode = data.zonecode.replace(/,/g,"")	
            document.getElementById("encHZipcode").value = data.zonecode;			//우편번호 넣어줌
            document.getElementById("doroPost").value = data.zonecode;			//우편번호 넣어줌
            document.getElementById("Address1").value = addr;						// 주소 넣어줌
            document.getElementById("doroAddr1").value = data.roadAddress;			// 도로주소
            document.getElementById("encHAddress1").value = data.jibunAddress;		// 지역주소	
            document.getElementById("Address2").focus();						// 상세주소로 커서 
         
            // iframe을 넣은 element를 안보이게 한다.
            element_layer.style.display = 'none';
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);
    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';
    initLayerPosition();
}
    function initLayerPosition(){
    	var element_layer = document.getElementById('layer');
        var width = 300; //우편번호서비스가 들어갈 element의 width
        var height = 400; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 5; //샘플에서 사용하는 border의 두께
        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
   }
/* 우편끝 */


/* 아이디 중복체크 */
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
/* 아이디 중복체크 끝 */

/* 시군구 선택 */
 
function fnSido(){
	var sido_cn = document.getElementById("sido").value
	
	$.ajax({
        url: "/sigunguList.ajax",
        method: "POST",
        data: {sidoCd:sido_cn},
        success: function(data) {
        	alert("하하")
			}
				
		});

	
}
/* 	var option = sido.querySelector("option");
	alert(option) */

 
/* 시군구 선택 끝 */

/* 가입버튼  */
function fnSbscrb(){
	if(validation()){
		var varForm = document.joinVO;
		if(validateJoinVO(varForm)){
	        document.joinVO.submit();
	    }
	}
}

function validation(){
	/* 입력값의 불필요한 공백이과 기호를 제거한후 다시 넣어준다 */
	var arr = ['encUsrNm','encEmail','tel1','tel2','tel3','Address2','birthdayD'];
	var stringRegx = /[~!@#$%^&*()_+|<>?:{}]/; 
	var regex= /^[0-9]/g;
	
	for(var i=0; i<arr.length; i++){
		document.getElementById(arr[i]).value = document.getElementById(arr[i]).value.replace(/ /g,"")
		document.getElementById(arr[i]).value = document.getElementById(arr[i]).value.replace(/,/g,"")
	}
	
	
	/* 나눠져있는 값을 합친다 */
	var encUsrNm = document.getElementById("encUsrNm").value
	var tel1 = document.getElementById("tel1").value
	var tel2 = document.getElementById("tel2").value
	var tel3 = document.getElementById("tel3").value
	var Address2 = document.getElementById("Address2").value
	
	document.getElementById("encPhoneNo").value = tel1 + tel2 + tel3
	document.getElementById("doroAddr2").value = Address2
	document.getElementById("encHAddress2").value = Address2
	document.getElementById("birthday").value = document.getElementById("birthdayD").value
	
	if(encUsrNm == null || encUsrNm == ""){
		alert("이름을 입력하세요")
		return false;
	}
	if(tel1 == null || tel1 == "" || tel2 == null || tel2 == "" || tel3 == null || tel3 == "" ){
		alert("전화번호를 입력해주세요.");
		return false;
	}

	if(document.joinVO.encUsrPw.value != document.joinVO.password2.value){
			alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        return false;
    }	
	
	return true;
}

</script>
</head>
<body>

<!-- wrap start -->
<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
	<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
</div>
<div id="wrap"> 

    <div id="bodywrap">
    	<div id="middle_content">
			<div id="content_field"><!--contents start-->
			
			
            <form:form commandName="joinVO" action="${pageContext.request.contextPath}/home/join/JoinMberRegist.do" name="joinVO"  method="post" >    
	        
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
			                <input name="encUsrNm" type="text" size="20" title="일반회원 이름" id="encUsrNm" maxlength="60" >
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
			                    <input id="birthdayD" type="date" Class="txaIpt" size="5" maxlength="5" />         
			                    <form:input path="birthday" id="birthday" type="hidden" name="birthday" Class="txaIpt" size="5" maxlength="5"  />         
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
			             
			             <tr>
			                <td class="td_width" rowspan="2">주소
			                </td>
			                <td class="td_content">
			               		<input type="hidden" id="doroAddrYn" name="doroAddrYn" title="주소 타입">
			                    <input id="encHZipcode" name="encHZipcode" title="우편번호" type="text" size="20" maxlength="8" readonly="readonly">
			                    <input type="hidden" id="doroPost" name="doroPost">
			                    <form:hidden path="encHZipcode" />
			                        <a href="#LINK" onclick="getPost();">
			                            <input type="button" value="우편번호 검색">
			                        </a>
			                </td>
			              </tr>
			              <tr>			              
			                <td class="td_content" style="width:50px;">
			                    <input id="Address1" type="text" size="30" maxlength="70" readonly />
			                    <form:input path="encHAddress1" cssClass="txaIpt" size="30" maxlength="70" readonly="true" type="hidden"/>
			                    <form:input path="doroAddr1" cssClass="txaIpt" size="30" maxlength="70" readonly="true" type="hidden"/>
			                </td> 
			                <td class="td_content">
			                    <input id="Address2" type="text" size="30" maxlength="70" />
			                    <form:input path="doroAddr2" cssClass="txaIpt" size="30" maxlength="70" readonly="true" type="hidden"/>
			                    <form:input path="encHAddress2" cssClass="txaIpt" size="30" maxlength="70" readonly="true" type="hidden"/>
			                    <div><form:errors path="encHAddress2" cssClass="error" /></div>
			                </td>
			            </tr>
			           <tr> 
			                <td class="td_width">시도
			                </td>
			                <td class="td_content">
			                	<select name="sido" class="txaIpt" id="sido" onchange="fnSido()">
			                		<option value="" selected>시도</option>
			                		<c:forEach items="${sido_result}" var="sido">
			                			<option value="${sido.sidoCd}" >${sido.sidoNm}</option>
			                		</c:forEach>
			                	</select> 
			                	<select name="gungu" class="txaIpt" id="gungu">
			                		<option value="" selected>시군구</option>
			                		
			                		
			                	</select> 
			                </td>
			            </tr>
			           
			            <tr>
				            <td class="td_width">센터명
				            </td>
				            <td class="td_content">
				                <input name="option2" type="text" size="20" title="소속기관명"
				                	value=<c:if test="${not empty option2}">"<c:out value="${option2}"/>"</c:if>
				                    <c:if test="${empty option2}">""</c:if>
				                maxlength="60" >
				           </td>
			           </tr>
			            <tr> 
			                <td class="td_width">연락처
			                </td>
			                <td class="td_content">
			                	<input type="hidden" name="encPhoneNo" id="encPhoneNo">
			                	<select name="tel1" class="txaIpt" id="tel1">
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
			                		<option value="010" >010</option>
			                		<option value="011" >011</option>
			                		<option value="02" >016</option>
			                	</select>
			                    - <input id="tel2" name="tel2" Class="txaIpt" size="5" maxlength="5" />
			                    - <input id="tel3" name="tel3" Class="txaIpt" size="5" maxlength="5" />
			                   
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