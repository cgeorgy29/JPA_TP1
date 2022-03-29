package monprojet.entity;
import javax.persistence.*;
import java.util.*;
import org.hibernate.annotations.Proxy;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Proxy(lazy = false)
@Entity // Une entité JPA
public class Country {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @Column(unique=true)
    @NonNull
    private String code;
    
    @Column(unique=true)
    @NonNull
    private String name;
    @OneToMany(mappedBy="country")

    @ToString.Exclude // On ne veut pas inclure la liste des villes dans le toString
    private List<City> cities = new ArrayList<>();
}
