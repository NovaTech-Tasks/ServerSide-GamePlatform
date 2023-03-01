package com.thanu.work.todo2023.repository;

import com.thanu.work.todo2023.entity.Games;
import com.thanu.work.todo2023.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Games,Integer> {
    List<Games> findAllGamesByUser(User user);



}
