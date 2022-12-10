package com.example.springsecurity.controller;


import com.example.springsecurity.model.*;
import com.example.springsecurity.repositories.CategoryRepository;
import com.example.springsecurity.repositories.OrderRepository;
import com.example.springsecurity.service.OrderService;
import com.example.springsecurity.service.PersonService;
import com.example.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private Order order;

    private final OrderService orderService;

    private final ProductService productService;

    private final OrderRepository orderRepository;
    private final PersonService personService;

    private final CategoryRepository categoryRepository;

    @Autowired
    public AdminController(OrderService orderService, ProductService productService, OrderRepository orderRepository, PersonService personService, CategoryRepository categoryRepository) {
        this.orderService = orderService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.personService = personService;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("")
        public String admin(Model model){
        model.addAttribute("product", productService.getAllProducts());
        return "admin/admin";
        }


        @GetMapping("/product/add")
        public String addProduct(Model model){
        model.addAttribute("product", new Product());
            model.addAttribute("category", categoryRepository.findAll());
            return "product/AddProduct";
        }

    // Метод по добавлению продукта в БД через сервис->репозиторий
    @PostMapping("/product/add")
//    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one, @RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three, @RequestParam("file_four")MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException

    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one")MultipartFile file_one,@RequestParam("file_two")MultipartFile file_two, @RequestParam("file_three")MultipartFile file_three) throws IOException

    {
        if(bindingResult.hasErrors())
        {
            return "product/AddProduct";
        }

        if(file_one != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image=new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_two != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

        if(file_three != null)
        {
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageToProduct(image);
        }

//        if(file_four != null)
//        {
//            File uploadDir = new File(uploadPath);
//            if(!uploadDir.exists()){
//                uploadDir.mkdir();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
//            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
//            Image image = new Image();
//            image.setProduct(product);
//            image.setFileName(resultFileName);
//            product.addImageToProduct(image);
//        }
//
//        if(file_five != null)
//        {
//            File uploadDir = new File(uploadPath);
//            if(!uploadDir.exists()){
//                uploadDir.mkdir();
//            }
//            String uuidFile = UUID.randomUUID().toString();
//            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
//            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
//            Image image = new Image();
//            image.setProduct(product);
//            image.setFileName(resultFileName);
//            product.addImageToProduct(image);
//        }
        productService.saveProduct(product);
        return "redirect:/admin";
    }


        @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
        }

    // Метод по отображению страницы с возможностью редактирования товаров
    @GetMapping("/product/edit/{id}")
    public String editProduct(Model model, @PathVariable("id") int id){
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    // Метод по редактированию товара
    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") int id){
        if(bindingResult.hasErrors())
        {
            return "product/editProduct";
        }
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }
    // Метод по открытию страницы с пользователями
    @GetMapping("/user")
    public String index(Model model){
        model.addAttribute("getUser", personService.getAllUser());
        return "admin/user";
    }

    // Метод по отправке информации о конкретном пользователи на шаблон
    @GetMapping("/user/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
        model.addAttribute("infoUser", personService.getUserId(id));
        model.addAttribute("product", productService.getProductId(id));
        model.addAttribute("order", orderRepository.findByPerson(personService.getUserId(id)));
        return "admin/infoUser";
    }


//    @GetMapping("/edit/{id}")
//    public String editUser(Model model, @PathVariable("id") int id){
//        model.addAttribute("editUser", orderRepository.findByPerson(personService.getUserId(id)));
//        return "admin/editUser";
//    }

//    @PostMapping("/edit/{id}")
//    public String editUser(@ModelAttribute("order") Order order, BindingResult bindingResult, @PathVariable("id") int id) {
//        if (bindingResult.hasErrors()) {
//            return "admin/editUser";
//        }
//        orderService.updateOrder(id, order);
//        return "redirect:/admin";
//    }


    @GetMapping("/user/new")
    public String newUser(Model model){
        Person user=new Person();
        model.addAttribute("user", user);
        return "admin/newUser";
    }

    @PostMapping("/user/save")
    public String saveUser(Person user, RedirectAttributes redirectAttributes){
    personService.save(user);
    redirectAttributes.addFlashAttribute("message", "Пользователь успешно добавлен");
    return "redirect:/admin/user";

    }

    @GetMapping("/user/edit/{id}")

    public String editUser(@PathVariable(name="id") int id, Model model ){
           Person user= personService.getUserId(id);
           model.addAttribute("user", user);
         return "admin/editUser";
    }

    @PostMapping("/user/edit/{id}")

    public String editUser(@ModelAttribute("user")  Person user, BindingResult bindingResult, @PathVariable("id") int id) {

        if(bindingResult.hasErrors())
        {
            return "admin/editUser";
        }
        personService.updateUser(id, user);

        return "redirect:/admin/user";
    }

    @GetMapping("/user/delete/{id}")

    public String deleteUser(@PathVariable("id") int id){
         personService.deleteUser(id);
        return "redirect:/admin/user";
    }


    @GetMapping("/order")
    public String orderindex (Model model){
        model.addAttribute("order", orderRepository.findAll());
        model.addAttribute("user", personService.getAllUser());

//        model.addAttribute("getUser", personService.getUserId());


        return "admin/order";
    }

    // Метод по отправке информации о конкретном пользователи на шаблон
    @GetMapping("/order/{id}")
    public String infoOrder(@PathVariable("id") int id, Model model){
        model.addAttribute("order", orderService.getOrderId(id));
        return "admin/infoOrder";
    }

    @GetMapping("/order/delete/{id}")

    public String deleteOrder(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return "redirect:/admin/order";
    }


    @GetMapping("/order/edit/{id}")

    public String editOrder(@PathVariable(name="id") int id, Model model ){
       Order order= orderService.getOrderId(id);
        model.addAttribute("order", order);
        return "admin/editOrder";
    }

    @PostMapping("/order/edit/{id}")

    public String editOrder(@ModelAttribute("order")  Order order, BindingResult bindingResult,  @PathVariable("id") int id) {

        if(bindingResult.hasErrors())
        {
            return "admin/editOrder";
        }
//        orderService.updateOrder(statusInfo,order);

        orderService.updateOrder(id, order);

        return "redirect:/admin/order";
    }

    @PostMapping("/order/sorting_and_searching")
    public String sorting_and_searching(@RequestParam("SortingOrFilteringOptions")String SortingOrFilteringOptions, @RequestParam("option_sort_or_search") String option_sort_or_search, Model model){

        if(SortingOrFilteringOptions.equals("number"))
        {
                model.addAttribute("search_order", orderService.getNumber(option_sort_or_search.toString()));
        }

        model.addAttribute("order", orderRepository.findAll());

        return "admin/order";
    }



}
