package seniorcare.crudseniorcare.domain.visita;


import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Table(name = "tb_visitas")
@Entity
@Getter
@Setter
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;


}
