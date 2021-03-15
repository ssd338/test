package home.cmm.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import home.cmm.service.CmmService;

@Controller
public class CmmController {
	
	@Resource(name="cmmService")
	private CmmService cmmService;
	
	/* 칼럼명과 테이블을 주면 다음 숫자를 만들어줌 */
	public int NextNo(
			String cm, String table
			) throws Exception {
		
		int re = cmmService.nextNo();
		return re;
	}
	
}
