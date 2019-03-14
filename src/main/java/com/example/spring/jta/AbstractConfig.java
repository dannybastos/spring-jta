package com.example.spring.jta;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.jta.bitronix.BitronixXADataSourceWrapper;

public abstract class AbstractConfig {
	
	protected DataSource createXADataSource(String url) throws Exception {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(url);
		ds.setUser("sa");
		BitronixXADataSourceWrapper wrapper = new BitronixXADataSourceWrapper();
		return wrapper.wrapDataSource(ds);
	}
	
}
