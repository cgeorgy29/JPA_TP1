package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.dto.PopulationResult;
import monprojet.entity.City;
import monprojet.entity.Country;


public interface CountryRepository extends JpaRepository<Country, Integer> {

    
     // Calculer la population totale d'un pays.
    default int populationDuPaysJava(int idDuPays) {
        int resultat = 0;
        Country country = findById(idDuPays).orElseThrow();
        for (City c : country.getCities()) {
            resultat += c.getPopulation();
        }
        return resultat;
    }
//jpql
    @Query("SELECT SUM(c.population) FROM City c WHERE c.country.id = :idDuPays")
    int populationDuPaysJPQL(int idDuPays);
//sql
    @Query(value = "SELECT SUM(c.population) FROM City c WHERE c.country_id = :idDuPays", 
    nativeQuery = true)
    int populationDuPaysSQL(int idDuPays);
//jpql
    @Query("SELECT c.country.name AS countryName, SUM(c.population) AS populationTotale FROM City c GROUP BY countryName")
    List<PopulationResult> populationParPaysJPQL();
//sql
    @Query(value = "SELECT Country.name AS countryName, SUM(population) AS populationTotale FROM Country INNER JOIN City ON country_id = Country.id GROUP BY countryName", 
    nativeQuery = true)
    List<PopulationResult> populationParPaysSQL();

}

