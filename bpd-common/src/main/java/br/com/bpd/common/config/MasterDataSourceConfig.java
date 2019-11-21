package br.com.bpd.common.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan(basePackages = { "br.com.bpd.common.service", "br.com.bpd.common.repository" })
public class MasterDataSourceConfig {

	@Value("#{systemProperties['spring.datasource.url.master'] ?: 'jdbc:mysql://localhost:3306/bpdDb?useTimezone=true&serverTimezone=UTC'}")
	private String masterUrl;

	@Value("#{systemProperties['spring.datasource.username.master'] ?: 'bpdUser'}")
	private String masterUsername;

	@Value("#{systemProperties['spring.datasource.password.master'] ?: '@Gh0st!'}")
	private String masterPassword;

	@Value("#{systemProperties['spring.datasource.driver-class-name.master'] ?: 'com.mysql.cj.jdbc.Driver'}")
	private String masterDriverClassName;

	@Value("#{systemProperties['spring.jpa.properties.hibernate.dialect.master'] ?: 'org.hibernate.dialect.MySQLDialect'}")
	private String masterJpaHibernateDialect;

	@Value("#{systemProperties['spring.jpa.show-sql.master'] ?: true}")
	private String masterShowSql;

	@Bean(name = "masterDatasource")
	@Primary
	public DataSource masterDatasource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(masterUrl);
		dataSource.setUsername(masterUsername);
		dataSource.setPassword(masterPassword);
		dataSource.setDriverClassName(masterDriverClassName);

		return dataSource;
	}

	@Bean(name = "masterEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean masterEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(masterDatasource());

		// Scan Entities in Package:
		em.setPackagesToScan(new String[] { "br.com.bpd.common.bean" });
		em.setPersistenceUnitName("masterEntityManager");

		//
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<String, Object>();

		// JPA & Hibernate
		properties.put("hibernate.dialect", masterJpaHibernateDialect);
		properties.put("hibernate.show-sql", masterShowSql);

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		
		return em;
	}

	@Bean(name = "masterTransactionManager")
	@Primary
	public PlatformTransactionManager masterTransactionManager() {
		return new JpaTransactionManager(masterEntityManager().getObject());
	}

}
