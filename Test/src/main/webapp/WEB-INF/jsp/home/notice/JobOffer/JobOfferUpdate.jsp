<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ page import="cmm.util.FileUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images/egovframework/cop/bbs/"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="ko">
<link rel="stylesheet" href="<c:url value='/'/>css/default.css" type="text/css" >
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<style type="text/css">
#paging_div{
	margin:50px;
}
 .paging_align{
 	margin-bottom:50px;
 }
 td{
 	text-align: left;
 }
 .inputT{
 	width: 193px;
 	height: 29.5px;
 	margin: 0px;
 	padding: 0px;
 }
 .inputT2{
 	width: 595px;
 	height: 29.5px;
 	margin: 0px;
 	padding: 0px;
 }
 .inputT3{
 	width: 130px;
 	height: 29.5px;
 	margin: 0px;
 	padding: 0px;
 }
 #btn{
 	margin-left:600px;
 	width: 100px;
 	height: 20px;
 }
 .btns{
 	width: 50px;
 	height: 20px;
 	cursor: pointer;
 }
</style>
<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
</c:when>
<c:otherwise>
<script type="text/javascript">

	/* 시도 select가 선택 시  option의 value가 아닌 text값을 가져오는 코드*/
	function fn_sidoName(){
		var sido = document.getElementById("sido")
		var sN = sido.options[sido.selectedIndex].text
		document.getElementById("sidoName").value = sN
	}
	function Update_Notice() {
		var date = document.getElementById("date1").value +' ~ ' + document.getElementById("date2").value
		document.getElementById("item07").value = date
		document.frm.action = "<c:url value='/noticeUpdate.do'/>";
		document.frm.submit();
	}
	function fnDetail(){
		var varForm = document.move
		varForm.action = "/selectNoticeDetail.do";
		varForm.submit();
	}

</script>
</c:otherwise>
</c:choose>
<title><c:out value="${notice.bbs_nm}"/> 게시판</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0px; line-height:0;}
</style>

</head>
<body>
<!-- login status start -->
<div id="login_area"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncTborder" /></div>
<!-- //login status end -->
<!-- wrap start -->
<div id="wrap">
    <!-- header start -->
    <div id="subheader"><c:import url="/sym/mms/EgovMainMenuHead.do" /></div>
    <!-- //header end -->
    <div id="bodywrap">
        <div id="leftmenu_div"><c:import url="/sym/mms/EgovMainMenuLeft.do" /></div>
        <div id="middle_content">
            <!-- 현재위치 네비게이션 시작 -->
            <div id="cur_loc">
                    <div id="cur_loc_align">
                        <ul>
                            <li>HOME</li>
                            <li>&gt;</li>
                            <li>알림마당</li>
                            <li>&gt;</li>
                            <li><strong>${notice.bbs_nm}</strong></li>
                        </ul>
                    </div>
            </div>
            <div id="content_field"><!--contents start-->

			<!-- sub title start -->
            <div><h2><c:out value="${notice.bbs_nm}"/> 게시판</h2></div>
            	<div id="btn">
            		<c:if test="${user.uniqId == searchVO.crt_usr_no}">
            			<input type="button" value=" 수정 왼료 " class="btns">
            		</c:if>
           		 </div>
            <form name="frm" method="post" enctype="multipart/form-data" action="/noticeUpdate.do">
            <input type="hidden" id="section_cd" name="bbs_section_cd" value="<c:out value='${notice.bbs_section_cd}'/>" />	
            <input type="hidden" name="bbs_no"  value="${searchVO.bbs_no }" id="bbs_no"/>
            <input type="hidden" name="usr_no"  value="${searchVO.usr_no }" />
            <input type="hidden" name="enc_usr_nm"  value="${searchVO.enc_usr_nm }" />
            <input type="hidden" name="department"  value="${searchVO.department }" />
            <input type="hidden" name="crt_usr_no"  value="${searchVO.crt_usr_no }" />
            <input type="hidden" name="crt_dt"  value="${searchVO.crt_dt }" />
            <input type="hidden" name="bbs_pw"  value="${searchVO.bbs_pw }" />
			<div><h2><label>제목: </label><input type="text" value="<c:out value='${searchVO.title}'/>" name="title"></h2></div>

            <div class="search_result_div">
            	<h3>구인 담당자 정보</h3>
                <table width="98%" cellpadding="0" cellspacing="0" summary="담당자, 전화번호, 팩스,  E-mail">
                <caption>구인 담당자 정보</caption>

                <colgroup>
                    <col width="25%" >
                    <col width="25%" >
                    <col width="25%" >
                    <col width="25%" >
                </colgroup>
                <tr>
                    <th>담당자</th>
                    <td ><input class="inputT" type="text" name="content1" value="<c:out value='${searchVO.enc_usr_nm}'/>"/></td>
                    <th>전화번호</th>		 
                    <td><input class="inputT" type="text" name="phone_no1" value="<c:out value='${searchVO.phone_no1}'/>"/></td> 
                </tr>
                <tr>
                    <th>팩스</th>
                    <td ><input class="inputT" type="text" name="phone_no2" value="<c:out value='${searchVO.phone_no2}'/>"/>/></td>
				    <th>E-mail</th>
				    <td><input class="inputT" type="text" name="enc_email" value="<c:out value='${searchVO.enc_email}'/>"/>/></td>
                </tr>          
                 </table>
            </div>
            <div class="search_result_div">
            	<h3>채용 정보</h3>
                <table width="98%" cellpadding="0" cellspacing="0" summary="근무형태, 직종, 근무지역, 모집인원, 자격요건, 채용내용, 준비서류, 접수기간, 접수방법, 근무기간, 급여">
                <caption>채용 정보</caption>
               <colgroup>
                    <col width="25%" >
                    <col width="25%" >
                    <col width="25%" >
                    <col width="25%" >
                </colgroup>
                <tr>
                <td colspan="4"><c:out value="${searchVO.title}"/></td>
                </tr>
                <tr>
                    <th>근무형태</th>
                    <td >
                    <select name="item01" class="inputT" title="근무조건 선택">
						<option value="<c:out value='${searchVO.item01}'/>" selected="selected"><c:out value='${searchVO.item01}'/></option>
					    <c:forEach items="${workType}" var="wT">
					    <c:if test="${wT != searchVO.item01 }">
			            	<option value="${wT}">${wT}</option>
			            </c:if>
			            </c:forEach>
					</select>
                    </td>
                    <th>직종</th>		 
                    <td>
                    <select name="item02" class="inputT" title="검색조건 선택">
						<option value="<c:out value='${searchVO.item02}'/>" selected="selected"><c:out value='${searchVO.item02}'/></option>
						<c:forEach items="${workJobType}" var="wJT">
			        	<c:if test="${wJT != searchVO.item02 }">
			            	<option value="${wJT}">${wJT}</option>
			            </c:if>
			        	</c:forEach>
			        </select>
                    </td> 
                </tr>
                <tr>
                    <th>근무지역</th>
                    <td >
                    	<input type="hidden" value="${searchVO.item11}" id="sidoName" name="item11">
                    	<select name="item03" class="inputT" id="sido" onchange="fn_sidoName()">
			            <option value="${searchVO.item03}" selected="selected"><c:out value='${searchVO.item11}'/></option>
			            <c:forEach items="${sido_result}" var="sido">
			            <c:if test="${sido.sidoCd != searchVO.item03 }">
			            	<option value="${sido.sidoCd}">${sido.sidoNm}</option>
			            </c:if>
			            </c:forEach>
			            </select>
			        </td>
				    <th>모집인원</th>
				    <td><input class="inputT3" type="text" name="item04" value="<c:out value='${searchVO.item04}' />"/> 명</td>
                </tr> 
                <tr>
                    <th>자격요건</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item05" value="<c:out value='${searchVO.item05}' />"/></td>
                </tr>    
                <tr>
                    <th>채용내용</th>
                    <td colspan="3"><input class="inputT2" type="text" name="contents" value="<c:out value='${searchVO.contents}' />"/></td>
                </tr>  
                 <tr>
                    <th>준비서류</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item06" value="<c:out value='${searchVO.item06}' />"/></td>
                </tr>
                <tr>
                    <th>접수기간</th>
                    <td colspan="3"><input type="hidden" value="<c:out value='${searchVO.item07}' />" name="item07" id="item07"><input id="date1" class="inputT3" type="Date"/> ~ <input id="date2" class="inputT3" type="Date"/></td>
				</tr>
				<tr>
				    <th>지원방법</th>
				    <td colspan="3"><input class="inputT2" type="text" name="item08" value="<c:out value='${searchVO.item08}' />"/></td>
                </tr> 
                <tr>
                    <th>근무기간</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item09" value="<c:out value='${searchVO.item09}' />"/></td>
                </tr>
                <tr>
                    <th>급여</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item10" value="<c:out value='${searchVO.item10}' />"/></td>
                </tr> 
                <tr>
                	<c:if test="${ fn:length(fileDetailVOList) > 0 }">
                     <th rowspan="2"><label for="FileUploader">파일첨부</label></th>
                     <td class="td_content" colspan="3">
	                 	<c:forEach items="${fileDetailVOList}" var="fileList" varStatus="status">
	                    	<a  class="fileD" href="here" onclick="downloadFile('frm', '${fileList.atchFileId}', '${fileList.fileSn}'); return false;">
								${fileList.orignlFileNm} <em>(${fileList.fileSize} bytes)</em>
							</a>
	                    </c:forEach>
                      </td>
				</tr>
				<tr>
                   	   <td class="td_content" colspan="3">
	                   		<input type="hidden" name="atchCtrl" value="<%=FileUtil.ORDER_FLAG_UPDATE%>"/>
							<input type="hidden" name="atchDtlNo" />
							<input type="file" name="atchFile"  class="file-input"/>
                  	   </td>
                </tr>
                  	 </c:if>
                  	 <c:if test="${ fn:length(fileDetailVOList) eq 0 }">
                  	 	<th><label for="FileUploader">파일첨부</label></th>
                    	<td class="td_content" colspan="3">
	                   		<input type="hidden" name="atchCtrl" value="<%=FileUtil.ORDER_FLAG_INSERT%>"/>
							<input type="hidden" name="atchDtlNo"/>
							<input type="file" name="atchFile"  class="file-input"/>
                   		</td>
                   	 </c:if>
                  </tr>
            
                 </table>
            </div>
           </form>
            <!-- search result end -->	

            </div><!-- contents end -->
      
            <input type="submit" value="수정하기" onclick="Update_Notice(); return false;" />
            <input type="submit" value=" 뒤로 " onclick="fnDetail(); return false;" />
            
        </div>
    </div>
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //wrap end -->

<form name="move" action ="/selectNoticeList.do" method="post">
<input type="hidden" name="bbs_section_cd" value="<c:out value='${notice.bbs_section_cd}'/>" />	
<input type="hidden" name="bbs_no"  value="${searchVO.bbs_no }"/>
</form>
</body>
</html>