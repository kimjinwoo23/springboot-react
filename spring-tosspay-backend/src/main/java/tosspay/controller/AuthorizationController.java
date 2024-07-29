package tosspay.controller;


import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthorizationController {
	
	// springboot에서 application.properties에 작성한 값을 가져오기 위해
	// @Value 어노테이션을 사용
	// @Value("${application.properties에 작성한 변수명 작성}")
	// lombok 아님!!
	// org.springframework.beans.factory.annotation.Value;
	@Value("${apiSecretKey}")
	private String apiSecretKey; //가져온 값을 담을 변수그릇 설정
	
	// HTTP 요청을 보내기 위해서 요청 보내는 것을 담을 공간 생성
	private final RestTemplate restTemplate = new RestTemplate();
	
	//const encryptedWidgetSecretKey = "Basic " + Buffer.from(widgetSecretKey + ":").toString("base64");
	//const encryptedApiSecretKey = "Basic " + Buffer.from(apiSecretKey + ":").toString("base64");
	// 주어진 비밀키를 Base64로 인코딩해서 HTTP 헤더에 비밀키를 가져갈수 있도록 설정
	/*** Base64 사람이 작성한 데이터를 컴퓨터가 읽을 수 있는 텍스트 형식으로 변환하는 방법
	 * 예를 들어 Hello라고 작성한 내용을 Base64를 이용해서 컴퓨터가 읽을 수 있도록 인코딩
	 * 사람언어 : Hello
	 * 컴퓨터언어로 변환 : SGVsbG8=
	 */
	private String encodeSecretKey(String secretKey) {
		return "Basic " + new String(Base64.getEncoder().encode(    (secretKey + ":").getBytes()     ));
	}
	/**
	 // 브랜드페이 Access Token 발급
		app.get("/callback-auth", function (req, res) {
  		const { customerKey, code } = req.query;
  		fetch("https://api.tosspayments.com/v1/brandpay/authorizations/access-token", {
  		method: "POST",
  		headers: {
	 */
	
	@GetMapping("/callback-auth") //app.get("/callback-auth",
	// function  (req, res) { const { customerKey, code } = req.query;
	// function  기능명이 생략가능해서 없을 뿐  (req, res) { const { customerKey, code } = req.query;
	// 자바는 기능명이 존재
	// function  callbackAuth (req, res) { const { customerKey, code } = req.query;
	public ResponseEntity<?> callbackAuth(@RequestParam String customerKey,  @RequestParam String code) {
		//fetch("https://api.tosspayments.com/v1/brandpay/authorizations/access-token", {
		String url = "https://api.tosspayments.com/v1/brandpay/authorizations/access-token";
		HttpHeaders headers = new HttpHeaders();
		// Authorization: encryptedApiSecretKey,
	    //  "Content-Type": "application/json",
	    //},
		headers.set("Authorization", encodeSecretKey(apiSecretKey));
		headers.set("Content-Type","application/json");
		
		Map<String,String> requestBody = Map.of(
				"grantType" , "AuthorizationCode",
				"customerKey" , customerKey,
				"code" , code
				);
		HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestBody,headers);
		
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST,entity,Map.class);
		return new ResponseEntity<>(response.getBody(),response.getStatusCode());
	}
	
	
}







