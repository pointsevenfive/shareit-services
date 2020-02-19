package com.shareit.model.repository;

import com.shareit.model.entities.Board;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepo extends PagingAndSortingRepository<Board, String> {
}
