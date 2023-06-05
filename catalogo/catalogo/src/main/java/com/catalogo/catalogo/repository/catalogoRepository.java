package com.catalogo.catalogo.repository;
import java.util.List;

import com.catalogo.catalogo.entity.catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface catalogoRepository {
    List<catalogo> findByUsuarioId(int registroId);
    ////////
    //holaaaaaaaaa

}
