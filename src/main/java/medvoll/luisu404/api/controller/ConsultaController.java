package medvoll.luisu404.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.luisu404.api.dto.consulta.DatosCancelamientoConsulta;
import medvoll.luisu404.api.dto.consulta.DatosDetalleConsulta;
import medvoll.luisu404.api.dto.consulta.DatosReservaConsulta;
import medvoll.luisu404.api.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos){

        var detalleConsulta = consultaService.reservar(datos);
        return ResponseEntity.ok(detalleConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        consultaService.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
