package com.expriment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


//@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

@ComponentScan(basePackages = {"com.expriment"})
//@EntityScan("com.expriment")
//@EnableJpaRepositories("com.expriment.utils.audit.DAO")
@SpringBootApplication
class ExprimentApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ExprimentApplication.class, args);
	}

//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
//		return hemf.getSessionFactory();
//	}
}
