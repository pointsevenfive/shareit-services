package com.shareit.model.repository;

import com.shareit.model.auth.TrustedUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrustedUserRepo extends PagingAndSortingRepository<TrustedUser, String> {

    Optional<TrustedUser> findByUsername(String email);
}
