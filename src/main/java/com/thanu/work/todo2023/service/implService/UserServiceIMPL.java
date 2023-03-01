package com.thanu.work.todo2023.service.implService;

import com.thanu.work.todo2023.dto.UserDTO;
import com.thanu.work.todo2023.entity.User;
import com.thanu.work.todo2023.excptions.DuplicateUserIdException;
import com.thanu.work.todo2023.excptions.NotFoundException;
import com.thanu.work.todo2023.repository.UserRepository;
import com.thanu.work.todo2023.service.UserService;
import com.thanu.work.todo2023.service.utilize.EntityToDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceIMPL implements UserService {
    private final UserRepository userRepository;
    private final EntityToDTO entityToDTO;

    public UserServiceIMPL(UserRepository userRepository,EntityToDTO entityToDTO){
        this.userRepository = userRepository;
        this.entityToDTO = entityToDTO;
    }
    @Override
    public UserDTO registerUser(UserDTO userDTO) throws DuplicateUserIdException {
        if(userRepository.existsById(userDTO.getEmail())) throw new DuplicateUserIdException("User already exist");
        userDTO.setId(UUID.randomUUID().toString());
        return entityToDTO.getUserDTO(userRepository.save(entityToDTO.getUserEntity(userDTO)));
    }
    //logging process
    @Override
    public UserDTO chkUser(String email,String password) throws NotFoundException {
        if(userRepository.existsUsersByEmailAndPassword(email,password)){
            User user = userRepository.findByEmail(email);
            return userRepository.findById(user.getId()).map(entityToDTO::getUserDTO)
                    .orElseThrow(() -> new NotFoundException("Invalid User"));
        }
        throw new NotFoundException("Invalid User");
    }

    @Override
    public void updateUser(UserDTO userDTO) throws NotFoundException {
        Optional<User> tmpUser = userRepository.findById(userDTO.getId());
        if(!tmpUser.isPresent()) throw new NotFoundException("User not found");
        tmpUser.get().setUserName(userDTO.getUserName());
        tmpUser.get().setPassword(userDTO.getPassword());

    }

    @Override
    public UserDTO getUserInfo(String userId) throws NotFoundException {
        return userRepository.findById(userId).map(entityToDTO::getUserDTO)
                .orElseThrow(() -> new NotFoundException("Invalid User"));
    }

    @Override
    public void deleteUser(String userId) throws NotFoundException {
      if(!userRepository.existsById(userId)) throw new NotFoundException("Invalid userId");
      userRepository.deleteById(userId);
      //getUserInfo(userId);
    }


}
