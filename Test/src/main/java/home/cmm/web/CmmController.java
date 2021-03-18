package home.cmm.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import home.cmm.service.CmmService;
import home.cmm.service.SigunguVO;

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
	
	@RequestMapping(value="/home/cmm/sigunguList.do",method = RequestMethod.POST )
	@ResponseBody
	public List<SigunguVO> sigunguList(@RequestParam HashMap map) throws Exception {
		String sidoCd = (String)map.get("sidoCd");
		List<SigunguVO> sigungu = cmmService.getSigungu(sidoCd);
		return sigungu;
	}
	
}
