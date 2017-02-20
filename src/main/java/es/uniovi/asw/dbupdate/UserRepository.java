package es.uniovi.asw.dbupdate;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uniovi.asw.CitizenDB;


public interface UserRepository extends JpaRepository<CitizenDB, Long>{
	CitizenDB findByDni (String dni);
}
