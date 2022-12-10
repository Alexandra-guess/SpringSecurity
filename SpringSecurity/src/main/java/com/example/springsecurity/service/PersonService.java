package com.example.springsecurity.service;

import com.example.springsecurity.model.Person;
import com.example.springsecurity.model.Product;
import com.example.springsecurity.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Person findByLogin(Person person) {
        Optional<Person> person_db = personRepository.findByLogin(person.getLogin());
        return person_db.orElse(null);
    }

    @Transactional
    public void register(Person person) {

        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        personRepository.save(person);
    }

    public List<Person> getAllUser(){
        return personRepository.findAll();
    }

    // Данный метод позволяет вернуть пользователя по id
    public Person getUserId(int id){
        Optional<Person> optionalUser = personRepository.findById(id);
        return optionalUser.orElse(null); // select * from user_site where id= переменная id
    }


    private void encodePassword(Person user){
        String encodePassword= passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

    }


    public void save(Person user)
    {;
        encodePassword(user);
          personRepository.save(user);

    }

    @Transactional
    public void deleteUser(int id){
        personRepository.deleteById(id);

    }


    @Transactional
    public void updateUser(int id, Person user){
        user.setId(id);
        encodePassword(user);
        personRepository.save(user);
    }


//    // Данный метод позволяет удалить пользовател по id
//    @Transactional
//    public void delete(int id)
//    {
//        personRepository.deleteById(id); // delete from user_site where id = параметру id
//    }





}
