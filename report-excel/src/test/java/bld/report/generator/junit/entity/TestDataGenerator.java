/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.generator.junit.entity.TestDataGenerator
 */
package bld.report.generator.junit.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Utility class that generates varied, reproducible random test data for use in unit tests.
 * <p>
 * An instance is created with a fixed {@code seed} so test runs are deterministic.
 * Italian first names, surnames, and department names are used for {@link EmployeeRow} data;
 * Italian product categories and supplier names are used for {@link ProductRow} data.
 * </p>
 */
public class TestDataGenerator {

    private static final String[] NOMI = {
        "Marco", "Luca", "Andrea", "Francesco", "Matteo", "Alessandro", "Davide", "Simone",
        "Roberto", "Stefano", "Giovanni", "Riccardo", "Fabio", "Massimo", "Daniele",
        "Sofia", "Giulia", "Martina", "Sara", "Chiara", "Valentina", "Federica", "Laura",
        "Paola", "Elisa", "Monica", "Serena", "Ilaria", "Alessia", "Francesca",
        "Antonio", "Giuseppe", "Salvatore", "Vincenzo", "Bruno", "Carmelo", "Pasquale",
        "Maria", "Anna", "Rosa", "Angela", "Giuseppina", "Concetta", "Caterina"
    };

    private static final String[] COGNOMI = {
        "Rossi", "Russo", "Ferrari", "Esposito", "Bianchi", "Romano", "Colombo", "Ricci",
        "Marino", "Greco", "Bruno", "Gallo", "Conti", "De Luca", "Mancini", "Costa",
        "Giordano", "Rizzo", "Lombardi", "Moretti", "Barbieri", "Fontana", "Santoro",
        "Mariani", "Rinaldi", "Caruso", "Ferretti", "Basile", "Gentile", "Martini",
        "Palumbo", "Serra", "Conte", "Longo", "Cattaneo", "Montanari", "Guerra",
        "Pellegrini", "Testa", "Valentini", "Benedetti", "Barone", "Monti", "Villa"
    };

    private static final String[] DIPARTIMENTI = {
        "Engineering", "Marketing", "Human Resources", "Finance", "Sales",
        "Operations", "Legal", "Research & Development", "Customer Support",
        "Procurement", "IT Infrastructure", "Data Analytics", "Product Management",
        "Quality Assurance", "Business Development", "Communications", "Compliance"
    };

    private static final String[] NOTE_PATTERNS = {
        "Dipendente senior con ottima performance",
        "In formazione avanzata",
        "Assegnato al progetto strategico",
        "Referente per il team",
        "Valutazione eccellente",
        "Remote working approvato",
        "Candidato alla promozione",
        "Nuovo assunto",
        "Trasferito dalla sede di Milano",
        "Esperto di dominio",
        "Responsabile di area",
        "In aspettativa parziale",
        "Mentor del team junior",
        "Certificazione in corso",
        "Collaborazione cross-team attiva",
        null, null, null
    };

    private static final String[] CATEGORIE_PRODOTTO = {
        "Elettronica", "Abbigliamento", "Alimentari", "Informatica", "Sport",
        "Casa & Giardino", "Libri", "Giocattoli", "Automotive", "Salute & Bellezza",
        "Musica", "Cinema", "Bricolage", "Gioielleria", "Ottica", "Ferramenta",
        "Cancelleria", "Animali", "Viaggi", "Arte & Creatività"
    };

    private static final String[] FORNITORI = {
        "Alfa Distribuzione Srl", "Beta Supply SpA", "Gamma Logistics", "Delta Trade",
        "Epsilon Commerce", "Zeta Import Export", "Eta Global Srl", "Theta Partners",
        "Iota Wholesale", "Kappa Industries", "Lambda Solutions", "Mu Forniture",
        "Nu Technologies", "Xi Materiali", "Omicron Retail", "Pi Distribution"
    };

    private static final String[][] DESCRIZIONI_PRODOTTO = {
        { "Base", "Pro", "Elite", "Premium", "Standard", "Advanced", "Plus", "Max", "Lite", "Ultra" },
        { "Modello A", "Modello B", "Modello C", "Serie X", "Serie Y", "Serie Z",
          "Edizione Speciale", "Versione 2", "Versione 3", "Classic", "Next Gen" }
    };

    private final Random random;
    private final Calendar cal;

    public TestDataGenerator(long seed) {
        this.random = new Random(seed);
        this.cal = Calendar.getInstance();
    }

    /**
     * Generates a list of {@code count} randomised {@link EmployeeRow} instances.
     * <p>
     * Hire dates are distributed across 2005–2022; salaries range from 18,000 to 100,000;
     * roughly 80 % of employees are marked active. A note is randomly omitted (null)
     * for some rows to exercise nullable-field handling.
     * </p>
     *
     * @param count the number of employee rows to generate; must be positive
     * @return a new mutable list of {@code count} {@link EmployeeRow} objects
     */
    public List<EmployeeRow> generateEmployees(int count) {
        List<EmployeeRow> list = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            cal.set(2005 + random.nextInt(18), random.nextInt(12), 1 + random.nextInt(28));
            String nome = NOMI[random.nextInt(NOMI.length)];
            String cognome = COGNOMI[random.nextInt(COGNOMI.length)];
            String dipartimento = DIPARTIMENTI[random.nextInt(DIPARTIMENTI.length)];
            double stipendio = 18000.0 + random.nextInt(82000) + Math.round(random.nextDouble() * 100.0) / 100.0;
            boolean attivo = random.nextInt(10) > 1;
            String nota = NOTE_PATTERNS[random.nextInt(NOTE_PATTERNS.length)];
            list.add(new EmployeeRow(i, nome, cognome, dipartimento, cal.getTime(), stipendio, attivo, nota));
        }
        return list;
    }

    /**
     * Generates a list of {@code count} randomised {@link ProductRow} instances.
     * <p>
     * Product codes follow the pattern {@code PRD-NNNNN}; descriptions are assembled
     * from a prefix, suffix, and category name. Creation dates are distributed across
     * 2015–2024; prices range from 1.00 to 5,000.00; roughly 80 % of products are
     * marked as available.
     * </p>
     *
     * @param count the number of product rows to generate; must be positive
     * @return a new mutable list of {@code count} {@link ProductRow} objects
     */
    public List<ProductRow> generateProducts(int count) {
        List<ProductRow> list = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            cal.set(2015 + random.nextInt(10), random.nextInt(12), 1 + random.nextInt(28));
            String codice = String.format("PRD-%05d", i);
            String prefisso = DESCRIZIONI_PRODOTTO[0][random.nextInt(DESCRIZIONI_PRODOTTO[0].length)];
            String suffisso = DESCRIZIONI_PRODOTTO[1][random.nextInt(DESCRIZIONI_PRODOTTO[1].length)];
            String categoria = CATEGORIE_PRODOTTO[random.nextInt(CATEGORIE_PRODOTTO.length)];
            String fornitore = FORNITORI[random.nextInt(FORNITORI.length)];
            double prezzo = 1.0 + random.nextInt(4999) + Math.round(random.nextDouble() * 100.0) / 100.0;
            int quantita = random.nextInt(5000);
            boolean disponibile = random.nextInt(5) > 0;
            list.add(new ProductRow(codice, prefisso + " " + suffisso + " - " + categoria,
                    categoria, fornitore, prezzo, quantita, cal.getTime(), disponibile));
        }
        return list;
    }
}
