package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Group;

import java.util.List;

public interface IGroupRepository extends JpaRepository<Group, String> {
    List<Group>     findAllBySubscriptionId(String subscriptionId);
}
