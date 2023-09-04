package med.voll.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{

	Page<Medico> findByActivoTrue(Pageable pageable);
	//el nombre de la función es una nomenclatura reconocible por spring data para realizar la query
	//de acuerdo al parámetro que deseamos.
}
