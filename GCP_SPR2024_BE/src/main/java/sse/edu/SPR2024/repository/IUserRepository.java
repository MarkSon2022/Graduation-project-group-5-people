package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.User;

import java.util.List;

public interface IUserRepository  extends JpaRepository<User, String> {
    User findFirstByEmailOrderByCreatedDateDesc(String email);
    User findFirstByEmail(String email);
    List<User> findAllByEmailOrderByCreatedDateDesc(String email);
    List<User> findAllByRole(String role);
}
