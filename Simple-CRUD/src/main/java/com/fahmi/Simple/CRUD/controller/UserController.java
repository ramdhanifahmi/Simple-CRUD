package com.fahmi.Simple.CRUD.controller;

import com.fahmi.Simple.CRUD.common.GenericResponseDTO;
import com.fahmi.Simple.CRUD.dto.UserDTO;
import com.fahmi.Simple.CRUD.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/get/{userId}")
    public ResponseEntity<GenericResponseDTO> getUserById(@PathVariable(required = false) Integer userId) {
        GenericResponseDTO response;
        if (userId != null && !userId.equals("")) {
            response = userService.getUserById(userId);
        } else {
            response = userService.getAllUsers();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<GenericResponseDTO> getAllUser() {
         var   response = userService.getAllUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponseDTO> createOrUpdateUser(@RequestBody UserDTO userDTO){
        GenericResponseDTO response;
        try {
            response = userService.createOrUpdateUser(userDTO);
        } catch (Exception e) {
            response = new GenericResponseDTO().errorResponse(400, e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<GenericResponseDTO> deleteUser(@PathVariable int userId) {
        GenericResponseDTO response = userService.deleteUser(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<GenericResponseDTO> handleException(Exception e) {
        GenericResponseDTO response = new GenericResponseDTO().errorResponse(500, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
