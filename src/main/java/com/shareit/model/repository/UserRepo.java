package com.shareit.model.repository;

import com.shareit.model.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, String> {

    Optional<User> findByEmail(String inEmail);
}
