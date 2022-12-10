package com.example.springsecurity.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Логин не может быть пустым")
    @Size(min = 5, max =100, message="Minimum number of 5 digits")
    @Column(name = "login")
    private String login;


    @NotEmpty(message = "Логин не может быть пустым")
    @Column(name = "password")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message="Пароль должен содержать Строчные и прописные латинские буквы, цифры, спецсимволы. Минимум 8 символов")
    private String password;

    @Column(name = "role")
    private String role;

    @ManyToMany()
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> product;

    @OneToMany(mappedBy = "person")
    private List<Order> orderList;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person() {
    }

    public Person(int id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", product=" + product +
                ", orderList=" + orderList +
                '}';
    }
}
