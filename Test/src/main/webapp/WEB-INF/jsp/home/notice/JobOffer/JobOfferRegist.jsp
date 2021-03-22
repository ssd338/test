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
</style>
<script type="text/javascript" src="<c:url value='/js/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
</c:when>
<c:otherwise>
<script type="text/javascript">


	function Regist_Notice() {
		var date = document.getElementById("date1").value +' ~ ' + document.getElementById("date2").value
		document.getElementById("item07").value = date
		document.frm.action = "<c:url value='/noticeRegist.do'/>";
		document.frm.submit();
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

            
			
            <input type="hidden" name="fileAtchPosblAt" value="Y" /><!-- 파일첨부 가능여부 -->
            <input type="hidden" id="posblAtchFileNumber" name="posblAtchFileNumber" value="3" /><!-- 파일 첨부 가능숫자 -->
            <input type="hidden" name="posblAtchFileSize" value="" /><!--  첨부 가능 사이즈 -->
         
            
			<!-- sub title start -->
            <div><h2><c:out value="${notice.bbs_nm}"/> 게시판</h2></div>
            <form name="frm" method="post" enctype="multipart/form-data" action="/noticeRegist.do">
            <input type="hidden" name="bbs_section_cd" value="<c:out value='${notice.bbs_section_cd}'/>" />			
			<div><h2><label>제목: </label><input type="text" value="" name="title"></h2></div>

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
                    <td ><input class="inputT" type="text" name="content1"/></td>
                    <th>전화번호</th>		 
                    <td><input class="inputT" type="text" name="phone_no1"/></td> 
                </tr>
                <tr>
                    <th>팩스</th>
                    <td ><input class="inputT" type="text" name="phone_no2"/></td>
				    <th>E-mail</th>
				    <td><input class="inputT" type="text" name="enc_email"/></td>
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
                <td colspan="4"><c:out value=""/></td>
                </tr>
                <tr>
                    <th>근무형태</th>
                    <td >
                    <select name="item01" class="inputT" title="근무조건 선택">
						<option value="" <c:if test="${searchVO.searchType1 == ''}">selected="selected"</c:if> >근무형태선택</option>
					    <c:forEach items="${workType}" var="wT">
			            	<option value="${wT}">${wT}</option>
			            </c:forEach>
					</select>
                    </td>
                    <th>직종</th>		 
                    <td>
                    <select name="item02" class="inputT" title="검색조건 선택">
						<option value="" <c:if test="${searchVO.searchType2 == ''}">selected="selected"</c:if> >직종선택</option>
						<c:forEach items="${workJobType}" var="wJT">
			        		<option value="${wJT}" >${wJT}</option>
			        	</c:forEach>
			        </select>
                    </td> 
                </tr>
                <tr>
                    <th>근무지역</th>
                    <td >
                    	<select name="item03" class="inputT" id="sido">
			            <option value="" selected>시도</option>
			            <c:forEach items="${sido_result}" var="sido">
			            <option value="${sido.sidoCd}" >${sido.sidoNm}</option>
			            </c:forEach>
			            </select>
			        </td>
				    <th>모집인원</th>
				    <td><input class="inputT3" type="text" name="item04"/> 명</td>
                </tr> 
                <tr>
                    <th>자격요건</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item05"/></td>
                </tr>    
                <tr>
                    <th>채용내용</th>
                    <td colspan="3"><input class="inputT2" type="text" name="content2"/></td>
                </tr>  
                 <tr>
                    <th>준비서류</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item06"/></td>
                </tr>
                <tr>
                    <th>접수기간</th>
                    <td colspan="3"><input type="hidden" value="" name="item07" id="item07"><input id="date1" class="inputT3" type="Date"/> ~ <input id="date2" class="inputT3" type="Date"/></td>
				</tr>
				<tr>
				    <th>지원방법</th>
				    <td colspan="3"><input class="inputT2" type="text" name="item08"/></td>
                </tr> 
                <tr>
                    <th>근무기간</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item09"/></td>
                </tr>
                <tr>
                    <th>급여</th>
                    <td colspan="3"><input class="inputT2" type="text" name="item10"/></td>
                </tr> 
                <tr>
                   <th><label for="egovComFileUploader">파일첨부</label></th>
                   <td class="td_content" colspan="3">
                   		 <input name="file_1" id="egovComFileUploader" type="file" title="파일첨부"/>
					        <div id="egovComFileList"></div>
                   </td>
                </tr>              
                 </table>
                 
                  <script type="text/javascript">
                       var maxFileNum = document.getElementById("posblAtchFileNumber").value;
                       if(maxFileNum==null || maxFileNum==""){
                           maxFileNum = 3;
                       } 
                           
                      var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
                      multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) ); 
                  </script> 
            </div>
           </form>
            <!-- search result end -->	

            </div><!-- contents end -->
      
            <input type="submit" value="등록" onclick="Regist_Notice(); return false;" />
            
        </div>
    </div>
    <!-- footer 시작 -->
    <div id="footer"><c:import url="/EgovPageLink.do?link=main/inc/EgovIncFooter" /></div>
    <!-- //footer 끝 -->
</div>
<!-- //wrap end -->


</body>
</html>