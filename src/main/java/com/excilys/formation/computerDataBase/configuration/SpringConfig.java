package com.excilys.formation.computerDataBase.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

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
		{"com.excilys.formation.computerDataBase.persistence"
		,"com.excilys.formation.computerDataBase.service"
		,"com.excilys.formation.computerDataBase.controller"
		,"com.excilys.formation.computerDataBase.mapper"
		,"com.excilys.formation.computerDataBase.validator"})
public class SpringConfig extends AbstractContextLoaderInitializer   {
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		context.register(HibernateConfig.class);
		return context;
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		AnnotationConfigWebApplicationContext serveltContext = new AnnotationConfigWebApplicationContext();
		serveltContext.register(SpringConfig.class,MVCConfig.class,HibernateConfig.class);
		serveltContext.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet", new DispatcherServlet(serveltContext));
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
	
//	@Bean
//    public PlatformTransactionManager txManager() {
//        return new DataSourceTransactionManager(hikariDataSource());
//    }



}
