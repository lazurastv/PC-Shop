package pw.pcshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pw.pcshop.dataModels.Processor;

public interface ProcessorRepository extends JpaRepository<Processor, Long> {
}
