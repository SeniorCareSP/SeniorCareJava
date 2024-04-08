package seniorcare.crudseniorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.model.Newsletter;

import java.util.UUID;

public interface NewsletterRepository extends JpaRepository<Newsletter, UUID> {
}
