package accessservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accessservice.PredixClientGetToken;
import accessservice.model.AccessTokenInfo;

@RestController
@RequestMapping("/accessToken")
public class PredixAccessController {
	@RequestMapping("/getAccessInfo")
	public AccessTokenInfo getAccessInfo(HttpServletRequest request) {
		String token = request.getHeader("Token");
		String authorization = request.getHeader("Authorization");
		System.out.println("token : " + token + " authorization : " + authorization);
		AccessTokenInfo accessTokenInfo = PredixClientGetToken.sendToTokenRequest();
		return accessTokenInfo;
	}

	@RequestMapping("/check")
	public int checkToken(HttpServletRequest request) {
		String token = request.getHeader("Token");
		String authorization = request.getHeader("Authorization");
		System.out.println("token : " + token + " authorization : " + authorization);
		return PredixClientGetToken.checkToken(token, authorization);
	}
}
