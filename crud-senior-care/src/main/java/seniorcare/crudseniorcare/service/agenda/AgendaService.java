package seniorcare.crudseniorcare.service.agenda;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.agenda.repository.AgendaRepository;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
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

    public Agenda byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Agenda")
        );
    }

    public Agenda create(Agenda novaAgenda){
        if (novaAgenda == null) return null;
        return repository.save(novaAgenda);
    }

    public void delete(Integer id){
        Optional<Agenda> agenda = repository.findById(id);
        if (agenda.isEmpty()){
            throw new NaoEncontradoException("Agenda");
        }
        repository.delete(agenda.get());
    }

    public Agenda update(Integer id, Agenda agenda){
        Optional<Agenda> agendaOpt = repository.findById(id);

        if (agendaOpt.isEmpty()) {
            throw new NaoEncontradoException("Agenda");
        }
        agenda.setIdAgenda(id);
        return repository.save(agenda);
    }





}
