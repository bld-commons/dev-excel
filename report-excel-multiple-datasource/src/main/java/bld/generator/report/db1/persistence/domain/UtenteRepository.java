package bld.generator.report.db1.persistence.domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface UtenteRepository extends JpaRepository<Utente,Integer>,JpaSpecificationExecutor<Utente> {
}
