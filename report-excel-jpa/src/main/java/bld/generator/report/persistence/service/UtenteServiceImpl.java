package bld.generator.report.persistence.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bld.generator.report.persistence.domain.Utente;
import bld.generator.report.persistence.domain.UtenteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    
    @PersistenceContext
    private EntityManager manager;
    
    public long countAllUtentes() {
        return utenteRepository.count();
    }
    public void deleteUtente(Utente utente) {
        utenteRepository.delete(utente);
    }
    public Utente findUtente(Integer id) {
        return utenteRepository.findById(id).get();
    }
    public List<Utente> findAllUtentes() {
        return utenteRepository.findAll();
    }
    public List<Utente> findUtenteEntries(int firstResult, int maxResults) {
        return utenteRepository.findAll(PageRequest.of(firstResult / maxResults, maxResults)).getContent();
    }
    public void saveUtente(Utente utente) {
        utenteRepository.save(utente);
    }
    public Utente updateUtente(Utente utente) {
        return utenteRepository.save(utente);
    }
	@Override
	public void updateImage(byte[] image,String path) {
		String sql="update Utente u set u.image=:image,u.path=:path";
		Query query = this.manager.createQuery(sql);
		query.setParameter("image", image);
		query.setParameter("path", path);
		query.executeUpdate();
	}
}
