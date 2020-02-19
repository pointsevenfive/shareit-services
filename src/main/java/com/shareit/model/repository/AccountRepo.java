package com.shareit.model.repository;

import com.shareit.model.entities.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepo extends PagingAndSortingRepository<Account, String> {
}
