package tosspay.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController {
	//value값 이용해서 apiSecretKey 가져오기
	
	//RestTemplate header와 body 최종으로 작성할 공간 생성
	
	//Map 같이 만들기
	private final Map<String, String> billingMap = new ConcurrentHashMap<>();
	
	
	//server.js confirm-billing url을 참조해서 코드 완성하기
	@PostMapping("/confirm-billing")
	public ResponseEntity<?> confirmBilling(@RequestBody Map<String, String> requestBody) {
		String billingKey = billingMap.get(requestBody.get("customerkey"));
		String url = "" + billingKey;
		//HttpHeaders 와 return new까지 완성
		
		
		
	}
}