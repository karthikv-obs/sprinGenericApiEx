package api.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
/**
 * 
 * @author karthikeyan_v
 *
 */
@Configuration
public class DatabaseConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.username}")
	private String DB_USERNAME;
	
	@Value("${spring.datasource.password}")
	private String DB_PASSWORD;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.jooq.sql-dialect}")
	private String HIBERNATE_DIALECT;
	
	@Value("${spring.jpa.show-sql}")
	private String HIBERNATE_SHOW_SQL;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String HIBERNATE_HBM2DDL_AUTO;
	
	@Value("${spring.main.sources}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;
	
	@Bean
	public DataSource datasource(){
		DriverManagerDataSource datasource=null;
		try{
			datasource=new DriverManagerDataSource();
			datasource.setDriverClassName(DB_DRIVER);
			datasource.setUsername(DB_USERNAME);
			datasource.setPassword(DB_PASSWORD);
			datasource.setUrl(DB_URL);
		} catch (Exception e) {
			e.getMessage();
		}
		return datasource;		
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(datasource());
		sessionFactory.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		Properties props=new Properties();
		props.put("hibernate.dialect",HIBERNATE_DIALECT);
		props.put("hibernate.show_sql",HIBERNATE_SHOW_SQL);
		props.put("hibernate.hbm2ddl.auto",HIBERNATE_HBM2DDL_AUTO);
		sessionFactory.setHibernateProperties(props);
		return sessionFactory;		
	}
	
	@Bean
	public HibernateTransactionManager transaction(){
		HibernateTransactionManager transaction=new HibernateTransactionManager();
		transaction.setSessionFactory(sessionFactory().getObject());
		return transaction;
	}	
}