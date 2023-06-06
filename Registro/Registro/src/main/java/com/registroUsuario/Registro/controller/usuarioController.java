package com.registroUsuario.Registro.controller;

import com.registroUsuario.Registro.entity.Usuario;
import com.registroUsuario.Registro.modelo.Recursos;
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
import com.registroUsuario.Registro.services.usuarioService;
import com.registroUsuario.Registro.feignclients.RecursosFeignClient;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    private usuarioService usuService;

    @Autowired
    public usuarioController(usuarioService usuService) {
        this.usuService = usuService;
    }

    @Autowired
    private RecursosFeignClient recursosFeignClient;
    @GetMapping("/todos")
    public List<Usuario> obtenerTodosLosItems() {

        return usuService.getListaTodo();
    }

    @PostMapping("/crear")
    public ResponseEntity<UUID> crearUsuario(@RequestBody Usuario usuario) {
        usuService.insertarCatalogo(usuario);
        return ResponseEntity.ok(usuario.getUuid());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable("id") int id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuarioActualizadoResultado = usuService.actualizarUsuario(id, usuarioActualizado);
        if (usuarioActualizadoResultado != null) {
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable("id") int id) {
        usuService.eliminarUsuario(id);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") int id) {
        Usuario usuarioEncontrado = usuService.buscarPorId(id);
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    ///////////////////
    @GetMapping("/recursos/{usuarioId}")
    public ResponseEntity<List<Recursos>> obtenerRecursosUsuario(@PathVariable("usuarioId") int usuarioId) {
        List<Recursos> recursos = usuService.getRecursos(usuarioId);
        return ResponseEntity.ok(recursos);
    }

    @PostMapping("/recursos/{usuarioId}")
    public ResponseEntity<Recursos> guardarRecursos(@PathVariable("usuarioId") int usuarioId, @RequestBody Recursos recursos) {
        recursos.setUsuarioId(usuarioId);
        Recursos nuevoRecursos = recursosFeignClient.save(recursos);
        return ResponseEntity.ok(nuevoRecursos);
    }





}
