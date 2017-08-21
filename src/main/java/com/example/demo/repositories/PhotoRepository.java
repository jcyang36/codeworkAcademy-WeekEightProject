package com.example.demo.repositories;

import com.example.demo.models.Photo;
import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by student on 7/10/17.
 */
public interface PhotoRepository extends CrudRepository<Photo,Integer> {
    public Photo findTop1ByPhotoname(String photoname);
    public Photo findById(int id);
}
