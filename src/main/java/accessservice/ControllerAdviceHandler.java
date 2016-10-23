package accessservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerAdviceHandler {
	@ResponseBody
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Client token is invalid")
	@ExceptionHandler(value = HttpClientErrorException.class)
	public void httpClientExceptionHandler() {

	}
}
