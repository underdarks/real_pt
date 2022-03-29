package health.real_pt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RealPtApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealPtApplication.class, args);
	}

}
