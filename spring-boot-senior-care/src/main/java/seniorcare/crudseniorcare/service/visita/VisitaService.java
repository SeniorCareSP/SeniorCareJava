package seniorcare.crudseniorcare.service.visita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.visita.Visita;
import seniorcare.crudseniorcare.domain.visita.repository.VisitaRepository;

import java.time.LocalDate;

@Service

public class VisitaService {
    private final VisitaRepository visitaRepository;

    @Autowired
    public VisitaService(VisitaRepository visitaRepository) {
        this.visitaRepository = visitaRepository;
    }

    public void registrarVisita() {
        Visita visita = new Visita();
        visita.setData(LocalDate.now());
        visitaRepository.save(visita);
    }

    public long contarVisitasHoje() {
        LocalDate hoje = LocalDate.now();
        return visitaRepository.countByData(hoje);
    }

}
