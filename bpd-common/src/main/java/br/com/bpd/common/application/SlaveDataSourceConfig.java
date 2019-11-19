package br.com.bpd.common.application;

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

	@Value("#{systemProperties['spring.datasource.url.slave']}")
	private String slaveUrl;

	@Value("#{systemProperties['spring.datasource.username.slave']}")
	private String slaveUsername;

	@Value("#{systemProperties['spring.datasource.password.slave']}")
	private String slavePassword;

	@Value("#{systemProperties['spring.datasource.driver-class-name.slave']}")
	private String slaveDriverClassName;

	@Value("#{systemProperties['spring.jpa.properties.hibernate.dialect.slave']}")
	private String slaveJpaHibernateDialect;

	@Value("#{systemProperties['spring.jpa.show-sql.slave']}")
	private String slaveShowSql;

	@Bean
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

		HashMap<String, Object> properties = new HashMap<>();

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
