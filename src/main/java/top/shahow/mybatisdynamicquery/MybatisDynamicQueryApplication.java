package top.shahow.mybatisdynamicquery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "top.shahow.mybatisdynamicquery")
@MapperScan(basePackages = "top.shahow.mybatisdynamicquery")
public class MybatisDynamicQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisDynamicQueryApplication.class, args);
	}

}
