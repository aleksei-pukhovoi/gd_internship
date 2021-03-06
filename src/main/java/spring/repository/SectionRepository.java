package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.model.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
}
