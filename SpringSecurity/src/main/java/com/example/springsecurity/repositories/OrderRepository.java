package com.example.springsecurity.repositories;

import com.example.springsecurity.model.Order;
import com.example.springsecurity.model.Person;
import com.example.springsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {

    List<Order> findByPerson(Person person);


    // Находим продукты по части наименования без учета регистра
    List<Order> findOrderByNumber(String number);

//    @Query(value = "select * from orders where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1'))", nativeQuery = true)

    @Query(value = "select * from orders where orders.number LIKE %?1%", nativeQuery =true)
    List<Order> findByNumberOrderA(String number);

//    @Query(value = "select * from orders where orders.number=?1", nativeQuery =true)
//    List<Order> findByNumberOrderA(String number);

}
