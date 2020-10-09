package bld.generator.report.excel.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class MultipleDatabaseConfiguration {

	public MultipleDatabaseConfiguration() {
		super();
	}

	public abstract DataSource dataSource();

	protected abstract String getPackageSup();

	protected abstract String getPersistenceUnit();

	public abstract LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource, JpaVendorAdapter jpaVendorAdapter);

	public abstract JpaVendorAdapter jpaVendorAdapter();

	public abstract PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder, LocalContainerEntityManagerFactoryBean lcemf, DataSource dataSource);

	public abstract NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource);

	public abstract EntityManager entityManager(LocalContainerEntityManagerFactoryBean lcemf);

	protected LocalContainerEntityManagerFactoryBean getEntityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource).packages(getPackageSup()).persistenceUnit(getPersistenceUnit()).build();
		em.setJpaVendorAdapter(jpaVendorAdapter);
		return em;
	}

	protected PlatformTransactionManager getTransactionManager(EntityManagerFactoryBuilder builder, LocalContainerEntityManagerFactoryBean lcemf, DataSource dataSource) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(lcemf.getObject());
		tm.setDataSource(dataSource);
		return tm;
	}

	protected EntityManager getEntityManager(LocalContainerEntityManagerFactoryBean lcemf) {
		EntityManagerFactory emf = lcemf.getObject();
		return emf.createEntityManager();
	}

}