package com.catalogo.catalogo.controller;

import com.catalogo.catalogo.entity.catalogo;
import com.catalogo.catalogo.services.catalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


import java.util.List;

@RestController
@RequestMapping("/catalogo")
public class catalogoController {

    private catalogoService catService;

    @Autowired
    public catalogoController(catalogoService catService) {
        this.catService = catService;
    }

    @GetMapping("/todos")
    public List<catalogo> obtenerTodosLosItems() {
        return catService.getListaTodo();
    }

    @PostMapping("/crear")
    public void crearCatalogo(@RequestBody catalogo catalogo) {
        catService.insertarCatalogo(catalogo);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCatalogo(@PathVariable("id") int id, @RequestBody catalogo catalogoActualizado) {
        catalogo catalogoActualizadoResultado = catService.actualizarCatalogo(id, catalogoActualizado);
        if (catalogoActualizadoResultado != null) {
            return ResponseEntity.ok("Catálogo actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCatalogo(@PathVariable("id") int id) {
        catService.eliminarCatalogo(id);
        return ResponseEntity.ok("Catálogo eliminado exitosamente");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<catalogo> buscarPorId(@PathVariable("id") int id) {
        catalogo catalogoEncontrado = catService.buscarPorId(id);
        if (catalogoEncontrado != null) {
            return ResponseEntity.ok(catalogoEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
