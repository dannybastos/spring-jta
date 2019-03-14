package com.example.spring.jta.bank.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.example.spring.jta.AbstractConfig;

import bitronix.tm.TransactionManagerServices;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages= {"com.example.spring.jta.bank"}, 
		entityManagerFactoryRef="bankAccountEntityManagerFactory"
		)
public class BankAccountConfig extends AbstractConfig {

	private static final String JDBC_H2_MEM_BANK = "jdbc:h2:mem:bank";

	@Primary
	@Bean(name = "bankAccountEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean bankAccountEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("bankAccountdataSource") DataSource dataSource) {
		Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.transaction.jta.platform","org.hibernate.engine.transaction.jta.platform.internal.BitronixJtaPlatform");
		return builder
				.dataSource(dataSource)
				.packages("com.example.spring.jta.bank.model")
				.properties(properties)
				.persistenceUnit("BankAccount")
				.jta(true)
				.build();
	}

	@Primary
	@Bean(name = "bankAccountdataSource")
	public DataSource dataSourceBankAccount() throws Exception {
		return createXADataSource(JDBC_H2_MEM_BANK);
	}
	
	@Bean(name="bankAccountTransactionManager")
	public TransactionManager bankTransactionManager() {
		return TransactionManagerServices.getTransactionManager();
	}
	
	@Primary
	@Bean(name = "jtaTransactionManager")
	public PlatformTransactionManager bankTransactionManager(TransactionManager bankTransactionManager) {
		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
		jtaTransactionManager.setTransactionManager(bankTransactionManager);
		jtaTransactionManager.setAllowCustomIsolationLevels(true);
		return jtaTransactionManager;
	}
}
