package com.example.springsecurity.util;

import com.example.springsecurity.model.Person;
import com.example.springsecurity.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    //Указываем для какой модели предназначен валидатор
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.findByLogin(person) !=null)
        {
        errors.rejectValue("login", "", "Данный логин уже занят");
        }
    }
}
