package a1;

import java.util.Date;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
    
//    @Controller
//    public static class StaticContent{}
    
    @Controller
	protected static class HomeController {

		@RequestMapping("/")
		@Secured("ROLE_ADMIN")
		public String home(Map<String, Object> model) {
			model.put("message", "Welcome: " + SecurityContextHolder.getContext().getAuthentication().toString());
			model.put("title", "Spring Boot Config Server");
			model.put("date", new Date());
			return "home";
		}

	}
}
