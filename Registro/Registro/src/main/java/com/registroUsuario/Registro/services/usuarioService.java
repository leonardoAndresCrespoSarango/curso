package com.registroUsuario.Registro.services;

import javax.persistence.TypedQuery;
import com.registroUsuario.Registro.entity.Usuario;
import com.registroUsuario.Registro.modelo.Recursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.registroUsuario.Registro.feignclients.RecursosFeignClient;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.web.client.RestTemplate;




@Service
@Transactional
public class usuarioService {
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private RecursosFeignClient recursosFeignClient;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> getListaTodo() {
        String jpql = "SELECT c FROM Usuario c";
        Query query = entityManager.createQuery(jpql, Usuario.class);
        List<Usuario> lista = query.getResultList();
        return lista;
    }

    public void insertarCatalogo(Usuario usuario) {
        entityManager.persist(usuario);
    }

    public Usuario actualizarUsuario(int id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = entityManager.find(Usuario.class, id);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
            usuarioExistente.setUsuario(usuarioActualizado.getUsuario());
            usuarioExistente.setContrasenia(usuarioActualizado.getContrasenia());
            entityManager.merge(usuarioExistente);
        }
        return usuarioExistente;
    }

    public void eliminarUsuario(int id) {
        Usuario usuarioExistente = entityManager.find(Usuario.class, id);
        if (usuarioExistente != null) {
            entityManager.remove(usuarioExistente);
        }
    }

    public Usuario buscarPorId(int id) {
        return entityManager.find(Usuario.class, id);
    }
    //////////
    public List<Recursos> getRecursos(int usuarioId) {
        String url = "http://localhost:8008/recursos/usuario/" + usuarioId;
        ResponseEntity<List<Recursos>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Recursos>>() {}
        );
        List<Recursos> recursos = response.getBody();
        return recursos;
    }

    public Recursos saveRecursos(int usuarioId, Recursos recursos) {
        recursos.setUsuarioId(usuarioId);
        Recursos nuevoRecursos = recursosFeignClient.save(recursos);
        return nuevoRecursos;
    }






}
