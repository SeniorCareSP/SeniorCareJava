package seniorcare.crudseniorcare.domain.newsletter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import seniorcare.crudseniorcare.domain.newsletter.Newsletter;

import java.util.UUID;

public interface NewsletterRepository extends JpaRepository<Newsletter, Integer> {
}
