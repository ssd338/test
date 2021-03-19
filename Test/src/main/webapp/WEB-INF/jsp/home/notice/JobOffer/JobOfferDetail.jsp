<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
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
</style>
<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">

	function press(event) {
	}

	function fn_egov_addNotice() {
	}

	function fn_select_noticeList(pageNo) {
	}

	function fn_egov_inqire_notice(nttId, bbsId) {
	}

</script>
</c:when>
<c:otherwise>
<script type="text/javascript">

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_noticeList('1');
		}
	}

	function fn_egov_addNotice() {
		document.frm.action = "<c:url value='/cop/bbs${prefix}/addBoardArticle.do'/>";
		document.frm.submit();
	}

	function select_noticeList(pageNo) {
		var listForm = document.forms["frm"];
		listForm.pageIndex.value = pageNo;
		listForm.action = "/selectNoticeList.do";
	   	listForm.submit();
	}

	function fn_egov_inqire_notice(nttId, bbsId) {
		//document.subForm.nttId.value = nttId;
		//document.subForm.bbsId.value = bbsId;
		//document.subForm.action = "<c:url value='/cop/bbs${prefix}/selectBoardArticle.do'/>";
		//document.subForm.submit();
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

			<form name="frm" action ="<c:url value='/selectNoticeList.do'/>" method="post">
			<input type="hidden" name="bbs_section_cd" value="<c:out value='${notice.bbs_section_cd}'/>" />			
			<input type="hidden" name="bbs_no"  value="${searchVO.bbs_no }" />
            </form>
            
			<!-- sub title start -->
            <div><h2><c:out value="${notice.bbs_nm}"/> 게시판</h2></div>
            <!-- sub title end -->


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
                    <td ><c:out value="${searchVO.enc_usr_nm}"/></td>
                    <th>전화번호</th>		 
                    <td><c:out value="${searchVO.phone_no1}" /></td> 
                </tr>
                <tr>
                    <th>팩스</th>
                    <td ><c:out value="${searchVO.phone_no2}"/></td>
				    <th>E-mail</th>
				    <td><c:out value="${searchVO.enc_email}" /></td>
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
                    <td ><c:out value="${searchVO.item01}"/></td>
                    <th>직종</th>		 
                    <td><c:out value="${searchVO.item02}" /></td> 
                </tr>
                <tr>
                    <th>근무지역</th>
                    <td ><input type="hidden" value="${searchVO.item03}"><c:out value="${searchVO.item03}"/></td>
				    <th>모집인원</th>
				    <td><c:out value="${searchVO.item04} 명" /></td>
                </tr> 
                <tr>
                    <th>자격요건</th>
                    <td colspan="3"><c:out value="${searchVO.item05}" /></td>
                </tr>    
                <tr>
                    <th>채용내용</th>
                    <td colspan="3"><c:out value="${searchVO.contents}" /></td>
                </tr>  
                 <tr>
                    <th>준비서류</th>
                    <td colspan="3"><c:out value="${searchVO.item06}" /></td>
                </tr>
                <tr>
                    <th>접수기간</th>
                    <td ><c:out value="${searchVO.item07}"/></td>
				    <th>지원방법</th>
				    <td><c:out value="${searchVO.item08} 명" /></td>
                </tr> 
                <tr>
                    <th>근무기간</th>
                    <td colspan="3"><c:out value="${searchVO.item09}" /></td>
                </tr>
                <tr>
                    <th>급여</th>
                    <td colspan="3"><c:out value="${searchVO.item10}" /></td>
                </tr>             
                 </table>
            </div>
            
            <!-- search result end -->	

            </div><!-- contents end -->
            <div style="border: 1px solid;">
            	<%-- <c:if test="${fn:length(resultPrevNextList) eq 2}">
            		<c:forEach items="${resultPrevNextList}" var="prevNextList" varStatus="status">
			        <fmt:formatDate value="${prevNextList.frstRegistPnttm}" type="date" pattern="yyyy-MM-dd" var="prevNextRegistPnttmFormat" />
					<c:choose>
						<c:when test="${prevNextList.gubun eq 'PREV'}">
							<p><span>이전글</span>
							<span class="type">[<c:out value='${prevNextList.nttType }' escapeXml="false" />]</span>
			                <a href="#LINK" onclick="fnDetail('${prevNextList.nttId}'); return false;"><c:out value='${prevNextList.nttSj}' escapeXml="false" /></a>
			                <span class="date">${prevNextRegistPnttmFormat}</span>
							</p>
						</c:when>
						<c:otherwise>
							<p><span>다음글</span>
								<span class="type">[<c:out value='${prevNextList.nttType }' escapeXml="false" />]</span>
								<a href="#LINK" onclick="fnDetail('${prevNextList.nttId}'); return false;"><c:out value='${prevNextList.nttSj}' escapeXml="false" /></a>
								<span class="date">${prevNextRegistPnttmFormat}</span>
							</p>
						</c:otherwise>
					</c:choose>
				</c:forEach> 
            	</c:if> --%>
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