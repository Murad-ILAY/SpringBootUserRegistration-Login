package Demo.user.registration.and.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class CodeJavaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeJavaAppApplication.class, args);
	}

}
