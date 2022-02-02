package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.model.entity.Forum;

@Repository
public interface ForumRepository extends JpaRepository<Forum,Long> {
}
