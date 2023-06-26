package com.cpan252.clothes;

import java.math.BigDecimal;

import com.cpan252.clothes.model.Cloth;
import com.cpan252.clothes.model.Cloth.Brand;
import com.cpan252.clothes.repository.ClothRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.cpan252.clothes.controller.HomeController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


/**
 * mvn spring-boot:run does following steps
 * 1. mvn clean
 * 2. mvn compile
 * 3. mvn package
 * 4. java -jar target/tekkenreborn-0.0.1-SNAPSHOT.jar
 * 5. deploys jar to embedded tomcat
 */
@SpringBootApplication
public class ClothesApplication {

	/**
	 * This is the main method that starts the application
	 * Spring Application Context is created here
	 * You can configure your application properties using @param args
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ClothesApplication.class, args);
		HomeController controller = context.getBean(HomeController.class);
	}
	@Bean
	public CommandLineRunner dataLoader(ClothRepository repository) {
		return args -> {
			repository.save(Cloth.builder()
					.name("cloth BBB")
					.yearcreated(2021)
					.brand(Cloth.Brand.GUCCI)
					.price(new BigDecimal(2009.1)).build());

			repository.save(Cloth.builder()
					.name("AAA")
					.yearcreated(2022)
					.brand(Brand.CHANEL)
					.price(new BigDecimal(3510)).build());

			repository.save(Cloth.builder()
					.name("CCC")
					.yearcreated(2022)
					.brand(Brand.CHANEL)
					.price(new BigDecimal(3510)).build());


		};
	}


}
