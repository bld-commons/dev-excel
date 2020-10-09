package bld.generator.report.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bld.generator.report.excel.config.MultipleDatabaseConfiguration;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = Db1DatabaseConfiguration.DB1_ENTITY_MANAGER_FACTORY,basePackages = { Db1DatabaseConfiguration.PACKAGE_SUP })
@EnableTransactionManagement
@EntityScan(basePackages = { Db1DatabaseConfiguration.PACKAGE_SUP })
public class Db1DatabaseConfiguration extends MultipleDatabaseConfiguration {
	
	
	/** The Constant PACKAGES_SUP. */
	public static final String PACKAGE_SUP = "bld.generator.report.db1.persistence.domain";
	
	/** The Constant ORACLE_TRANSACTION_MANAGER. */
	public static final String DB1_TRANSACTION_MANAGER = "postgresTransactionManager";
	
	/** The Constant ORACLE_JPA. */
	private static final String DB1_JPA = "postgres.jpa";
	
	/** The Constant JPA_VENDOR_ADAPTER_ORACLE. */
	private static final String JPA_VENDOR_ADAPTER_DB1 = "jpaVendorAdapterPostgres";
	
	/** The Constant ORACLE. */
	private static final String DB1 = "postgres";
	
	
	/** The Constant ORACLE_DATASOURCE. */
	private static final String DB1_DATASOURCE = "postgres.datasource";
	
	/** The Constant ORACLE_DATA_SOURCE. */
	private static final String DB1_DATA_SOURCE = "postgresDataSource";
	
	public static final String PERSISTENCE_DB1="persistencePostgres";
	
	public final static String DB1_ENTITY_MANAGER_FACTORY = "postgresEntityManagerFactory";
	
	public final static String DB1_ENTITY_MANAGER="postgresEntityManager";
	
	public final static String JDBC_TEMPLATE="postgresJdbcTemplate";
	

	/**
	 * Data source.
	 *
	 * @return the data source
	 */
	@Bean(name = DB1_DATA_SOURCE)
	@ConfigurationProperties(prefix = DB1_DATASOURCE)
	@Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
 
	/**
	 * Oracle entity manager factory.
	 *
	 * @param builder the builder
	 * @param dataSource the data source
	 * @param jpaVendorAdapter the jpa vendor adapter
	 * @return the local container entity manager factory bean
	 */
	@PersistenceContext(unitName=PERSISTENCE_DB1)
    @Bean(name = DB1_ENTITY_MANAGER_FACTORY)
	@ConfigurationProperties(prefix = DB1)
	@Primary
	@Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory( EntityManagerFactoryBuilder builder,
            @Qualifier(DB1_DATA_SOURCE)  DataSource dataSource,@Qualifier(JPA_VENDOR_ADAPTER_DB1) JpaVendorAdapter jpaVendorAdapter) {
    	return super.getEntityManagerFactory(builder, dataSource, jpaVendorAdapter);
    }
	
	/**
	 * Jpa vendor adapter oracle.
	 *
	 * @return the jpa vendor adapter
	 */
	@Bean(JPA_VENDOR_ADAPTER_DB1)
    @ConfigurationProperties(prefix = DB1_JPA)
	@Primary
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }
	
	/**
	 * Oracle transaction manager.
	 *
	 * @param builder the builder
	 * @param lcemf the lcemf
	 * @param dataSource the data source
	 * @return the platform transaction manager
	 */
	@Bean(name = DB1_TRANSACTION_MANAGER)
	@Primary
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder,@Qualifier(DB1_ENTITY_MANAGER_FACTORY)LocalContainerEntityManagerFactoryBean lcemf, @Qualifier(DB1_DATA_SOURCE) DataSource dataSource) {
	   return super.getTransactionManager(builder, lcemf, dataSource);
	}
	
	@Bean(JDBC_TEMPLATE)
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier(DB1_DATA_SOURCE)DataSource dataSource) {
	    return new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	protected String getPackageSup() {
		return PACKAGE_SUP;
	}

	@Override
	protected String getPersistenceUnit() {
		return PERSISTENCE_DB1;
	}

	@Override
	@Bean(DB1_ENTITY_MANAGER)
	public EntityManager entityManager(@Qualifier(DB1_ENTITY_MANAGER_FACTORY)LocalContainerEntityManagerFactoryBean lcemf) {
		return super.getEntityManager(lcemf);
	}


}
