package org.serratec.backend.TrabalhoFinal.repository;

import org.serratec.backend.TrabalhoFinal.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{	
}