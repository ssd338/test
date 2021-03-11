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
    private String encEmail;       // 이메일
    private String encMobileNo;    // 휴대폰
    private String encPhoneNo;    // 전화번호
    private String encHZipcode;    // 지역번호
    private String encHAddress1;    // 주소1
    private String encHAddress2;    // 상세주소
    private String doroPost;       // 도로명 지역번호
    private String doroAddr1;       // 도로명 주소1
    private String doroAddr2;       // 도로명 주소2
    private String doroAddrYn;    // 도로명 주소 사용여부
    private String option1;          // 소속
    private String option2;          // 소속기관명      
    private String option3;          // 대표자명
    private String option4;          // 설립년도
    private Date crtDt;          // 등록일
    private Date udtDt;          // 수정일
    private String ESNTLID;       // 권한 아이디
    private Date LASTLOGINPNTTM; // 최종로그인 일자
    private String LASTLOGINIP;    // 최종 로그인 아이피
    private String CRTFCNO;       // 인증번호
    private String LOGINTRYCNT;    // 로그인 시도 횟수

    
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
	
	public String getEncEmail() {
		return encEmail;
	}
	public void setEncEmail(String encEmail) {
		this.encEmail = encEmail;
	}
	
	public String getEncMobileNo() {
		return encMobileNo;
	}
	public void setEncMobileNo(String encMobileNo) {
		this.encMobileNo = encMobileNo;
	}
	
	public String getEncPhoneNo() {
		return encPhoneNo;
	}
	public void setEncPhoneNo(String encPhoneNo) {
		this.encPhoneNo = encPhoneNo;
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
	
	public String getDoroAddrYn() {
		return doroAddrYn;
	}
	public void setDoroAddrYn(String doroAddrYn) {
		this.doroAddrYn = doroAddrYn;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}