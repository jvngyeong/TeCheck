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

import DDL.command.BookCommand;
import DDL.service.book.BookDeleteService;
import DDL.service.book.BookFormService;
import DDL.service.book.BookInfoService;
import DDL.service.book.BookInsertService;
import DDL.service.book.BookMyListService;
import DDL.service.book.BookStatusUpdateService;
import DDL.service.book.BookUpdateService;
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
	
	@Autowired
	BookInfoService bookInfoService;
	
	@Autowired
	BookUpdateService bookUpdateService;
	
	@Autowired
	BookDeleteService bookDeleteService;
	
	@Autowired
	BookStatusUpdateService bookStatusUpdateService;
	@GetMapping("bookForm")
	public String bookForm(Model model, String goodsNum, String goodsName) {
		bookFormService.execute(model);
		model.addAttribute("goodsName", goodsName);
		model.addAttribute("goodsNum", goodsNum);
		return "thymeleaf/book/bookForm";
	}
	@Autowired
	GoodsListService goodsListService;
	@GetMapping("goodsItem")
	public String goodsItem(@RequestParam(value="searchWord" , required = false) String searchWord
			, @RequestParam(value = "page" , required = false , defaultValue = "1") int page
			, Model model) {
		goodsListService.execute(null, model, -1);
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
	@GetMapping("bookDetail")
	public String bookDetail(Model model,HttpSession session, String bookNum) {
		String result = bookInfoService.execute(model, session, bookNum);
		if(result == "200") {
			return "thymeleaf/book/bookDetail";
		}else {
			return "/";
		}
	}
	@GetMapping("bookModify")
	public String bookModify(Model model,HttpSession session, String bookNum) {
		bookFormService.execute(model);
		String result = bookInfoService.execute(model, session, bookNum);
		if(result == "200") {
			return "thymeleaf/book/bookModify";
		}else {
			return "/";
		}
	}
	@PostMapping("bookUpdate")
	public String bookUpdate(@Validated BookCommand bookCommand
			, BindingResult result
			, HttpSession session) {
		bookUpdateService.execute(bookCommand, session);
		return "redirect:bookDetail?bookNum=" + bookCommand.getBookNum();
	}
	@GetMapping("bookDelete")
	public String bookDelete(String bookNum) {
		bookDeleteService.execute(bookNum);
		return "redirect:bookList";
	}
	@GetMapping("empBookList")
	public String empBookList(Model model, HttpSession session) {
		bookMyListService.execute(model, session);
		return "thymeleaf/book/empBookList";
	}
	@GetMapping("bookStatusUpdate")
	public String bookStatusUpdate(String bookNum, String bookStatus) {
		bookStatusUpdateService.execute(bookNum, bookStatus);
		return "redirect:empBookList";
	}
	@GetMapping("memBookStatusUpdate")
	public String memBookStatusUpdate(String bookNum, String bookStatus) {
		bookStatusUpdateService.execute(bookNum, bookStatus);
		return "redirect:bookList";
	}
	
}
