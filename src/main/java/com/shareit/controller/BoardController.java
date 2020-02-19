package com.shareit.controller;

import com.shareit.aspect.RestLogger;
import com.shareit.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestLogger
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/pin/{id}")
    public ResponseEntity<String> pinToBoard(@PathVariable(name = "id") String postId) {
        return boardService.addPostToBoard(postId);
    }
}
