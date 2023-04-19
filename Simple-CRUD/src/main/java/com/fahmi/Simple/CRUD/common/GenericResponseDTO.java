package com.fahmi.Simple.CRUD.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponseDTO<T> implements Serializable {
    private String status;
    private int code;
    private String message;
    private T data;

    @JsonIgnore
    public GenericResponseDTO<T> successResponse() {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus("Success");
        data.setCode(201);
        data.setMessage("Process Successed");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> successResponse(T t) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus("Success");
        data.setCode(201);
        data.setData(t);
        data.setMessage("Process Successed");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> noDataFoundResponse(T t) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus("Success");
        data.setCode(204);
        data.setData(t);
        data.setMessage("No Data Found");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> noDataFoundResponse() {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus("Success");
        data.setCode(204);
        data.setMessage("No Data Found");
        return data;
    }

    @JsonIgnore
    public GenericResponseDTO<T> errorResponse(int code, String message) {
        GenericResponseDTO<T> data = new GenericResponseDTO();
        data.setStatus("Failed");
        data.setCode(code);
        data.setMessage(message);
        return data;
    }

}
