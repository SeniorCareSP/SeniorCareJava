    package seniorcare.crudseniorcare.controller.dashboard;
    
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import seniorcare.crudseniorcare.service.caracteristica.CaracteristicaService;
    import seniorcare.crudseniorcare.service.denuncia.DenunciaService;
    import seniorcare.crudseniorcare.service.idoso.IdosoService;
    import seniorcare.crudseniorcare.service.usuario.CuidadorService;
    import seniorcare.crudseniorcare.service.visita.VisitaService;
    
    import java.time.LocalDate;
    import java.util.Map;
    
    
    @RestController
    @RequestMapping("/dasboard")
    @RequiredArgsConstructor
    public class DashboadController {
        private final VisitaService visitaService;
        private final DenunciaService denunciaService;
        private final IdosoService idosoService;
        private final CaracteristicaService caracteristicaService;
        private final CuidadorService cuidadorService;
    
        @GetMapping("/quantidade-denuncia-aberta")
        public ResponseEntity<Integer> getCountByStatusFalse() {
            Integer count = denunciaService.getCountByStatusFalse();
            return ResponseEntity.ok(count);
        }
    
        @GetMapping("/cuidador/count-by-genero")
        public ResponseEntity<Map<String, Long>> contarCuidadoresPorGenero() {
            Map<String, Long> contagemPorGenero = cuidadorService.contarCuidadoresPorGenero();
            return ResponseEntity.ok(contagemPorGenero);
        }
            @GetMapping("/caracteristica/count-by-nome")
        public ResponseEntity<Map<String, Long>> contarCaracteristicasPorNome() {
            Map<String, Long> contagemPorNome = caracteristicaService.contarCaracteristicasPorNome();
            return ResponseEntity.ok(contagemPorNome);
        }
    
        @GetMapping("/idosos/count-by-faixa-etaria-e-genero")
        public ResponseEntity<Map<String, Map<String, Long>>> contarIdososPorFaixaEtariaEGenero() {
            Map<String, Map<String, Long>> contagem = idosoService.contarIdososPorFaixaEtariaEGenero();
            return ResponseEntity.ok(contagem);
        }
        @GetMapping("/count")
        public ResponseEntity<Long> contarVisitasHoje() {
            long totalVisitasHoje = visitaService.contarVisitasHoje();
            return ResponseEntity.ok(totalVisitasHoje);
        }
    
        @GetMapping("/denuncias/count")
        public ResponseEntity<Long> contarDenunciasPorDia() {
            long count = denunciaService.contarDenunciasPorDia(LocalDate.now());
            return ResponseEntity.ok(count);
        }
    }
