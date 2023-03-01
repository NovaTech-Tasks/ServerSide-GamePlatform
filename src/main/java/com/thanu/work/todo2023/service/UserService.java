package com.thanu.work.todo2023.service;

import com.thanu.work.todo2023.dto.UserDTO;
import com.thanu.work.todo2023.excptions.DuplicateUserIdException;
import com.thanu.work.todo2023.excptions.NotFoundException;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO) throws DuplicateUserIdException;
    void updateUser(UserDTO userDTO) throws NotFoundException;
    UserDTO getUserInfo(String userId) throws NotFoundException;
    void deleteUser(String userId) throws NotFoundException;
    UserDTO chkUser(String email,String password) throws NotFoundException;
}
