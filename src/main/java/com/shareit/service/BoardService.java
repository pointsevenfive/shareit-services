package com.shareit.service;

import com.shareit.exception.NotFoundException;
import com.shareit.model.entities.Post;
import com.shareit.model.entities.User;
import com.shareit.model.repository.PostRepo;
import com.shareit.model.repository.UserRepo;
import com.shareit.security.SecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BoardService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private SecUtils secUtils;

    public ResponseEntity<String> addPostToBoard(String postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format("post id: %s", postId)));
        String contextEmail = secUtils.getUserFromContext();
        User user = userRepo.findByEmail(contextEmail)
                .orElseThrow(() -> new NotFoundException(String.format("user id: %s", contextEmail)));
        user.getBoard().addPostToBoard(post);
        userRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
