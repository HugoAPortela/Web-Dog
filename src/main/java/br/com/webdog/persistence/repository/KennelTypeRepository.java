package br.com.webdog.persistence.repository;

import br.com.webdog.persistence.model.KennelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KennelTypeRepository extends JpaRepository<KennelType, Long> {
}
