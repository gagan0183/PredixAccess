package accessservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PredixAccessApplication {

	public static void main(String[] args) {
		// PredixClientGetToken.checkToken();
		SpringApplication.run(PredixAccessApplication.class, args);
	}
}
