package com.mastermind.moneytransferapp;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/api/exception")
public class ExceptionController {

    @ExceptionHandler({NoSuchElementException.class, JsonMappingException.class})
    public ModelAndView handleException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("custom-error");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

    public ResponseEntity<String> fetchAll() {
        throw new NoSuchElementException();
    }


}
