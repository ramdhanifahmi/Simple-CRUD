package com.fahmi.Simple.CRUD.service.impl;

import com.fahmi.Simple.CRUD.common.GenericResponseDTO;
import com.fahmi.Simple.CRUD.domain.User;
import com.fahmi.Simple.CRUD.dto.UserDTO;
import com.fahmi.Simple.CRUD.repository.UserRepository;
import com.fahmi.Simple.CRUD.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    GenericResponseDTO data = new GenericResponseDTO();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GenericResponseDTO getUserById(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            logger.warn("Get user with id {} failed, user not found", userId);

            return data.noDataFoundResponse("User not found with id " + userId);
        }
        logger.info("Get user with id {} success", userId);
        return data.successResponse(optionalUser.get());
    }

    @Override
    public GenericResponseDTO getAllUsers() {
        List<User> userList = userRepository.findAll();
        logger.info("Get all users success");
        return data.successResponse(userList);
    }

    @Transactional
    @Override
    public GenericResponseDTO createOrUpdateUser(UserDTO userDTO) throws Exception {
        User user;
        if(userDTO.getUserId() == null) {
            user = new User();
            logger.info("Create new user with data : {}", objectMapper.writeValueAsString(userDTO));

        } else {
            Optional<User> optionalUser = userRepository.findById(userDTO.getUserId());
            if(optionalUser.isEmpty()) {
                logger.warn("Get user with id {} failed, user not found", userDTO.getUserId());
                return data.noDataFoundResponse("User not found with id " + userDTO.getUserId());
            }
            user = optionalUser.get();
            logger.info("Update user with id {}", user.getUserid());
        }

        user.setNamalengkap(userDTO.getNamaLengkap());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setStatus(userDTO.getStatus());

        userRepository.save(user);

        return data.successResponse(user);
    }

    @Transactional
    @Override
    public GenericResponseDTO deleteUser(int userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            logger.warn("Delete user with id {} failed, user not found", userId);
            return data.noDataFoundResponse("User not found with id " + userId);
        }

        userRepository.deleteById(userId);
        logger.info("Delete user with id {} success", userId);
        return data.successResponse("Success delete data with userId : " + userId);
    }
}
