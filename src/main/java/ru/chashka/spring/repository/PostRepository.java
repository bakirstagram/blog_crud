package ru.chashka.spring.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chashka.spring.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}
