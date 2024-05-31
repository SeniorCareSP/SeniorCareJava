package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.endereco.Endereco;
import seniorcare.crudseniorcare.domain.idioma.Idioma;
import seniorcare.crudseniorcare.domain.idoso.Idoso;
import seniorcare.crudseniorcare.domain.usuario.Cuidador;
import seniorcare.crudseniorcare.domain.usuario.Responsavel;
import seniorcare.crudseniorcare.domain.usuario.Usuario;
import seniorcare.crudseniorcare.domain.usuario.repository.CuidadorRepository;
import seniorcare.crudseniorcare.domain.usuario.repository.UsuarioRepository;
import seniorcare.crudseniorcare.exception.ConflitoException;
import seniorcare.crudseniorcare.exception.NaoEncontradoException;
import seniorcare.crudseniorcare.service.agenda.AgendaService;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.idioma.IdiomaService;

import java.util.List;
import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class CuidadorService {
        private final CuidadorRepository repository;
        private final UsuarioRepository usuarioRepository;
        private final PasswordEncoder passwordEncoder;
        private final EnderecoService enderecoService;
        private final AgendaService agendaService;
        private final IdiomaService idiomaService;
        public Cuidador criar(Cuidador novoCuidador) {
            String senhaCriptografada = passwordEncoder.encode(novoCuidador.getSenha());
            novoCuidador.setSenha(senhaCriptografada);
            if (emailJaExiste(novoCuidador.getEmail())) {
                throw new ConflitoException("Email Cuidador");
            }

            Endereco endereco = enderecoService.create(novoCuidador.getEndereco());
            Agenda agenda = agendaService.create(novoCuidador.getAgenda());

            novoCuidador.setEndereco(endereco);
            novoCuidador.setAgenda(agenda);
            Cuidador usuarioSalvo = repository.save(novoCuidador);


            for (Idioma idioma : usuarioSalvo.getIdiomas()){
                idioma.setUsuario(usuarioSalvo);
                idiomaService.create(idioma);
            }

            return usuarioSalvo;

        }



    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(email);
        return emailUsuario.isPresent();
    }

    public List<Cuidador> list(){ return repository.findAll();}

    public Cuidador byId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Cuidador")
        );
    }

    public void delete(Integer id){
        Optional<Cuidador> cuidador = repository.findById(id);
        if (cuidador.isEmpty()){
            throw new NaoEncontradoException("Cuidador");
        }
        repository.delete(cuidador.get());
    }

        public Cuidador update(Integer id, Cuidador cuidador){
            Optional<Cuidador> cuidadadorOpt = repository.findById(id);

            if (cuidadadorOpt.isEmpty()) {
                throw new NaoEncontradoException("Cuidador");
            }

            cuidador.setIdUsuario(id);
            return repository.save(cuidador);
        }

    }
