package com.fahmi.Simple.CRUD.service;

import com.fahmi.Simple.CRUD.common.GenericResponseDTO;
import com.fahmi.Simple.CRUD.domain.User;
import com.fahmi.Simple.CRUD.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    public GenericResponseDTO getUserById(int userId);
    public GenericResponseDTO getAllUsers();
    public GenericResponseDTO createOrUpdateUser(UserDTO userDTO) throws Exception;
    public GenericResponseDTO deleteUser(int userId);
}
