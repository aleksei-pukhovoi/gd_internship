package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
