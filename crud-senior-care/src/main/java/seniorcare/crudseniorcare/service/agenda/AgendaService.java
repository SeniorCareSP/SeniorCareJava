package seniorcare.crudseniorcare.service.agenda;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.agenda.repository.AgendaRepository;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.dto.AgendaCriacaoDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;

    public List<Agenda> list(){ return repository.findAll();}

    public Agenda byId(UUID id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Agenda")
        );
    }

    public Agenda create(Agenda novaAgenda){
        return repository.save(novaAgenda);
    }

    public void delete(UUID id){
        Optional<Agenda> agenda = repository.findById(id);
        if (agenda.isEmpty()){
            throw new NaoEncontradoException("Agenda");
        }
        repository.delete(agenda.get());
    }

    public Agenda update(UUID id, Agenda agenda){
        Optional<Agenda> agendaOpt = repository.findById(id);

        if (agendaOpt.isEmpty()) {
            throw new NaoEncontradoException("Agenda");
        }
        Agenda uptAgenda = agendaOpt.get();

        uptAgenda.setUsuario(agenda.getUsuario());
        //uptAgenda.setDiaDaSemana(agenda.getDiaDaSemana());
        uptAgenda.setDisponibilidade(agenda.getDisponibilidade());

        return uptAgenda;
    }





}
