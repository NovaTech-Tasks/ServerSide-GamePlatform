package com.thanu.work.todo2023.service.implService;

import com.thanu.work.todo2023.dto.GameDTO;
import com.thanu.work.todo2023.entity.Games;
import com.thanu.work.todo2023.entity.User;
import com.thanu.work.todo2023.excptions.NotFoundException;
import com.thanu.work.todo2023.excptions.UnauthorizedAccessException;
import com.thanu.work.todo2023.repository.GameRepository;
import com.thanu.work.todo2023.repository.UserRepository;
import com.thanu.work.todo2023.service.GameService;
import com.thanu.work.todo2023.service.utilize.EntityToDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceIMPL implements GameService {

    public final UserRepository userRepository;
    public final GameRepository gameRepository;
    public final EntityToDTO entityToDTO;

    public GameServiceIMPL(UserRepository userRepository, EntityToDTO entityToDTO, GameRepository toDoRepository) {
        this.userRepository = userRepository;
        this.entityToDTO = entityToDTO;
        this.gameRepository = toDoRepository;
    }

    @Override
    public GameDTO saveGame(GameDTO gameDTO) throws NotFoundException {
        Games toDoEntity = entityToDTO.getGameEntity(gameDTO);
        toDoEntity.setUser(getUser(gameDTO.getUserIdInGame()));
        return entityToDTO.getGameDTO(gameRepository.save(entityToDTO.getGameEntity(gameDTO)));
    }


    public void updateGame(GameDTO gameDTO) throws NotFoundException {
        Optional<Games> tmpTodo = gameRepository.findById(gameDTO.getGameId());
        if (!tmpTodo.isPresent()) throw new NotFoundException("Game not found");
        tmpTodo.get().setGameName(gameDTO.getGameName());
        tmpTodo.get().setGameImg(gameDTO.getGameImg());

    }

    @Override
    public List<GameDTO> getAllGames(String userId) {
        return gameRepository.findAllGamesByUser(getUser(userId))
                .stream().map(entityToDTO::getGameDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteGame(String userId, int gameId) throws NotFoundException {
        Games todo = gameRepository
                .findById(gameId)
                .orElseThrow(() -> new NotFoundException("Invalid Game")
                );
        if (getUser(userId) == todo.getUser()) {
            gameRepository.deleteById(gameId);
        }else{
            throw new UnauthorizedAccessException("Not allow to delete");
        }
    }

    private User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
