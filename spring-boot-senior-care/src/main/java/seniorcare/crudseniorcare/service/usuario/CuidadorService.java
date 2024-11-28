package seniorcare.crudseniorcare.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import seniorcare.crudseniorcare.domain.agenda.Agenda;
import seniorcare.crudseniorcare.domain.ajuda.Ajuda;
import seniorcare.crudseniorcare.domain.caracteristica.Caracteristica;
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
import seniorcare.crudseniorcare.service.agenda.dto.AgendaMapper;
import seniorcare.crudseniorcare.service.ajuda.AjudaService;
import seniorcare.crudseniorcare.service.caracteristica.CaracteristicaService;
import seniorcare.crudseniorcare.service.endereco.EnderecoService;
import seniorcare.crudseniorcare.service.endereco.dto.EnderecoMapper;
import seniorcare.crudseniorcare.service.geolocalizacao.CoordenadaService;
import seniorcare.crudseniorcare.service.idioma.IdiomaService;
import seniorcare.crudseniorcare.service.idioma.dto.IdiomaMapper;
import seniorcare.crudseniorcare.service.usuario.dto.cuidador.CuidadorAtualizacaoDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        private final AjudaService ajudaService;
        private final CaracteristicaService  caracteristicaService;
        private final CoordenadaService coordenadaService;
        public Cuidador criar(Cuidador novoCuidador) throws IOException {

            String senhaCriptografada = passwordEncoder.encode(novoCuidador.getSenha());
            novoCuidador.setSenha(senhaCriptografada);

            if (emailJaExiste(novoCuidador.getEmail())) {
                throw new ConflitoException("Email Cuidador");
            }

            Endereco endereco = (novoCuidador.getEndereco());
            Endereco enderecoCompleto = coordenadaService.pegarPosicoes(endereco);
            Cuidador usuarioSalvo = repository.save(novoCuidador);



            enderecoCompleto.setUsuario(usuarioSalvo);
            enderecoService.create(enderecoCompleto);


            Agenda agenda = usuarioSalvo.getAgenda();
            agenda.setUsuario(usuarioSalvo);
            agendaService.create(agenda);



            for (Idioma idioma : usuarioSalvo.getIdiomas()){
                idioma.setUsuario(usuarioSalvo);
                idiomaService.create(idioma);
            }
            for (Caracteristica caracteristica : usuarioSalvo.getCaracteristicas()){
                caracteristica.setCuidador(usuarioSalvo);
                caracteristicaService.create(caracteristica);
            }
            for (Ajuda ajuda : usuarioSalvo.getAjudas()){
                ajuda.setCuidador(usuarioSalvo);
                ajudaService.create(ajuda);
            }
            return usuarioSalvo;

        }



    public boolean emailJaExiste(String email) {
        Optional<Usuario> emailUsuario = usuarioRepository.findByEmailIgnoreCase(email);
        return emailUsuario.isPresent();
    }

    public List<Cuidador> list(){ return repository.findAll();}

    public Cuidador byId(Integer id){
        Cuidador cuidador = repository.findById(id).orElseThrow(
                () -> new NaoEncontradoException("Cuidador")
        );
//        List<Ajuda> ajudas =  ajudaService.buscarPorResponsavel(cuidador);
//        cuidador.setAjudas(ajudas);
        return cuidador;
    }

        public Optional<Cuidador> findById(Integer idUsuario) {
            return repository.findById(idUsuario);
        }

    public void delete(Integer id){
        Optional<Cuidador> cuidador = repository.findById(id);
        if (cuidador.isEmpty()){
            throw new NaoEncontradoException("Cuidador");
        }
        repository.delete(cuidador.get());
    }

        public Cuidador update(Integer id, CuidadorAtualizacaoDto cuidadorAtualizacaoDto) throws IOException {
            Optional<Cuidador> cuidadadorOpt = repository.findById(id);
            if (cuidadadorOpt.isEmpty()) {
                throw new NaoEncontradoException("Cuidador");
            }
            Cuidador cuidador = cuidadadorOpt.get();
            if (cuidadorAtualizacaoDto.getNome() != null){
                cuidador.setNome(cuidadorAtualizacaoDto.getNome());
            }
            if(cuidadorAtualizacaoDto.getImagemUrl() != null){
                cuidador.setImagemUrl(cuidadorAtualizacaoDto.getImagemUrl());
            }
            if (cuidadorAtualizacaoDto.getEmail() != null){
                cuidador.setEmail(cuidadorAtualizacaoDto.getEmail());
            }
            if (cuidadorAtualizacaoDto.getSenha() != null){
                cuidador.setSenha(cuidadorAtualizacaoDto.getSenha());
            }
            if (cuidadorAtualizacaoDto.getTelefone() != null){
                cuidador.setTelefone(cuidadorAtualizacaoDto.getTelefone());
            }
            if (cuidadorAtualizacaoDto.getCpf() != null){
                cuidador.setCpf(cuidadorAtualizacaoDto.getCpf());
            }
            if (cuidadorAtualizacaoDto.getSexoBiologico() != null){
                cuidador.setSexoBiologico(cuidadorAtualizacaoDto.getSexoBiologico());
            }
            if (cuidadorAtualizacaoDto.getDtNascimento() != null){
                cuidador.setDtNascimento(cuidadorAtualizacaoDto.getDtNascimento());
            }
            if(cuidadorAtualizacaoDto.getApresentacao() != null){
                cuidador.setApresentacao(cuidadorAtualizacaoDto.getApresentacao());
            }
            if (cuidadorAtualizacaoDto.getAgendas() != null) {
                Agenda novaAgenda = AgendaMapper.toEntity(cuidadorAtualizacaoDto.getAgendas());
                novaAgenda.setUsuario(cuidador);
                if (cuidador.getAgenda() != null && !novaAgenda.equals(cuidador.getAgenda())) {
                    agendaService.update(cuidador.getAgenda().getIdAgenda(), novaAgenda);
                } else {
                    cuidador.setAgenda(novaAgenda);
                }
            }
            if (cuidadorAtualizacaoDto.getExperiencia() != null){
                cuidador.setExperiencia(cuidadorAtualizacaoDto.getExperiencia());
            }
            if(cuidadorAtualizacaoDto.getFaixaEtaria() != null){
                cuidador.setFaixaEtaria(cuidadorAtualizacaoDto.getFaixaEtaria());
            }

            if (cuidadorAtualizacaoDto.getEndereco() != null){
                Endereco novoEndereco = EnderecoMapper.toEndereco(cuidadorAtualizacaoDto.getEndereco());
                novoEndereco.setUsuario(cuidador);
                Endereco endereco = coordenadaService.pegarPosicoes(novoEndereco);
                novoEndereco.setLatidude(endereco.getLatidude());
                novoEndereco.setLatidude(endereco.getLatidude());
                if (cuidador.getEndereco() != null && !novoEndereco.equals(cuidador.getEndereco())) {
                    enderecoService.update(cuidador.getEndereco().getIdEndereco(), novoEndereco);
                } else {
                    cuidador.setEndereco(novoEndereco);
                }
            }
            cuidador.setIdUsuario(id);

            return repository.save(cuidador);

        }


        public Map<String, Long> contarCuidadoresPorGenero() {
            List<Object[]> results = repository.countByGeneroGroupByGenero();
            Map<String, Long> contagemPorGenero = new HashMap<>();
            for (Object[] result : results) {
                String genero = (String) result[0];
                Long count = (Long) result[1];
                contagemPorGenero.put(genero, count);
            }
            return contagemPorGenero;
        }
    }
