package pw.pcshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pw.pcshop.dataModels.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
