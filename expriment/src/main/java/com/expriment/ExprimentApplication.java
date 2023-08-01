package com.expriment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


//@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

@ComponentScan(basePackages = {"com.expriment"})
//@EntityScan("com.expriment")
//@EnableJpaRepositories("com.expriment.utils.audit.DAO")
@SpringBootApplication
@EnableScheduling
class ExprimentApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ExprimentApplication.class, args);
	}

//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
//		return hemf.getSessionFactory();
//	}
}
