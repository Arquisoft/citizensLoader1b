package dbupdate;

import org.springframework.data.jpa.repository.JpaRepository;

import model.CitizenDB;

public interface UserRepository extends JpaRepository<CitizenDB, Long>{
	CitizenDB findByDni (String dni);
}
