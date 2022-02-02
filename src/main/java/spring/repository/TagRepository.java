package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.model.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
