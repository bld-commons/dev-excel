/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.config.MultipleDatabaseConfiguration.java
 */
package com.bld.generator.report.excel.config;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

/**
 * The Class MultipleDatabaseConfiguration.<br>
 * The classes that will extend @{link bld.generator.report.excel.config.MultipleDatabaseConfiguration} will need 
 * to implement abstract functions to configuration the n data sources.<br>
 */
public abstract class MultipleDatabaseConfiguration {

	/**
	 * Instantiates a new multiple database configuration.
	 */
	public MultipleDatabaseConfiguration() {
		super();
	}

	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	public abstract DataSource dataSource();

	
	/**
	 * Gets the entity package.
	 *
	 * @return the entity package
	 */
	protected abstract String getEntityPackage();

	/**
	 * Gets the persistence unit.
	 *
	 * @return the persistence unit
	 */
	protected abstract String getPersistenceUnit();

	/**
	 * Entity manager factory.
	 *
	 * @param builder the builder
	 * @param dataSource the data source
	 * @param jpaVendorAdapter the jpa vendor adapter
	 * @return the local container entity manager factory bean
	 */
	public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource, JpaVendorAdapter jpaVendorAdapter);

	/**
	 * Jpa vendor adapter.
	 *
	 * @return the jpa vendor adapter
	 */
	public abstract JpaVendorAdapter jpaVendorAdapter();

	/**
	 * Transaction manager.
	 *
	 * @param builder the builder
	 * @param lcemf the lcemf
	 * @param dataSource the data source
	 * @return the platform transaction manager
	 */
	public abstract PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder, LocalContainerEntityManagerFactoryBean lcemf, DataSource dataSource);

	/**
	 * Named parameter jdbc template.
	 *
	 * @param dataSource the data source
	 * @return the named parameter jdbc template
	 */
	public abstract NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource);

	/**
	 * Entity manager.
	 *
	 * @param lcemf the lcemf
	 * @return the entity manager
	 */
	public abstract EntityManager entityManager(LocalContainerEntityManagerFactoryBean lcemf);

	/**
	 * Gets the entity manager factory.
	 *
	 * @param builder the builder
	 * @param dataSource the data source
	 * @param jpaVendorAdapter the jpa vendor adapter
	 * @return the entity manager factory
	 */
	protected LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource).packages(getEntityPackage()).persistenceUnit(getPersistenceUnit()).build();
		em.setJpaVendorAdapter(jpaVendorAdapter);
		return em;
	}

	/**
	 * Gets the transaction manager.
	 *
	 * @param builder the builder
	 * @param lcemf the lcemf
	 * @param dataSource the data source
	 * @return the transaction manager
	 */
	protected PlatformTransactionManager getTransactionManager(EntityManagerFactoryBuilder builder, LocalContainerEntityManagerFactoryBean lcemf, DataSource dataSource) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(lcemf.getObject());
		tm.setDataSource(dataSource);
		return tm;
	}

	/**
	 * Gets the entity manager.
	 *
	 * @param lcemf the lcemf
	 * @return the entity manager
	 */
	protected EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean lcemf) {
		EntityManagerFactory emf = lcemf.getObject();
		return emf.createEntityManager();
	}

}