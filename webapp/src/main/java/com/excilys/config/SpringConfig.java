package com.excilys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.WebApplicationInitializer;

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
@ComponentScan(basePackages = 
		{"com.excilys.binding"
		,"com.excilys.console"
		,"com.excilys.core"
		,"com.excilys.persistence"
		,"com.excilys.service"
		,"com.excilys.controller"})
public class SpringConfig implements WebApplicationInitializer   {
	

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class, MVCConfig.class, HibernateConfig.class);
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
