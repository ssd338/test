package home.join.service;

import java.util.Date;

/**
 * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  JJY            최초 생성
 *   2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성 
 *
 * </pre>
 */
public class JoinVO{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String usrNo;          // 사용자 no
    private String usrId;          // 사용자 아이디
    private String encUsrPw;      // 사용자 비밀번호
    private String birthday;       // 생년월일
    private String sexCd;          // 성별
    private String encUsrNm;       // 사용자명
    private String engNm;          // 사용자 영문이름
    private String nickNm;          // 사용자 닉네임
    private String levelNo;       // 사용자 레벨
    private String vlevelNo;       // 평가센터 사용자 레벨
    private String encEmail;       // 이메일
    private String emailReceiveYn; // 이메일 수신여부
    private String homepage;        // 홈페이지
    private String encMobileNo;    // 휴대폰
    private String smsEeceiveYn;    // 문자수신여부
    private String encPhoneNo;    // 전화번호
    private String jobCd;          // 직업코드
    private String encHZipcode;    // 지역번호
    private String encHAddress1;    // 주소1
    private String encHAddress2;    // 주소2
    private String encHAddress3;    // 상세주소
    private String doroPost;       // 도로명 지역번호
    private String doroAddr1;       // 도로명 주소1
    private String doroAddr2;       // 도로명 주소2
    private String doroAddr3;       // 도로명 주소3
    private String doroAddr4;       // 도로명 주소4
    private String doroAddrYn;    // 도로명 주소 사용여부
    private String companyNm;       // 업체명
    private String department;       // 부서
    private String memo;          // 메모
    private String option1;          // 소속
    private String option2;          // 소속기관명      
    private String option3;          // 대표자명
    private String option4;          // 설립년도
    private String option5;          // 지역(아마도 사용자 시군구)
    private String joinPath;       // 승인 계정
    private Date crtDt;          // 등록일
    private Date udtDt;          // 수정일
    private String ESNTLID;       // 권한 아이디
    private String ORGNZTID;       // 조직 아이디
    private Date LASTLOGINPNTTM; // 최종로그인 일자
    private String LASTLOGINIP;    // 최종 로그인 아이피
    private String CRTFCNO;       // 인증번호
    private String LOGINTRYCNT;    // 로그인 시도 횟수
    private String workingYear;    // 연차
    private String license;          // 자격증
    private String practicianYn;    // 법적종사자 유무
    private Date PASSWORDLASTPNTTM; // 패스워드 마지막 패턴
    
	public String getUsrNo() {
		return usrNo;
	}
	public void setUsrNo(String usrNo) {
		this.usrNo = usrNo;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
	public String getEncUsrPw() {
		return encUsrPw;
	}
	public void setEncUsrPw(String encUsrPw) {
		this.encUsrPw = encUsrPw;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSexCd() {
		return sexCd;
	}
	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}
	public String getEncUsrNm() {
		return encUsrNm;
	}
	public void setEncUsrNm(String encUsrNm) {
		this.encUsrNm = encUsrNm;
	}
	public String getEngNm() {
		return engNm;
	}
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}
	public String getNickNm() {
		return nickNm;
	}
	public void setNickNm(String nickNm) {
		this.nickNm = nickNm;
	}
	public String getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(String levelNo) {
		this.levelNo = levelNo;
	}
	public String getVlevelNo() {
		return vlevelNo;
	}
	public void setVlevelNo(String vlevelNo) {
		this.vlevelNo = vlevelNo;
	}
	public String getEncEmail() {
		return encEmail;
	}
	public void setEncEmail(String encEmail) {
		this.encEmail = encEmail;
	}
	public String getEmailReceiveYn() {
		return emailReceiveYn;
	}
	public void setEmailReceiveYn(String emailReceiveYn) {
		this.emailReceiveYn = emailReceiveYn;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getEncMobileNo() {
		return encMobileNo;
	}
	public void setEncMobileNo(String encMobileNo) {
		this.encMobileNo = encMobileNo;
	}
	public String getSmsEeceiveYn() {
		return smsEeceiveYn;
	}
	public void setSmsEeceiveYn(String smsEeceiveYn) {
		this.smsEeceiveYn = smsEeceiveYn;
	}
	public String getEncPhoneNo() {
		return encPhoneNo;
	}
	public void setEncPhoneNo(String encPhoneNo) {
		this.encPhoneNo = encPhoneNo;
	}
	public String getJobCd() {
		return jobCd;
	}
	public void setJobCd(String jobCd) {
		this.jobCd = jobCd;
	}
	public String getEncHZipcode() {
		return encHZipcode;
	}
	public void setEncHZipcode(String encHZipcode) {
		this.encHZipcode = encHZipcode;
	}
	public String getEncHAddress1() {
		return encHAddress1;
	}
	public void setEncHAddress1(String encHAddress1) {
		this.encHAddress1 = encHAddress1;
	}
	public String getEncHAddress2() {
		return encHAddress2;
	}
	public void setEncHAddress2(String encHAddress2) {
		this.encHAddress2 = encHAddress2;
	}
	public String getEncHAddress3() {
		return encHAddress3;
	}
	public void setEncHAddress3(String encHAddress3) {
		this.encHAddress3 = encHAddress3;
	}
	public String getDoroPost() {
		return doroPost;
	}
	public void setDoroPost(String doroPost) {
		this.doroPost = doroPost;
	}
	public String getDoroAddr1() {
		return doroAddr1;
	}
	public void setDoroAddr1(String doroAddr1) {
		this.doroAddr1 = doroAddr1;
	}
	public String getDoroAddr2() {
		return doroAddr2;
	}
	public void setDoroAddr2(String doroAddr2) {
		this.doroAddr2 = doroAddr2;
	}
	public String getDoroAddr3() {
		return doroAddr3;
	}
	public void setDoroAddr3(String doroAddr3) {
		this.doroAddr3 = doroAddr3;
	}
	public String getDoroAddr4() {
		return doroAddr4;
	}
	public void setDoroAddr4(String doroAddr4) {
		this.doroAddr4 = doroAddr4;
	}
	public String getDoroAddrYn() {
		return doroAddrYn;
	}
	public void setDoroAddrYn(String doroAddrYn) {
		this.doroAddrYn = doroAddrYn;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public String getJoinPath() {
		return joinPath;
	}
	public void setJoinPath(String joinPath) {
		this.joinPath = joinPath;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public Date getUdtDt() {
		return udtDt;
	}
	public void setUdtDt(Date udtDt) {
		this.udtDt = udtDt;
	}
	public String getESNTLID() {
		return ESNTLID;
	}
	public void setESNTLID(String eSNTLID) {
		ESNTLID = eSNTLID;
	}
	public String getORGNZTID() {
		return ORGNZTID;
	}
	public void setORGNZTID(String oRGNZTID) {
		ORGNZTID = oRGNZTID;
	}
	public Date getLASTLOGINPNTTM() {
		return LASTLOGINPNTTM;
	}
	public void setLASTLOGINPNTTM(Date lASTLOGINPNTTM) {
		LASTLOGINPNTTM = lASTLOGINPNTTM;
	}
	public String getLASTLOGINIP() {
		return LASTLOGINIP;
	}
	public void setLASTLOGINIP(String lASTLOGINIP) {
		LASTLOGINIP = lASTLOGINIP;
	}
	public String getCRTFCNO() {
		return CRTFCNO;
	}
	public void setCRTFCNO(String cRTFCNO) {
		CRTFCNO = cRTFCNO;
	}
	public String getLOGINTRYCNT() {
		return LOGINTRYCNT;
	}
	public void setLOGINTRYCNT(String lOGINTRYCNT) {
		LOGINTRYCNT = lOGINTRYCNT;
	}
	public String getWorkingYear() {
		return workingYear;
	}
	public void setWorkingYear(String workingYear) {
		this.workingYear = workingYear;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getPracticianYn() {
		return practicianYn;
	}
	public void setPracticianYn(String practicianYn) {
		this.practicianYn = practicianYn;
	}
	public Date getPASSWORDLASTPNTTM() {
		return PASSWORDLASTPNTTM;
	}
	public void setPASSWORDLASTPNTTM(Date pASSWORDLASTPNTTM) {
		PASSWORDLASTPNTTM = pASSWORDLASTPNTTM;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}