package com.example.springsecurity.controller;


import com.example.springsecurity.repositories.ProductRepository;
import com.example.springsecurity.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public String getAllProduct(Model model) {
        model.addAttribute("product", productService.getAllProducts());
        return "product/product";
    }

    @GetMapping("/info/{id}")
    public String ProductInfo(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProductId(id));
        return "product/infoProduct";
    }

    @PostMapping("/search")
    public String productSearch(
            @RequestParam("search") String search, @RequestParam("before") String before, @RequestParam("after") String after, @RequestParam( value = "price", required = false, defaultValue = "") String price, @RequestParam(value = "contact", required = false, defaultValue = "")String contact, Model model){

        if (!before.isEmpty() & !after.isEmpty()) {

            if (!price.isEmpty()) {
                if (price.equals("sorted_by_ascending_price")){
                        if (!contact.isEmpty()) {

                            if (contact.equals("white")){
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 1 ));
                            }
                            else if (contact.equals("red")){
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 2 ));
                            }
                            else if (contact.equals("sparkling")){
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 3 ));
                            }
                            else if (contact.equals("basket")){
                                model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 4 ));
                            }
                        }

                }else if(price.equals("sorted_by_descending_price")) {

                    if (!contact.isEmpty()) {

                        if (contact.equals("white")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 1 ));
                        }
                        else if (contact.equals("red")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 2 ));
                        }
                        else if (contact.equals("sparkling")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 3 ));
                        }
                        else if (contact.equals("basket")){
                            model.addAttribute("search_product", productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),Float.parseFloat(after), Float.parseFloat(before), 4 ));
                        }
                    }
                }

            }

            else {
                    model.addAttribute("search_product", productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search, Float.parseFloat(after), Float.parseFloat(before)));}
        }
        else if (!contact.isEmpty()) {

            if (contact.equals("white")){
                model.addAttribute("search_product", productRepository.findByCategory( 1 ));
            }
            else if (contact.equals("red")){
                model.addAttribute("search_product", productRepository.findByCategory( 2 ));
            }
            else if (contact.equals("sparkling")){
                model.addAttribute("search_product", productRepository.findByCategory( 3 ));
            }
            else if (contact.equals("basket")){
                model.addAttribute("search_product", productRepository.findByCategory( 4 ));
            }
        }



        else {
            model.addAttribute("search_product", productRepository.findByTitleContainingIgnoreCase(search));
        }

        model.addAttribute("value_search", search);
        model.addAttribute("value_price_after", after);
        model.addAttribute("value_price_before", before);
        model.addAttribute("product", productService.getAllProducts());


        return "product/product";
    }









}
