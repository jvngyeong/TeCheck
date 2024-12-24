package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.GoodsIpgoCommand;
import DDL.mapper.AutoNumMapper;
import DDL.service.goodsIpgo.GoodsIpgoListService;

@Controller
@RequestMapping("goods")
public class GoodsIpgoController {
	@Autowired
	GoodsIpgoListService goodsIpgoListService;
	@Autowired
	AutoNumMapper autoNumMapper;
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
	
}
