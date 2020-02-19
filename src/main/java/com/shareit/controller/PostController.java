package com.shareit.controller;

import com.shareit.aspect.RestLogger;
import com.shareit.model.entities.Post;
import com.shareit.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestLogger
@RestController
@RequestMapping("/post")
@Api(value = "posts")
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "${PostController.find}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "User not found")})
    @GetMapping("/title")
    public List<Post> find(@RequestParam(name = "q") String searchString) {
        return postService.findByTitleSearch(searchString);
    }

    @ApiOperation(value = "${PostController.all}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "User not found")})
    @GetMapping("/all")
    public List<Post> findAll() {
        return postService.all();
    }


    @ApiOperation(value = "${PostController.get}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "User not found")})
    @GetMapping("/title/{id}")
    public Post get(@PathVariable(name = "id") String id) {
        return postService.findById(id);
    }


    @ApiOperation(value = "${PostController.create}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 404, message = "User not found")})
    @PostMapping("/new")
    public Post create(@Valid @RequestBody Post post) {
        return postService.create(post);
    }
}
