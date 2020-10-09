package bld.generator.report.db2.persistence.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * The Interface UserEntityRepository.
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,String>,JpaSpecificationExecutor<UserEntity> {
	
}
