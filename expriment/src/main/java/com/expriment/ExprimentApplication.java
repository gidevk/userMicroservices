package com.expriment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
//@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

@ComponentScan(basePackages = {"com.expriment"})
@ServletComponentScan(basePackages ="com.expriment.utils")
//@EntityScan("com.expriment")
//@EnableJpaRepositories("com.expriment.utils.audit.DAO")
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableScheduling
@EnableSwagger2
class ExprimentApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ExprimentApplication.class, args);
	}

//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){
//		return hemf.getSessionFactory();
//	}
}
