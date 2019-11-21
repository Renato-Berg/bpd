package br.com.bpd.common.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SlaveDataSourceConfig {

	@Value("#{systemProperties['spring.datasource.url.slave'] ?: 'jdbc:mysql://localhost:3306/bpdDb?useTimezone=true&serverTimezone=UTC'}")
	private String slaveUrl;

	@Value("#{systemProperties['spring.datasource.username.slave'] ?: 'bpdUser'}")
	private String slaveUsername;

	@Value("#{systemProperties['spring.datasource.password.slave'] ?: '@Gh0st!'}")
	private String slavePassword;

	@Value("#{systemProperties['spring.datasource.driver-class-name.slave'] ?: 'com.mysql.cj.jdbc.Driver'}")
	private String slaveDriverClassName;

	@Value("#{systemProperties['spring.jpa.properties.hibernate.dialect.slave'] ?: 'org.hibernate.dialect.MySQLDialect'}")
	private String slaveJpaHibernateDialect;

	@Value("#{systemProperties['spring.jpa.show-sql.slave'] ?: true}")
	private String slaveShowSql;

	@Bean(name = "slaveDatasource")
	public DataSource slaveDatasource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(slaveUrl);
		dataSource.setUsername(slaveUsername);
		dataSource.setPassword(slavePassword);
		dataSource.setDriverClassName(slaveDriverClassName);

		return dataSource;
	}

	@Bean(name = "slaveEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean slaveEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(slaveDatasource());

		// Scan Entities in Package:
		em.setPackagesToScan(new String[] { "br.com.bpd.common.bean" });
		em.setPersistenceUnitName("slaveEntityManager");

		//
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<String, Object>();

		// JPA & Hibernate
		properties.put("hibernate.dialect", slaveJpaHibernateDialect);
		properties.put("hibernate.show-sql", slaveShowSql);

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		
		return em;
	}

	@Bean(name = "slaveTransactionManager")
	public PlatformTransactionManager slaveTransactionManager() {
		return new JpaTransactionManager(slaveEntityManager().getObject());
	}

}
