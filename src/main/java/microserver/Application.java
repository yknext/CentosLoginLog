package microserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microserver.task.ApplicationStartup;


@RestController
@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@EnableScheduling
//@EnableJms
public class Application {
	
	@RequestMapping("/")
	String home() {
		   //就不告诉你这是java
	       return "Hello PHP!";
	}
	
	public static void main(String[] args) {
		SpringApplication springApplication =new SpringApplication(Application.class);
//		springApplication.addListeners(new ApplicationStartup());//改为配置文件
		springApplication.run(args);
	}

}
