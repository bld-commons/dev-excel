package bld.generator.report.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bld.generator.report.excel.config.MultipleDatabaseConfiguration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = Db2DatabaseConfiguration.DB2_ENTITY_MANAGER_FACTORY, basePackages = { Db2DatabaseConfiguration.PACKAGE_SUP })
@EnableTransactionManagement
@EntityScan(basePackages = { Db2DatabaseConfiguration.PACKAGE_SUP })
@ConditionalOnProperty(name = "bld.commons.multiple.datasource", havingValue = "true", matchIfMissing = false)
public class Db2DatabaseConfiguration extends MultipleDatabaseConfiguration {

	/** The Constant PACKAGES_SUP. */
	public static final String PACKAGE_SUP = "bld.generator.report.db2.persistence.domain";

	/** The Constant ORACLE_TRANSACTION_MANAGER. */
	public static final String DB2_TRANSACTION_MANAGER = "postgres2TransactionManager";

	/** The Constant ORACLE_JPA. */
	private static final String DB2_JPA = "postgres2.jpa";

	/** The Constant JPA_VENDOR_ADAPTER_ORACLE. */
	private static final String JPA_VENDOR_ADAPTER_DB2 = "jpaVendorAdapterPostgres2";

	/** The Constant ORACLE. */
	private static final String DB2 = "postgres2";

	/** The Constant ORACLE_DATASOURCE. */
	private static final String DB2_DATASOURCE = "postgres2.datasource";

	/** The Constant ORACLE_DATA_SOURCE. */
	private static final String DB2_DATA_SOURCE = "postgres2DataSource";

	public static final String PERSISTENCE_DB2 = "persistencePostgres2";

	public final static String DB2_ENTITY_MANAGER_FACTORY = "postgres2EntityManagerFactory";

	public final static String DB2_ENTITY_MANAGER = "postgres2EntityManager";

	public final static String JDBC_TEMPLATE = "postgres2JdbcTemplate";


	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	@Bean(name = DB2_DATA_SOURCE)
	@ConfigurationProperties(prefix = DB2_DATASOURCE)
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	/**
	 * Oracle entity manager factory.
	 *
	 * @param builder          the builder
	 * @param dataSource       the data source
	 * @param jpaVendorAdapter the jpa vendor adapter
	 * @return the local container entity manager factory bean
	 */
	@PersistenceContext(unitName = PERSISTENCE_DB2)
	@Bean(name = DB2_ENTITY_MANAGER_FACTORY)
	@ConfigurationProperties(prefix = DB2)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(DB2_DATA_SOURCE) DataSource dataSource, @Qualifier(JPA_VENDOR_ADAPTER_DB2) JpaVendorAdapter jpaVendorAdapter) {
		return super.getEntityManagerFactory(builder, dataSource, jpaVendorAdapter);
	}

	/**
	 * Jpa vendor adapter oracle.
	 *
	 * @return the jpa vendor adapter
	 */
	@Bean(JPA_VENDOR_ADAPTER_DB2)
	@ConfigurationProperties(prefix = DB2_JPA)
	public JpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	/**
	 * Oracle transaction manager.
	 *
	 * @param builder    the builder
	 * @param lcemf      the lcemf
	 * @param dataSource the data source
	 * @return the platform transaction manager
	 */
	@Bean(name = DB2_TRANSACTION_MANAGER)
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder, @Qualifier(DB2_ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean lcemf, @Qualifier(DB2_DATA_SOURCE) DataSource dataSource) {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(lcemf.getObject());
		tm.setDataSource(dataSource);
		return tm;
	}

	@Bean(JDBC_TEMPLATE)
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier(DB2_DATA_SOURCE) DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	protected String getEntityPackage() {
		return PACKAGE_SUP;
	}

	@Override
	protected String getPersistenceUnit() {
		return PERSISTENCE_DB2;
	}

	@Override
	@Bean(DB2_ENTITY_MANAGER)
	public EntityManager entityManager(@Qualifier(DB2_ENTITY_MANAGER_FACTORY)LocalContainerEntityManagerFactoryBean lcemf) { 
		return super.getEntityManager(lcemf);
	}

}
