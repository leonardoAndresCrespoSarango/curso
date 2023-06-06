package com.incripcionUsuarios.InscripcionPagos.controller;

import com.incripcionUsuarios.InscripcionPagos.entity.registro;
import com.incripcionUsuarios.InscripcionPagos.services.registroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registro")
public class registroController {

    private registroService regService;

    @Autowired
    public registroController(registroService regService) {
        this.regService = regService;
    }
    @GetMapping("/todos")
    public List<registro> obtenerTodosLosItems() {
        return regService.getListaTodo();
    }

    @PostMapping("/crear")
    public void crearRegistro(@RequestBody registro registro) {
        regService.insertarRegistro(registro);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCatalogo(@PathVariable("id") int id, @RequestBody registro registroActualizado) {
        registro registroActualizadoResultado = regService.actualizarRegistro(id, registroActualizado);
        if (registroActualizadoResultado != null) {
            return ResponseEntity.ok("Catálogo actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRegistro(@PathVariable("id") int id) {
        regService.eliminarRegistro(id);
        return ResponseEntity.ok("Registro eliminado exitosamente");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<registro> buscarPorId(@PathVariable("id") int id) {
        registro registroEncontrado = regService.buscarPorId(id);
        if (registroEncontrado != null) {
            return ResponseEntity.ok(registroEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
