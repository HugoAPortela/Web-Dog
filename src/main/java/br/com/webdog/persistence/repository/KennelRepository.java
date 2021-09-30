package br.com.webdog.persistence.repository;

import br.com.webdog.persistence.model.Kennel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KennelRepository extends JpaRepository<Kennel, Long> {
}
