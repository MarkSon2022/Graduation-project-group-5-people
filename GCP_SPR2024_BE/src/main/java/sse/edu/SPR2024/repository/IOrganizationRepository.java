package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Organization;

public interface IOrganizationRepository extends JpaRepository<Organization, String> {
    Organization findFirstByUserEmail(String email);
}
