package home.notice.service;

import java.util.Date;

public class NoticeVO {
	private String bbs_section_cd;
	private String bbs_title;
	private String bbs_nm;
	private String use_comment_yn;
	private String use_upload_yn;
	
	private String bbs_no;
	private String depth_no;
	private String usr_no;
	private String enc_usr_nm;
	private String enc_email;
	private String department;
	private String phone_no1;
	private String phone_no2;
	private String title;
	private String contents;
	private String bbs_pw;
	private String pub_yn;
	private String del_yn;
	private String notice_yn;
	private String hit_cnt;
	private String crt_usr_no;
	private Date crt_dt;
	private String udt_usr_no;
	private Date udt_dt;
	private String gubun1;
	private String gubun2;
	private String content1;		// 담당자
	private String content2;		// 채용내용
	private String content3;
	private String item01;			// 근무형태 searchType1
	private String item02;			// 직종 searchType2
	private String item03;			// 지역 searchType3	
	private String item04;			// 인원
	private String item05;			// 글	자격요건
	private String item06;			// 필요서류
	private String item07;			// 지원기간 searchType4
	private String item08;			// 지원방식
	private String item09;			// 근무기간
	private String item10;			// 급여
	private String item11;			
	
	private String atch_file_id;     // 첨부파일 id
	private String file_1;     // 첨부파일 id
	
	private int recordCountPerPage = 10;
	private int pageIndex = 1;				// 현재 페이지 인덱스
	private int pageUnit = 10;
	private int pageUnit10 = 10;
	private int pageSize = 10;
	private int mobilePageSize = 5;
	private int firstIndex = 1;
	private int lastIndex = 1;
	private int limit = 5;
	private int fixedPage = 0;
	
	private String searchCnd = "0";		// 0 제목, 1 내용, 2 작성자
	private String searchWrd ="";
	private String searchType1 ="";		// 근무형태
	private String searchType2 ="";		// 직종
	private String searchType3 ="";		// 지역
	private String searchType4 ="";		// 기간
	private String searchType5 ="";		
	
	
	
	public String getFile_1() {
		return file_1;
	}
	public void setFile_1(String file_1) {
		this.file_1 = file_1;
	}
	public String getAtch_file_id() {
		return atch_file_id;
	}
	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}
	public String getSearchType1() {
		return searchType1;
	}
	public void setSearchType1(String searchType1) {
		this.searchType1 = searchType1;
	}
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String getSearchType3() {
		return searchType3;
	}
	public void setSearchType3(String searchType3) {
		this.searchType3 = searchType3;
	}
	public String getSearchType4() {
		return searchType4;
	}
	public void setSearchType4(String searchType4) {
		this.searchType4 = searchType4;
	}
	public String getSearchType5() {
		return searchType5;
	}
	public void setSearchType5(String searchType5) {
		this.searchType5 = searchType5;
	}
	public String getSearchWrd() {
		return searchWrd;
	}
	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}
	public String getSearchCnd() {
		return searchCnd;
	}
	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}
	public String getBbs_nm() {
		return bbs_nm;
	}
	public void setBbs_nm(String bbs_nm) {
		this.bbs_nm = bbs_nm;
	}
	public String getBbs_section_cd() {
		return bbs_section_cd;
	}
	public void setBbs_section_cd(String bbs_section_cd) {
		this.bbs_section_cd = bbs_section_cd;
	}
	public String getBbs_title() {
		return bbs_title;
	}
	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}
	public String getUse_comment_yn() {
		return use_comment_yn;
	}
	public void setUse_comment_yn(String use_comment_yn) {
		this.use_comment_yn = use_comment_yn;
	}
	public String getUse_upload_yn() {
		return use_upload_yn;
	}
	public void setUse_upload_yn(String use_upload_yn) {
		this.use_upload_yn = use_upload_yn;
	}
	public String getBbs_no() {
		return bbs_no;
	}
	public void setBbs_no(String bbs_no) {
		this.bbs_no = bbs_no;
	}
	public String getDepth_no() {
		return depth_no;
	}
	public void setDepth_no(String depth_no) {
		this.depth_no = depth_no;
	}
	public String getUsr_no() {
		return usr_no;
	}
	public void setUsr_no(String usr_no) {
		this.usr_no = usr_no;
	}
	public String getEnc_usr_nm() {
		return enc_usr_nm;
	}
	public void setEnc_usr_nm(String enc_usr_nm) {
		this.enc_usr_nm = enc_usr_nm;
	}
	public String getEnc_email() {
		return enc_email;
	}
	public void setEnc_email(String enc_email) {
		this.enc_email = enc_email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone_no1() {
		return phone_no1;
	}
	public void setPhone_no1(String phone_no1) {
		this.phone_no1 = phone_no1;
	}
	public String getPhone_no2() {
		return phone_no2;
	}
	public void setPhone_no2(String phone_no2) {
		this.phone_no2 = phone_no2;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getBbs_pw() {
		return bbs_pw;
	}
	public void setBbs_pw(String bbs_pw) {
		this.bbs_pw = bbs_pw;
	}
	public String getPub_yn() {
		return pub_yn;
	}
	public void setPub_yn(String pub_yn) {
		this.pub_yn = pub_yn;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getNotice_yn() {
		return notice_yn;
	}
	public void setNotice_yn(String notice_yn) {
		this.notice_yn = notice_yn;
	}
	public String getHit_cnt() {
		return hit_cnt;
	}
	public void setHit_cnt(String hit_cnt) {
		this.hit_cnt = hit_cnt;
	}
	public String getCrt_usr_no() {
		return crt_usr_no;
	}
	public void setCrt_usr_no(String crt_usr_no) {
		this.crt_usr_no = crt_usr_no;
	}
	public Date getCrt_dt() {
		return crt_dt;
	}
	public void setCrt_dt(Date crt_dt) {
		this.crt_dt = crt_dt;
	}
	public String getUdt_usr_no() {
		return udt_usr_no;
	}
	public void setUdt_usr_no(String udt_usr_no) {
		this.udt_usr_no = udt_usr_no;
	}
	public Date getUdt_dt() {
		return udt_dt;
	}
	public void setUdt_dt(Date udt_dt) {
		this.udt_dt = udt_dt;
	}
	public String getGubun1() {
		return gubun1;
	}
	public void setGubun1(String gubun1) {
		this.gubun1 = gubun1;
	}
	public String getGubun2() {
		return gubun2;
	}
	public void setGubun2(String gubun2) {
		this.gubun2 = gubun2;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getContent3() {
		return content3;
	}
	public void setContent3(String content3) {
		this.content3 = content3;
	}
	public String getItem01() {
		return item01;
	}
	public void setItem01(String item01) {
		this.item01 = item01;
	}
	public String getItem02() {
		return item02;
	}
	public void setItem02(String item02) {
		this.item02 = item02;
	}
	public String getItem03() {
		return item03;
	}
	public void setItem03(String item03) {
		this.item03 = item03;
	}
	public String getItem04() {
		return item04;
	}
	public void setItem04(String item04) {
		this.item04 = item04;
	}
	public String getItem05() {
		return item05;
	}
	public void setItem05(String item05) {
		this.item05 = item05;
	}
	public String getItem06() {
		return item06;
	}
	public void setItem06(String item06) {
		this.item06 = item06;
	}
	public String getItem07() {
		return item07;
	}
	public void setItem07(String item07) {
		this.item07 = item07;
	}
	public String getItem08() {
		return item08;
	}
	public void setItem08(String item08) {
		this.item08 = item08;
	}
	public String getItem09() {
		return item09;
	}
	public void setItem09(String item09) {
		this.item09 = item09;
	}
	public String getItem10() {
		return item10;
	}
	public void setItem10(String item10) {
		this.item10 = item10;
	}
	public String getItem11() {
		return item11;
	}
	public void setItem11(String item11) {
		this.item11 = item11;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageUnit10() {
		return pageUnit10;
	}
	public void setPageUnit10(int pageUnit10) {
		this.pageUnit10 = pageUnit10;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getMobilePageSize() {
		return mobilePageSize;
	}
	public void setMobilePageSize(int mobilePageSize) {
		this.mobilePageSize = mobilePageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getFixedPage() {
		return fixedPage;
	}
	public void setFixedPage(int fixedPage) {
		this.fixedPage = fixedPage;
	}
	
	/***************************************************** */
	
	
}
