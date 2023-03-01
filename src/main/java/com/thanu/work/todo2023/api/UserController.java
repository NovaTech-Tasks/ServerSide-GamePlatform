package com.thanu.work.todo2023.api;

import com.thanu.work.todo2023.dto.UserDTO;
import com.thanu.work.todo2023.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/users")

public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    //create user
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json",produces = "application/json")
    public UserDTO registerUser(@RequestBody @Validated UserDTO user, Errors errors){
        if(errors.hasFieldErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.getFieldErrors()
                    .get(0).getDefaultMessage());

        } return userService.registerUser(user);
    }
    //logging process

    @GetMapping(path = "/{email}/{password}",produces = "application/json")
    public UserDTO chkUser (@PathVariable String email, @PathVariable String password){
        return userService.chkUser(email,password);
    }

    //delete user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{userId:[A-Fa-f0-9\\-]{36}}")
    public void deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
    }

    //update user
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(path = "/{userId}",consumes = "application/json")
    public void updateUser(@PathVariable String userId,@RequestBody UserDTO user,Errors errors){
        if(errors.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errors.getFieldErrors()
                    .get(0).getDefaultMessage());
        }
        user.setId(userId);
        userService.updateUser(user);
    }

    //user info
    @GetMapping(path = "/{userId:[A-Fa-f0-9\\-]{36}}",produces = "application/json")
    public UserDTO getUserInfo (@PathVariable String userId){
        return userService.getUserInfo(userId);
    }

}
