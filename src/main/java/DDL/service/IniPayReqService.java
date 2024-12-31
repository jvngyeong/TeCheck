package DDL.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.inicis.std.util.SignatureUtil;

import DDL.domain.OrderDTO;
import DDL.mapper.OrderMapper;
import jakarta.servlet.http.HttpSession;
@Service
public class IniPayReqService {
   @Autowired
   OrderMapper orderMapper;
   public void execute(String orderNum, String goodsName, Model model, HttpSession session) throws Exception{
      OrderDTO dto = orderMapper.orderSelectOne(orderNum);
      String mid               = "INIpayTest";                          // 상점아이디               
      String signKey             = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";   // 웹 결제 signkey
      
      String mKey = SignatureUtil.hash(signKey, "SHA-256");
      String timestamp         = SignatureUtil.getTimestamp();         // util에 의해서 자동생성
      String orderNumber         = orderNum;   // 가맹점 주문번호(가맹점에서 직접 설정)
      String price            = String.valueOf(dto.getOrderPrice());                        // 상품가격(특수기호 제외, 가맹점에서 직접 설정)
      Map<String, String> signParam = new HashMap<String, String>();
      signParam.put("oid", orderNumber);
      signParam.put("price", price);
      signParam.put("timestamp", timestamp);
      String signature = SignatureUtil.makeSignature(signParam);   
      
      if(goodsName != null) {
    	  session.setAttribute("goodsName", goodsName);
      }
      model.addAttribute("mid", mid);
      model.addAttribute("orderNumber", orderNumber);
      model.addAttribute("price", price);
      model.addAttribute("timestamp", timestamp);
      model.addAttribute("signature", signature);
      model.addAttribute("mKey", mKey);
      model.addAttribute("deliveryPhone", dto.getDeliveryPhone());
   }
}