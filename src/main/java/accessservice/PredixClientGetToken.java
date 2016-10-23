package accessservice;

import java.util.Arrays;
import java.util.LinkedHashMap;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import accessservice.model.AccessTokenInfo;

@Configuration
public class PredixClientGetToken {
	public static final String REST_SERVICE_URI = "https://0309d52b-b4a7-4edf-adc0-d3f8455a54de.predix-uaa.run.aws-usw02-pr.ice.predix.io";
	public static final String AUTH_SERVER_URI = "https://0309d52b-b4a7-4edf-adc0-d3f8455a54de.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token";
	public static final String GRANT_URI = "?client_id=gagan-ref&grant_type=client_credentials";
	public static final String CHECP_URI = "/check_token";

	/**
	 * Prepare HTTP headers
	 */
	public static HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return httpHeaders;
	}

	/**
	 * Add authentication header
	 */
	public static HttpHeaders getHeadersWithClientCredentials() {
		String credentials = "gagan-ref:ARIHANT";
		String baseClientCredentials = new String(
				org.apache.commons.net.util.Base64.encodeBase64(credentials.getBytes()));
		System.out.println("baseClientCredentials : " + baseClientCredentials);
		HttpHeaders headers = getHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + baseClientCredentials);
		return headers;
	}

	/**
	 * post request
	 */
	public static AccessTokenInfo sendToTokenRequest() {
		AccessTokenInfo accessTokenInfo = new AccessTokenInfo();
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> request = new HttpEntity<>(getHeadersWithClientCredentials());
		ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI + GRANT_URI, HttpMethod.POST, request,
				Object.class);
		LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();
		String access_token = (String) map.get("access_token");
		accessTokenInfo.setAccess_token(access_token);
		String token_type = (String) map.get("token_type");
		accessTokenInfo.setToken_type(token_type);
		int expires_in = (Integer) map.get("expires_in");
		accessTokenInfo.setExpires_in(expires_in);
		String scope = (String) map.get("scope");
		accessTokenInfo.setScope(scope);
		String jti = (String) map.get("jti");
		accessTokenInfo.setJti(jti);
		System.out.println("access token: " + access_token);
		return accessTokenInfo;
	}

	public static int checkToken(String token, String authorization) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.add(HttpHeaders.AUTHORIZATION, authorization);
		HttpEntity<String> request = new HttpEntity<>(httpHeaders);
		int value = restTemplate
				.exchange(REST_SERVICE_URI + CHECP_URI + "?token=" + token, HttpMethod.POST, request, Object.class)
				.getStatusCode().value();
		return value;
	}
}
