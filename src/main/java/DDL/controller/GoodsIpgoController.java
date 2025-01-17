package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DDL.command.GoodsIpgoCommand;
import DDL.mapper.AutoNumMapper;
import DDL.service.goods.GoodsListService;
import DDL.service.goodsIpgo.GoodsIpgoListService;
import DDL.service.goodsIpgo.GoodsIpgoService;
import DDL.service.goodsIpgo.IpgoDeleteService;
import DDL.service.goodsIpgo.IpgoDetailService;
import DDL.service.goodsIpgo.IpgoUpdateService;
import DDL.service.goodsOrder.GoodsOrderListService;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("goods")
public class GoodsIpgoController {
	@Autowired
	GoodsIpgoListService goodsIpgoListService;
	@Autowired
	AutoNumMapper autoNumMapper;
	@Autowired
	GoodsIpgoService goodsIpgoService;
	@Autowired
	IpgoDetailService ipgoDetailService;
	@Autowired
	IpgoUpdateService ipgoUpdateService;
	@Autowired
	IpgoDeleteService ipgoDeleteService;
	@Autowired
	GoodsOrderListService goodsOrderListService;
	@GetMapping("goodsIpgoList")
	public String goodsIpgoList(Model model) {
		goodsIpgoListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsIpgoList";
	}
	@GetMapping("goodsIpgo")
	public String goodsIpgo(Model model, GoodsIpgoCommand goodsIpgoCommand) {
		String ipgoNum = autoNumMapper.getAutoNum("ipgo_", "6", "IPGO_NUM", "goods_ipgo");
		goodsIpgoCommand.setIpgoNum(ipgoNum);
		model.addAttribute("goodsIpgoCommand", goodsIpgoCommand);
		return "thymeleaf/goodsIpgo/goodsIpgo";
	}
	@PostMapping("ipgoRegist")
	public String ipgoRegist(@Validated GoodsIpgoCommand goodsIpgoCommand
			, BindingResult result
			, HttpSession session) {
		if (result.hasErrors()) {
			return "thymeleaf/goodsIpgo/goodsIpgo";
		}
		goodsIpgoService.execute(goodsIpgoCommand, session);
		return "redirect:goodsIpgoList";
	}
	@Autowired
	GoodsListService goodsListService;
	@GetMapping("goodsItem")
	public String goodsItem(@RequestParam(value="searchWord" , required = false) String searchWord
			, @RequestParam(value = "page" , required = false , defaultValue = "1") int page
			, Model model, HttpSession session) {
		goodsOrderListService.execute(model);
		goodsListService.execute(null, model, -1, session);
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	@GetMapping("goodsIpgoDetail")
	public String goodsIpgoDetail(String ipgoNum, String goodsNum, Model model) {
		ipgoDetailService.execute(ipgoNum, goodsNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgoDetail";
	}
	@GetMapping("goodsIpgoUpdate")
	public String goodsIpgoUpdate(String ipgoNum, String goodsNum, Model model) {
		ipgoDetailService.execute(ipgoNum, goodsNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgoUpdate";
	}
	@PostMapping("goodsIpgoModify")
	public String goodsIpgoModify(GoodsIpgoCommand goodsIpgoCommand) {
		ipgoUpdateService.execute(goodsIpgoCommand);
		return "redirect:goodsIpgoDetail?ipgoNum=" + goodsIpgoCommand.getIpgoNum() 
						+ "&goodsNum="+goodsIpgoCommand.getGoodsNum();
	}
	@GetMapping("goodsIpgoDelete")
	public String goodsIpgoDelete(String ipgoNum, String goodsNum) {
		ipgoDeleteService.execute(ipgoNum, goodsNum);
		return "redirect:goodsIpgoList";
	}
	
}
