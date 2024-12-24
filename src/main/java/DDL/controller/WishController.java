package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import DDL.service.wish.WishCheckService;
import DDL.service.wish.WishMergeService;

@Controller
@RequestMapping("wish")
public class WishController {
	@Autowired
	WishMergeService wishMergeService;	
	
	@Autowired
	WishCheckService wishCheckService;
	
	@GetMapping("wishMerge")
	public @ResponseBody String wishMerge(String goodsNum, String memberNum) {
		if(memberNum == null || memberNum.equals("")) {
			return "000";
		}
		else if (memberNum.equals("emp")) {
			return "900";
		}
		else {
			wishMergeService.execute(goodsNum, memberNum);
			return "200";
		}
	}
	
	@GetMapping("wishCheck")
	public @ResponseBody String wishCheck(String goodsNum, String memberNum) {
		if(memberNum == null || memberNum.equals("")) {
			return "000";
		}
		else {
			return wishCheckService.execute(goodsNum, memberNum);
		}
	}
}
