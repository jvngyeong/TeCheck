package DDL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DDL.command.BookCommand;
import DDL.service.book.BookFormService;
import DDL.service.book.BookInsertService;
import DDL.service.book.BookMyListService;
import DDL.service.goods.GoodsListService;
import DDL.service.store.StoreListService;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("book")
public class BookController {
	@Autowired
	BookFormService bookFormService;
	@Autowired
	BookInsertService bookInsertService;
	@Autowired
	BookMyListService bookMyListService;
	@GetMapping("bookForm")
	public String bookForm(Model model) {
		bookFormService.execute(model);
		return "thymeleaf/book/bookForm";
	}
	@Autowired
	GoodsListService goodsListService;
	@GetMapping("goodsItem")
	public String goodsItem(Model model) {
		goodsListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	@Autowired
	StoreListService storeListService;
	@GetMapping("storeSelect")
	public String storeSelect(Model model) {
		storeListService.execute(model);
		return "thymeleaf/book/storeSelect";
	}
	@PostMapping("bookWrite")
	public String bookWrite(@Validated BookCommand bookCommand
			, BindingResult result
			, HttpSession session) {
		if (result.hasErrors()) {
			return "redirect:bookForm";
		}
		bookInsertService.execute(bookCommand, session);
		return "redirect:/";
	}
	@GetMapping("bookList")
	public String bookList(Model model, HttpSession session) {
		bookMyListService.execute(model, session);
		return "thymeleaf/book/bookList";
	}
	
	
}
