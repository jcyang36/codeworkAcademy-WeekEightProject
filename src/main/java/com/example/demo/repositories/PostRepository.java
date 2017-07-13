package com.example.demo.repositories;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by student on 7/13/17.
 */
public interface PostRepository extends CrudRepository<Post, Integer> {
    public Iterable<Post> findAllByUserId(int userId);
   public List<Post> findTop1ByUserIdOrderByIdDesc(int userId);
}
