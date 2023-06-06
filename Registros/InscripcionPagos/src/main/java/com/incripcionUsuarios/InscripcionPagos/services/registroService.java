package com.incripcionUsuarios.InscripcionPagos.services;

import com.incripcionUsuarios.InscripcionPagos.entity.registro;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class registroService {
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(registro registro) {
        entityManager.persist(registro);
    }

    public List<registro> getListaTodo() {
        String jpql = "SELECT c FROM registro c";
        Query query = entityManager.createQuery(jpql, registro.class);
        List<registro> lista = query.getResultList();
        return lista;
    }

    public void insertarRegistro(registro registro) {
        entityManager.persist(registro);
    }

    public registro actualizarRegistro(int id, registro rgistroActualizado) {
        registro registroExistente = entityManager.find(registro.class, id);
        if (registroExistente != null) {
            registroExistente.setId(registroExistente.getId());
            registroExistente.setDescripcion(registroExistente.getDescripcion());

            entityManager.merge(registroExistente);
        }
        return registroExistente;
    }

    public void eliminarRegistro(int id) {
        registro registroExistente = entityManager.find(registro.class, id);
        if (registroExistente != null) {
            entityManager.remove(registroExistente);
        }
    }

    public registro buscarPorId(int id) {
        return entityManager.find(registro.class, id);
    }
}
