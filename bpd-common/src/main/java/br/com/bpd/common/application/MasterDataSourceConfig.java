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
public class MasterDataSourceConfig {

	@Value("#{systemProperties['spring.datasource.url.master']}")
	private String masterUrl;

	@Value("#{systemProperties['spring.datasource.username.master']}")
	private String masterUsername;

	@Value("#{systemProperties['spring.datasource.password.master']}")
	private String masterPassword;

	@Value("#{systemProperties['spring.datasource.driver-class-name.master']}")
	private String masterDriverClassName;

	@Value("#{systemProperties['spring.jpa.properties.hibernate.dialect.master']}")
	private String masterJpaHibernateDialect;

	@Value("#{systemProperties['spring.jpa.show-sql.master']}")
	private String masterShowSql;

	@Bean
	public DataSource masterDatasource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(masterUrl);
		dataSource.setUsername(masterUsername);
		dataSource.setPassword(masterPassword);
		dataSource.setDriverClassName(masterDriverClassName);

		return dataSource;
	}

	@Bean(name = "masterEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean masterEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(masterDatasource());

		// Scan Entities in Package:
		em.setPackagesToScan(new String[] { "br.com.bpd.common.bean" });
		em.setPersistenceUnitName("masterEntityManager");

		//
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		em.setJpaVendorAdapter(vendorAdapter);

		HashMap<String, Object> properties = new HashMap<>();

		// JPA & Hibernate
		properties.put("hibernate.dialect", masterJpaHibernateDialect);
		properties.put("hibernate.show-sql", masterShowSql);

		em.setJpaPropertyMap(properties);
		em.afterPropertiesSet();
		
		return em;
	}

	@Bean(name = "masterTransactionManager")
	public PlatformTransactionManager masterTransactionManager() {
		return new JpaTransactionManager(masterEntityManager().getObject());
	}

}
