package com.shareit.model.repository;

import com.shareit.model.entities.Post;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends PagingAndSortingRepository<Post, String> {

    Optional<List<Post>> findByTitleContaining(String title);
}
