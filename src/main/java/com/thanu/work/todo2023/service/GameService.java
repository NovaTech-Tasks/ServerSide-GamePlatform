package com.thanu.work.todo2023.service;

import com.thanu.work.todo2023.dto.GameDTO;
import com.thanu.work.todo2023.excptions.NotFoundException;

import java.util.List;

public interface GameService {
    GameDTO saveGame(GameDTO gameDTO) throws NotFoundException;
    List<GameDTO> getAllGames(String userId);

    void updateGame(GameDTO gameDTO) throws NotFoundException;
    void deleteGame(String userId, int gameId) throws NotFoundException;
}
