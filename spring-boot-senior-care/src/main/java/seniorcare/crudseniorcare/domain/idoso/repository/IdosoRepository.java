package seniorcare.crudseniorcare.domain.idoso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import seniorcare.crudseniorcare.domain.idoso.Idoso;

import java.util.List;
import java.util.UUID;

public interface IdosoRepository extends JpaRepository<Idoso, Integer> {
    @Query("SELECT " +
            "CASE " +
            "  WHEN YEAR(CURRENT_DATE) - YEAR(i.DtNascimento) < 70 THEN '60' " +
            "  WHEN YEAR(CURRENT_DATE) - YEAR(i.DtNascimento) < 80 THEN '70' " +
            "  WHEN YEAR(CURRENT_DATE) - YEAR(i.DtNascimento) < 90 THEN '80' " +
            "  WHEN YEAR(CURRENT_DATE) - YEAR(i.DtNascimento) < 100 THEN '90' " +
            "  ELSE '100+' " +
            "END AS faixaEtaria, " +
            "i.genero, COUNT(i) " +
            "FROM Idoso i " +
            "GROUP BY faixaEtaria, i.genero " +
            "ORDER BY faixaEtaria")
    List<Object[]> countByFaixaEtariaAndGenero();
}
