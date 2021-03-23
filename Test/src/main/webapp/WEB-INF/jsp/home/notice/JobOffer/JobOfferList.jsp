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
</style>
<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${user.uniqId == 'anonymous'}">
<script type="text/javascript">

	function press(event) {
	}
	
	function Regist_Notice(){
	}

</script>
</c:when>
<c:otherwise>
<script type="text/javascript">

	function press(event) {
		if (event.keyCode==13) {
			select_noticeList('1');
		}
	}

	function select_noticeList(pageNo) {
		var listForm = document.forms["frm"];
		listForm.pageIndex.value = pageNo;
		listForm.action = "/selectNoticeList.do";
	   	listForm.submit();
	}

	function fn_deatil(bbs_no){
		var listForm = document.forms["frm"];
		listForm.bbs_no.value = bbs_no;
		listForm.action = "/selectNoticeDetail.do";
	   	listForm.submit();
	}

	function Regist_Notice(){
		var listForm = document.forms["frm"];
		listForm.action = "/insertNotice.do";
	   	listForm.submit();
	}
	
</script>
</c:otherwise>
</c:choose>
<title><c:out value="${notice.bbs_nm}"/> 게시판</title>

<style type="text/css">
	h1 {font-size:12px;}
	caption {visibility:hidden; font-size:0; height:0; margin:0; padding:0; line-height:0;}
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
			
			<input type="hidden" name="bbs_no"  value="0" />
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			<input type="submit" value="실행" onclick="select_noticeList('1'); return false;" id="invisible" class="invisible" />

			<!-- sub title start -->
            <div><h2><c:out value="${notice.bbs_nm}"/> 게시판</h2></div>
            <!-- sub title end -->

            <!-- search area start-->
            <div class="search_service">
                <div class="search_area">
                	<div class="search_conditions" >
	                    <select name="searchType1" class="searchItem" title="근무조건 선택">
					           <option value="" <c:if test="${searchVO.searchType1 == ''}">selected="selected"</c:if> >근무형태선택</option>
					           <c:forEach items="${workType}" var="wT">
			                		<option value="${wT}" <c:if test="${searchVO.searchType1 == wT}">selected="selected"</c:if> >${wT}</option>
			                   </c:forEach>
					    </select>
	                </div>
	                <div class="search_conditions" >
	                    <select name="searchType2" class="searchItem" title="검색조건 선택">
					           <option value="" <c:if test="${searchVO.searchType2 == ''}">selected="selected"</c:if> >직종선택</option>
					           <c:forEach items="${workJobType}" var="wJT">
			                		<option value="${wJT}" <c:if test="${searchVO.searchType2 == wJT}">selected="selected"</c:if> >${wJT}</option>
			                   </c:forEach>
			            </select>
	                </div>
	                <div class="search_conditions" >	
	                    <select name="searchType3" class="searchItem" title="검색조건 선택">
					           <option value="" <c:if test="${searchVO.searchType3 == ''}">selected="selected"</c:if> >지역선택</option>
					           <c:forEach items="${sidoResult}" var="sido">
			                		<option value="${sido.sidoCd}"  <c:if test="${searchVO.searchType3 == sido.sidoCd}">selected="selected"</c:if>>${sido.sidoNm}</option>
			                   </c:forEach>
					    </select>
	                </div>
	                <div class="search_conditions" >
	                    <select name="searchType4" class="searchItem" title="검색조건 선택">
					           <option value="" <c:if test="${searchVO.searchType4 == ''}">selected="selected"</c:if> >전체</option>
					          <c:forEach items="${workTime}" var="workTime">
			                		<option value="${workTime}" <c:if test="${searchVO.searchType4 == workTime}">selected="selected"</c:if>>${workTime}</option>
			                   </c:forEach>
			                    </select>
	                </div>
	                <div class="search_conditions" style=" clear: both;">
	                    <select name="searchCnd" class="select" title="검색조건 선택">
					           <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
					           <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
					           <option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
					    </select>
					    <input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
	                </div>
	                <div class="search_buttons">
				        <input type="submit" value="<spring:message code='button.inquire' />" onclick="select_noticeList('1'); return false;" />
	                </div>
                </div>
            </div>
            <!-- search area end -->

            </form>

<!-- search result start -->
            <div class="search_result_div">
                <table width="98%" cellpadding="0" cellspacing="0" summary="번호, 지역, 제목, 작성자, 모집기간, 인원, 조회수   입니다">
                <caption>게시물목록</caption>

                <colgroup>
                    <col width="5%" >
                    <col width="10%" >
                    <col >
                    <c:if test="${anonymous != 'true'}">
                      <col width="10%" >
                    </c:if>
                    <col width="25%" >
                    <col width="5%" >
                    <col width="5%" >
                </colgroup>

                <thead>
                <tr>
                    <th>번호</th>
                    <th>지역</th>
				    <th>제목</th>		 
				    <c:if test="${anonymous != 'true'}">
				        <th>작성자</th>
				    </c:if>
				    <th>모집기간</th>
				    <th>인원</th>
				    <th>조회수</th>
                </tr>
                </thead>

                <tbody>
				<c:if test="${fn:length(resultList) == 0}">
			        게시물이 없습니다.
			     </c:if>
                 <c:forEach var="result" items="${resultList}" varStatus="status">      
			     <tr onclick="fn_deatil('${result.bbs_no}')">
			        <td ><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.pageIndex-1) * searchVO.pageSize + status.count)}"/></td>
			        <td><c:out value="${result.item11}" /></td>
			        <td align="left">
			        <form name="subForm" method="post" action="<c:url value='/cop/bbs${prefix}/selectBoardArticle.do'/>">
			           
			            <c:choose>
			                <c:when test="${result.pub_yn == 'Y' && result.del_yn == 'N'}">
			                    <c:out value="${result.title}" />
			                </c:when>
			                <c:otherwise>
			                        <input type="hidden" name="bbs_section_cd" value="<c:out value='${result.bbs_section_cd}'/>" />
			                        <input type="hidden" name="bbs_no"  value="<c:out value="${result.bbs_no}"/>" />
			                        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			                        <span class="link"><input type="submit" style="width:320px;border:solid 0px black;text-align:left;" value="<c:out value="${result.title}"/>" ></span>
			                </c:otherwise>
			            </c:choose>
			            </form>
			        </td>
			       
			        <c:if test="${anonymous != 'true'}">
			            <td ><c:out value="${result.enc_usr_nm}"/></td>
			        </c:if>
			        <td ><c:out value="${result.item07}"/></td>
			        <td><c:out value="${result.item04}" /></td>
			        <td ><c:out value="${result.hit_cnt}"/></td>
			     </tr>
			     </c:forEach>
                 </tbody>

                 </table>
            </div>
            <!-- search result end -->

			<div id="paging_div">
			<ul class="paging_align">
				<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="select_noticeList" />
				</ul>
				<c:if test="${user.uniqId != 'anonymous'}"><input type="submit" value="등록" onclick="Regist_Notice(); return false;" /></c:if>
			</div>


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