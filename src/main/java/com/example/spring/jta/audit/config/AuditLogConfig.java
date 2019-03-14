package com.example.spring.jta.audit.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.spring.jta.AbstractConfig;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages= {"com.example.spring.jta.audit"}, 
		entityManagerFactoryRef="auditLogEntityManagerFactory"
		)
public class AuditLogConfig extends AbstractConfig {

	private static final String JDBC_H2_MEM_AUDIT = "jdbc:h2:mem:audit";

	@Bean(name = "auditLogEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean auditLogEntityManagerFactory(
			EntityManagerFactoryBuilder builder,
			@Qualifier("dataSourceAuditLog") DataSource dataSource)  {
		Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.transaction.jta.platform","org.hibernate.engine.transaction.jta.platform.internal.BitronixJtaPlatform");
		return builder
				.dataSource(dataSource)
				.packages("com.example.spring.jta.audit.model")
				.persistenceUnit("audit")
				.properties(properties)
				.jta(true)
				.build();
	}

	@Bean(name = "jdbcTemplateAuditLog")
	public JdbcTemplate getJdbcTemplateAuditLog(@Qualifier("dataSourceAuditLog") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "dataSourceAuditLog")
	public DataSource dataSourceAuditLog() throws Exception {
		return createXADataSource(JDBC_H2_MEM_AUDIT);
	}
}
