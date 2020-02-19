package com.shareit.model.repository;

import com.shareit.model.entities.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfileRepo extends PagingAndSortingRepository<Profile, String> {
}
