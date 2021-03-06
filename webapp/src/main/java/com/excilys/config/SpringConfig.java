package com.excilys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.WebApplicationInitializer;

import com.excilys.rest.controller.ComputerRestController;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.excilys.controller"})
public class SpringConfig implements WebApplicationInitializer   {
	

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(CoreConfig.class,PersitenceConfig.class,ServiceConfig.class,SpringConfig.class, MVCConfig.class, HibernateConfig.class,SpringSecurityConfig.class,SpringSecurityInitializer.class,ComputerRestController.class);
		context.setServletContext(servletContext);
		
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet", new DispatcherServlet(context));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

	@Bean
	public DataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/hikariConfig.properties"));
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplet() {
		return new NamedParameterJdbcTemplate(hikariDataSource());
	}
}
