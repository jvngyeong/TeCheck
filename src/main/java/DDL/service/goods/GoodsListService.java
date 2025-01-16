package DDL.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import DDL.domain.AuthInfoDTO;
import DDL.domain.GoodsDTO;
import DDL.domain.StartEndPageDTO;
import DDL.mapper.GoodsMapper;
import DDL.mapper.MemberMapper;
import DDL.service.StartEndPageService;
import jakarta.servlet.http.HttpSession;


@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	StartEndPageService startEndPageService;
	
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(String searchWord, Model model, int page, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		model.addAttribute("memberNum", memberNum);
		if(searchWord == null && page < 0) {
			List<GoodsDTO> list = goodsMapper.goodsAllSelect();
			model.addAttribute("list", list);
		}else {
			// 한페이지에 보일 list
			int limit = 6;
			StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
			
			
			List<GoodsDTO> list = goodsMapper.goodsListSelect(sepDTO);
			int count = goodsMapper.goodsCount(searchWord);
			// 페이징
			startEndPageService.execute(page, limit, count, searchWord, list, model);
		}
	}
	
	public void execute(String searchWord, Model model, int page, String sortValue, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memberNum = memberMapper.getMemberNum(auth.getUserId());
		model.addAttribute("memberNum", memberNum);
		if(searchWord == null && page < 0) {
			List<GoodsDTO> list = goodsMapper.goodsAllSelect();
			model.addAttribute("list", list);
		}else {
			int limit = 6;
			StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
			sepDTO.setSortValue(sortValue);
			List<GoodsDTO> list = goodsMapper.goodsSortedListSelect(sepDTO);
			int count = goodsMapper.goodsCount(searchWord);
			startEndPageService.execute(page, limit, count, searchWord, list, model);
		}
	}
}
