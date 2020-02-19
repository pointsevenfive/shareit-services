package com.shareit.controller;

import javax.validation.Valid;

import com.shareit.aspect.RestLogger;
import com.shareit.model.entities.Post;
import com.shareit.model.entities.User;
import com.shareit.service.PostService;
import com.shareit.service.UserService;
import com.shareit.util.UserSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestLogger
@RestController
@RequestMapping("/user")
@Api(tags = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @ApiOperation(value = "${UserController.createUser}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 409, message = "Unable to persist user"),
            @ApiResponse(code = 404, message = "User not found")})
    @PostMapping("/new")
    public User createUser(@Valid @RequestBody User user) {
        return userService.persistUser(user);
    }

    @ApiOperation(value = "${UserController.update}")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 409, message = "Unable to persist user"),
            @ApiResponse(code = 404, message = "User not found")})
    @PutMapping("/{id}/{sec}")
    public User update(@PathVariable(name = "id") String id,
                       @PathVariable(name = "sec") UserSection section,
                       @Valid @RequestBody User user) {
        return userService.updateUser(id, section, user);
    }
}
