package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.model.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
