package com.shaikhabdulgani.crudrest.controller;

import com.shaikhabdulgani.crudrest.model.ApiError;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping("/error")
    public ApiError handleError(WebRequest request, HttpServletResponse response) {
        ApiError error = new ApiError();
        error.setStatusCode(response.getStatus());
        error.setType(HttpStatus.valueOf(response.getStatus()).getReasonPhrase());
        error.setDate(new Date());
        error.setMessage(errorAttributes.getError(request).getLocalizedMessage());

        return error;
    }

}
