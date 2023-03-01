package com.thanu.work.todo2023.service.utilize;

import com.thanu.work.todo2023.dto.GameDTO;
import com.thanu.work.todo2023.dto.UserDTO;
import com.thanu.work.todo2023.entity.Games;
import com.thanu.work.todo2023.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EntityToDTO {
    private ModelMapper modelMapper;

    public EntityToDTO(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public User getUserEntity (UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO getUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }
    public Games getGameEntity(GameDTO toDoDTO){
        return modelMapper.map(toDoDTO, Games.class);
    }
    public GameDTO getGameDTO(Games todoEntity){
        return modelMapper.typeMap(Games.class, GameDTO.class)
                .addMapping(todo -> todo.getUser().getId(), GameDTO::setUserIdInGame)
                .map(todoEntity);
    }



}
