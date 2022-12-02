package com.example.springsecurity.repositories;

import com.example.springsecurity.model.Order;
import com.example.springsecurity.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {

    List<Order> findByPerson(Person person);
}
