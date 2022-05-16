package pw.pcshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pw.pcshop.dataModels.GraphicsCard;

public interface GraphicsCardRepository extends JpaRepository<GraphicsCard, Long> {
}
