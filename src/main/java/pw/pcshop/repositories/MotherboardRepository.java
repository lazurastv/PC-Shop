package pw.pcshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pw.pcshop.dataModels.Motherboard;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {
}