package com.shareit.service;

import com.shareit.exception.NotFoundException;
import com.shareit.model.entities.Post;
import com.shareit.model.entities.User;
import com.shareit.model.repository.PostRepo;
import com.shareit.model.repository.UserRepo;
import com.shareit.security.SecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecUtils secUtils;

    @Autowired
    private List<Post> wrapperList;

    public List<Post> findByTitleSearch(String title){
        return postRepo.findByTitleContaining(title)
                .orElseThrow(() -> new NotFoundException(String.format("title: %s", title)));
    }

    public Post findById(String id) {
        return postRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("post id: %s", id)));
    }

    public List<Post> all() {
        postRepo.findAll().forEach(wrapperList::add);
        return wrapperList;
    }

    public Post create(Post newPost) {
        String email = secUtils.getUserFromContext();
        User postUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("User id: %s", email)));
        newPost.setUserId(postUser.getId());
        Post savedPost = postRepo.save(newPost);
        postUser.appendPost(savedPost);
        userRepo.save(postUser);
        return savedPost;
    }
}
