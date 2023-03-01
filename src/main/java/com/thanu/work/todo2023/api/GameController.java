package com.thanu.work.todo2023.api;

import com.thanu.work.todo2023.dto.GameDTO;
import com.thanu.work.todo2023.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/users/{userId:[A-Fa-f0-9\\-]{36}}/game")
public class GameController {
    private final GameService gameService;
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json",produces = "application/json")
    public GameDTO addGame(@PathVariable String userId, @RequestBody @Valid GameDTO toDoDTO, Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()
                    .get(0).getDefaultMessage());
        }
       if(!userId.equals(toDoDTO.getUserIdInGame()))
           throw new ResponseStatusException(HttpStatus.CONFLICT,"User id is mismatch");
           return gameService.saveGame(toDoDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{gameId}",consumes = "application/json")
    public void updateGame(@PathVariable int gameId, @RequestBody GameDTO toDoDTO, Errors errors){
        if(errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors()
                    .get(0).getDefaultMessage());
        }
        toDoDTO.setGameId(gameId);
        gameService.updateGame(toDoDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{gameId:\\d+}")
    public void deleteGame(@PathVariable String userId, @PathVariable int gameId){
        gameService.deleteGame(userId, gameId);
    }
    @GetMapping(produces = "application/json")
    public List<GameDTO> getAllGames(@PathVariable String userId){
        return gameService.getAllGames(userId);
    }

}
