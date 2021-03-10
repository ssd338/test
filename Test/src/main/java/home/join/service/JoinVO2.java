//package home.join.service;
//
//import java.util.Date;
//
///**
// * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
// * @author 공통서비스 개발팀 조재영
// * @since 2009.04.10
// * @version 1.0
// * @see
// *
// * <pre>
// * << 개정이력(Modification Information) >>
// *   
// *   수정일      수정자           수정내용
// *  -------    --------    ---------------------------
// *   2009.04.10  JJY            최초 생성
// *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성 
// *
// * </pre>
// */
//public class JoinVO{
//
//	/**
//	 * serialVersionUID
//	 */
//	private static final long serialVersionUID = 1L;
//	private String usr_no;			 // 사용자 no
//	private String usr_id;			 // 사용자 아이디
//	private String enc_usr_pw;  	 // 사용자 비밀번호
//	private String birthday;		 // 생년월일
//	private String sex_cd;			 // 성별
//	private String enc_usr_nm;		 // 사용자명
//	private String eng_nm;			 // 사용자 영문이름
//	private String nick_nm;			 // 사용자 닉네임
//	private String level_no;		 // 사용자 레벨
//	private String vlevel_no;		 // 평가센터 사용자 레벨
//	private String enc_email;		 // 이메일
//	private String email_receive_yn; // 이메일 수신여부
//	private String homepage; 		 // 홈페이지
//	private String enc_mobile_no;	 // 휴대폰
//	private String sms_receive_yn;	 // 문자수신여부
//	private String enc_phone_no;	 // 전화번호
//	private String job_cd;			 // 직업코드
//	private String enc_h_zipcode;	 // 지역번호
//	private String enc_h_address1;	 // 주소1
//	private String enc_h_address2;	 // 주소2
//	private String enc_h_address3;	 // 상세주소
//	private String doro_post;		 // 도로명 지역번호
//	private String doro_addr1;		 // 도로명 주소1
//	private String doro_addr2;		 // 도로명 주소2
//	private String doro_addr3;		 // 도로명 주소3
//	private String doro_addr4;		 // 도로명 주소4
//	private String doro_addr_yn;	 // 도로명 주소 사용여부
//	private String company_nm;		 // 업체명
//	private String department;		 // 부서
//	private String memo;			 // 메모
//	private String option1;			 // 소속
//	private String option2;			 // 소속기관명		
//	private String option3;			 // 대표자명
//	private String option4;			 // 설립년도
//	private String option5;			 // 지역
//	private String join_path;		 // 승인 계정
//	private Date crt_dt;			 // 등록일
//	private Date udt_dt;			 // 수정일
//	private String ESNTL_ID;		 // 권한 아이디
//	private String ORGNZT_ID;		 // 조직 아이디
//	private Date LAST_LOGIN_PNTTM; // 최종로그인 일지
//	private String LAST_LOGIN_IP;    // 최종 로그인 아이피
//	private String CRTFC_NO;		 // 인증번호
//	private String LOGIN_TRY_CNT;	 // 로그인 시도 횟수
//	private String working_year;	 // 연차
//	private String license;			 // 자격증
//	private String practician_yn;	 // 법적종사자 유무
//	private Date PASSWORD_LAST_PNTTM; // 패스워드 마지막 패턴
//	public String getUsr_no() {
//		return usr_no;
//	}
//	public void setUsr_no(String usr_no) {
//		this.usr_no = usr_no;
//	}
//	public String getUsr_id() {
//		return usr_id;
//	}
//	public void setUsr_id(String usr_id) {
//		this.usr_id = usr_id;
//	}
//	public String getEnc_usr_pw() {
//		return enc_usr_pw;
//	}
//	public void setEnc_usr_pw(String enc_usr_pw) {
//		this.enc_usr_pw = enc_usr_pw;
//	}
//	public String getBirthday() {
//		return birthday;
//	}
//	public void setBirthday(String birthday) {
//		this.birthday = birthday;
//	}
//	public String getSex_cd() {
//		return sex_cd;
//	}
//	public void setSex_cd(String sex_cd) {
//		this.sex_cd = sex_cd;
//	}
//	public String getEnc_usr_nm() {
//		return enc_usr_nm;
//	}
//	public void setEnc_usr_nm(String enc_usr_nm) {
//		this.enc_usr_nm = enc_usr_nm;
//	}
//	public String getEng_nm() {
//		return eng_nm;
//	}
//	public void setEng_nm(String eng_nm) {
//		this.eng_nm = eng_nm;
//	}
//	public String getNick_nm() {
//		return nick_nm;
//	}
//	public void setNick_nm(String nick_nm) {
//		this.nick_nm = nick_nm;
//	}
//	public String getLevel_no() {
//		return level_no;
//	}
//	public void setLevel_no(String level_no) {
//		this.level_no = level_no;
//	}
//	public String getVlevel_no() {
//		return vlevel_no;
//	}
//	public void setVlevel_no(String vlevel_no) {
//		this.vlevel_no = vlevel_no;
//	}
//	public String getEnc_email() {
//		return enc_email;
//	}
//	public void setEnc_email(String enc_email) {
//		this.enc_email = enc_email;
//	}
//	public String getEmail_receive_yn() {
//		return email_receive_yn;
//	}
//	public void setEmail_receive_yn(String email_receive_yn) {
//		this.email_receive_yn = email_receive_yn;
//	}
//	public String getHomepage() {
//		return homepage;
//	}
//	public void setHomepage(String homepage) {
//		this.homepage = homepage;
//	}
//	public String getEnc_mobile_no() {
//		return enc_mobile_no;
//	}
//	public void setEnc_mobile_no(String enc_mobile_no) {
//		this.enc_mobile_no = enc_mobile_no;
//	}
//	public String getSms_receive_yn() {
//		return sms_receive_yn;
//	}
//	public void setSms_receive_yn(String sms_receive_yn) {
//		this.sms_receive_yn = sms_receive_yn;
//	}
//	public String getEnc_phone_no() {
//		return enc_phone_no;
//	}
//	public void setEnc_phone_no(String enc_phone_no) {
//		this.enc_phone_no = enc_phone_no;
//	}
//	public String getJob_cd() {
//		return job_cd;
//	}
//	public void setJob_cd(String job_cd) {
//		this.job_cd = job_cd;
//	}
//	public String getEnc_h_zipcode() {
//		return enc_h_zipcode;
//	}
//	public void setEnc_h_zipcode(String enc_h_zipcode) {
//		this.enc_h_zipcode = enc_h_zipcode;
//	}
//	public String getEnc_h_address1() {
//		return enc_h_address1;
//	}
//	public void setEnc_h_address1(String enc_h_address1) {
//		this.enc_h_address1 = enc_h_address1;
//	}
//	public String getEnc_h_address2() {
//		return enc_h_address2;
//	}
//	public void setEnc_h_address2(String enc_h_address2) {
//		this.enc_h_address2 = enc_h_address2;
//	}
//	public String getEnc_h_address3() {
//		return enc_h_address3;
//	}
//	public void setEnc_h_address3(String enc_h_address3) {
//		this.enc_h_address3 = enc_h_address3;
//	}
//	public String getDoro_post() {
//		return doro_post;
//	}
//	public void setDoro_post(String doro_post) {
//		this.doro_post = doro_post;
//	}
//	public String getDoro_addr1() {
//		return doro_addr1;
//	}
//	public void setDoro_addr1(String doro_addr1) {
//		this.doro_addr1 = doro_addr1;
//	}
//	public String getDoro_addr2() {
//		return doro_addr2;
//	}
//	public void setDoro_addr2(String doro_addr2) {
//		this.doro_addr2 = doro_addr2;
//	}
//	public String getDoro_addr3() {
//		return doro_addr3;
//	}
//	public void setDoro_addr3(String doro_addr3) {
//		this.doro_addr3 = doro_addr3;
//	}
//	public String getDoro_addr4() {
//		return doro_addr4;
//	}
//	public void setDoro_addr4(String doro_addr4) {
//		this.doro_addr4 = doro_addr4;
//	}
//	public String getDoro_addr_yn() {
//		return doro_addr_yn;
//	}
//	public void setDoro_addr_yn(String doro_addr_yn) {
//		this.doro_addr_yn = doro_addr_yn;
//	}
//	public String getCompany_nm() {
//		return company_nm;
//	}
//	public void setCompany_nm(String company_nm) {
//		this.company_nm = company_nm;
//	}
//	public String getDepartment() {
//		return department;
//	}
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//	public String getMemo() {
//		return memo;
//	}
//	public void setMemo(String memo) {
//		this.memo = memo;
//	}
//	public String getOption1() {
//		return option1;
//	}
//	public void setOption1(String option1) {
//		this.option1 = option1;
//	}
//	public String getOption2() {
//		return option2;
//	}
//	public void setOption2(String option2) {
//		this.option2 = option2;
//	}
//	public String getOption3() {
//		return option3;
//	}
//	public void setOption3(String option3) {
//		this.option3 = option3;
//	}
//	public String getOption4() {
//		return option4;
//	}
//	public void setOption4(String option4) {
//		this.option4 = option4;
//	}
//	public String getOption5() {
//		return option5;
//	}
//	public void setOption5(String option5) {
//		this.option5 = option5;
//	}
//	public String getJoin_path() {
//		return join_path;
//	}
//	public void setJoin_path(String join_path) {
//		this.join_path = join_path;
//	}
//	public Date getCrt_dt() {
//		return crt_dt;
//	}
//	public void setCrt_dt(Date crt_dt) {
//		this.crt_dt = crt_dt;
//	}
//	public Date getUdt_dt() {
//		return udt_dt;
//	}
//	public void setUdt_dt(Date udt_dt) {
//		this.udt_dt = udt_dt;
//	}
//	public String getESNTL_ID() {
//		return ESNTL_ID;
//	}
//	public void setESNTL_ID(String eSNTL_ID) {
//		ESNTL_ID = eSNTL_ID;
//	}
//	public String getORGNZT_ID() {
//		return ORGNZT_ID;
//	}
//	public void setORGNZT_ID(String oRGNZT_ID) {
//		ORGNZT_ID = oRGNZT_ID;
//	}
//	public Date getLAST_LOGIN_PNTTM() {
//		return LAST_LOGIN_PNTTM;
//	}
//	public void setLAST_LOGIN_PNTTM(Date lAST_LOGIN_PNTTM) {
//		LAST_LOGIN_PNTTM = lAST_LOGIN_PNTTM;
//	}
//	public String getLAST_LOGIN_IP() {
//		return LAST_LOGIN_IP;
//	}
//	public void setLAST_LOGIN_IP(String lAST_LOGIN_IP) {
//		LAST_LOGIN_IP = lAST_LOGIN_IP;
//	}
//	public String getCRTFC_NO() {
//		return CRTFC_NO;
//	}
//	public void setCRTFC_NO(String cRTFC_NO) {
//		CRTFC_NO = cRTFC_NO;
//	}
//	public String getLOGIN_TRY_CNT() {
//		return LOGIN_TRY_CNT;
//	}
//	public void setLOGIN_TRY_CNT(String lOGIN_TRY_CNT) {
//		LOGIN_TRY_CNT = lOGIN_TRY_CNT;
//	}
//	public String getWorking_year() {
//		return working_year;
//	}
//	public void setWorking_year(String working_year) {
//		this.working_year = working_year;
//	}
//	public String getLicense() {
//		return license;
//	}
//	public void setLicense(String license) {
//		this.license = license;
//	}
//	public String getPractician_yn() {
//		return practician_yn;
//	}
//	public void setPractician_yn(String practician_yn) {
//		this.practician_yn = practician_yn;
//	}
//	public Date getPASSWORD_LAST_PNTTM() {
//		return PASSWORD_LAST_PNTTM;
//	}
//	public void setPASSWORD_LAST_PNTTM(Date pASSWORD_LAST_PNTTM) {
//		PASSWORD_LAST_PNTTM = pASSWORD_LAST_PNTTM;
//	}
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//}