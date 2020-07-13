package com.excilys.formation.computerDataBase.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.excilys.formation.computerDataBase")
public class SpringConfig extends AbstractContextLoaderInitializer {

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringConfig.class);
		return context;
	}
	
	@Bean
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(new HikariConfig("/hikariConfig.properties"));
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplet() {
		return new NamedParameterJdbcTemplate(hikariDataSource());
	}
	
	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(hikariDataSource());
    }

}
