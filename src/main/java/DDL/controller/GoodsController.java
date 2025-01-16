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
import org.springframework.web.bind.annotation.ResponseBody;

import DDL.command.GoodsCommand;
import DDL.service.goods.FileDelService;
import DDL.service.goods.GoodsDeleteService;
import DDL.service.goods.GoodsDetailService;
import DDL.service.goods.GoodsListService;
import DDL.service.goods.GoodsUpdateService;
import DDL.service.goods.GoodsWriteService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("goods")
public class GoodsController {
	@Autowired
    GoodsListService goodsListService;
    
    @Autowired
    GoodsWriteService goodsWriteService;
    
    @Autowired
    GoodsDetailService goodsDetailService;
    
    @Autowired
    GoodsUpdateService goodsUpdateService;
    
    @Autowired
    GoodsDeleteService goodsDeleteService;
	@GetMapping("goodsList")
	public String List(@RequestParam(value="searchWord" , required = false) String searchWord
			, @RequestParam(value = "page" , required = false , defaultValue = "1") int page
			, Model model, HttpSession session) {
		goodsListService.execute(searchWord, model, page, session);
		return "thymeleaf/goods/goodsList";
	}
	@GetMapping("goodsWrite")
	public String goodsWrite(GoodsCommand goodsCommand) {
		return "thymeleaf/goods/goodsWrite";
	}
	@PostMapping("goodsWrite")
	public String goodsWrite(@Validated GoodsCommand goodsCommand
			,BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsWrite";
		}
		goodsWriteService.execute(goodsCommand);
		return "redirect:/goods/goodsList";
	}
	@GetMapping("goodsDetail")
	public String goodsDetail(String goodsNum, Model model, HttpSession session) {
	    // 삭제할 파일을 선택한 후 다시 수정페이지로 오면 삭제할 파일정보를 가진 session이 존재하여 오류의 소지가 있다.
		// 그래서 수정 페이지에 오면 삭제할 파일정보를 가진 session을 제거 하는 것이 좋다. 
		session.removeAttribute("fileList"); // 삭제할 파일 정보를 가지고 있는 session삭제
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsDetail";
	}
	@GetMapping("goodsModify")
	public String goodsModify(String goodsNum, Model model, HttpSession session) {
		session.removeAttribute("fileList");
		goodsDetailService.execute(goodsNum, model);
		return "thymeleaf/goods/goodsModify";
	}
	@PostMapping("goodsModify")
	public String goodsModify(@Validated GoodsCommand goodsCommand
			, BindingResult result
			, HttpSession session
			, Model model) {
		goodsUpdateService.execute(goodsCommand, result, session, model);
		if(result.hasErrors()) {
			return "thymeleaf/goods/goodsModify";
		}
		return "redirect:/goods/goodsDetail?goodsNum="+goodsCommand.getGoodsNum();
	}
	@GetMapping("goodsDelete")
	public String goodsDelete(String goodsNum) {
		goodsDeleteService.execute(goodsNum);
		return "redirect:/goods/goodsList";
	}
}
