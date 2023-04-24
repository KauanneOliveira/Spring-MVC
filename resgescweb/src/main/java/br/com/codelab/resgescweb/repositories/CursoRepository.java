package br.com.codelab.resgescweb.repositories;

import br.com.codelab.resgescweb.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}

