package pw.pcshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pw.pcshop.dataModels.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
