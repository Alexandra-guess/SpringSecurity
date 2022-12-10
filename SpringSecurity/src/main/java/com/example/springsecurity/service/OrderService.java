package com.example.springsecurity.service;

import com.example.springsecurity.model.Order;
import com.example.springsecurity.model.Person;
import com.example.springsecurity.model.Product;
import com.example.springsecurity.model.Status;
import com.example.springsecurity.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    public final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    //Ищем продукты по id
    public Order getOrderId(int id){
        Optional<Order> optionalOrder=orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    @Transactional
    public void updateOrder(int id, Order order){
        order.setId(id);
        orderRepository.save(order);

    }


    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);

    }


    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }



    public List<Order> getNumber(String number){
        List<Order> orders = orderRepository.findByNumberOrderA(number);
        return orders;
    }



}
