package com.catalogo.catalogo.services;

import com.catalogo.catalogo.entity.catalogo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class catalogoService {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(catalogo catalogo) {
        entityManager.persist(catalogo);
    }

    public List<catalogo> getListaTodo() {
        String jpql = "SELECT c FROM catalogo c";
        Query query = entityManager.createQuery(jpql, catalogo.class);
        List<catalogo> lista = query.getResultList();
        return lista;
    }

    public void insertarCatalogo(catalogo catalogo) {
        entityManager.persist(catalogo);
    }

    public catalogo actualizarCatalogo(int id, catalogo catalogoActualizado) {
        catalogo catalogoExistente = entityManager.find(catalogo.class, id);
        if (catalogoExistente != null) {
            catalogoExistente.setNombre(catalogoActualizado.getNombre());
            catalogoExistente.setDescripccion(catalogoActualizado.getDescripccion());

            entityManager.merge(catalogoExistente);
        }
        return catalogoExistente;
    }

    public void eliminarCatalogo(int id) {
        catalogo catalogoExistente = entityManager.find(catalogo.class, id);
        if (catalogoExistente != null) {
            entityManager.remove(catalogoExistente);
        }
    }

    public catalogo buscarPorId(int id) {
        return entityManager.find(catalogo.class, id);
    }
}
