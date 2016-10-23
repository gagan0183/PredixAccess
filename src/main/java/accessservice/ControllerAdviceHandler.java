package accessservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@RestController
public class ControllerAdviceHandler {
	// @ResponseBody
	// @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Client token is
	// invalid")
	@ExceptionHandler(value = HttpClientErrorException.class)
	public accessservice.ExceptionHandler httpClientExceptionHandler(HttpServletRequest request, Exception exception) {
		accessservice.ExceptionHandler exceptionHandler = new accessservice.ExceptionHandler();
		exceptionHandler.setStatus(HttpStatus.BAD_REQUEST.value());
		exceptionHandler.setMessage("Client token is not correct");
		exceptionHandler.setPath(request.getRequestURI());
		return exceptionHandler;
	}
}
