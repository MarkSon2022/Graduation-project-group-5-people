package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Mentor;
import sse.edu.SPR2024.entity.Organization;

import java.util.List;

public interface IMentorRepository extends JpaRepository<Mentor, String> {
    //List<Mentor> findAllByOrganizationAndIdEquals(String organizationId);
    Mentor findFirstByEmail(String email);
    List<Mentor> findAllByOrganizationOrgId(String id);
}
