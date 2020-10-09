package bld.generator.report.db1.persistence.service;
import java.util.List;

import bld.generator.report.db1.persistence.domain.Utente;
public interface UtenteService {
    public abstract long countAllUtentes();    
    public abstract void deleteUtente(Utente utente);    
    public abstract Utente findUtente(Integer id);    
    public abstract List<Utente> findAllUtentes();    
    public abstract List<Utente> findUtenteEntries(int firstResult, int maxResults);    
    public abstract void saveUtente(Utente utente);    
    public abstract Utente updateUtente(Utente utente);    
}
