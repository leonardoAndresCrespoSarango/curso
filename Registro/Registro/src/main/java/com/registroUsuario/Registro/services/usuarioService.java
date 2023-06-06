package com.registroUsuario.Registro.services;

import com.registroUsuario.Registro.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class usuarioService {

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

}
