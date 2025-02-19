package id.ac.ui.cs.advprog.eshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";

    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute("product") Product product) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productList(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable String id) {
        try{
            Product product = service.findById(id);
            if (product == null) { // Pastikan produk ditemukan
                System.out.println("Product with ID " + id + " not found.");
                return "redirect:/product/list";
            }

            model.addAttribute("product", product);
            return "editProduct";

        }
        catch (Exception e) {
            System.out.println("Error"+ e.getMessage());
            return  "redirect:/product/list";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable String id, @ModelAttribute Product product) {
        service.update(product);
        return "redirect:/product/list";
    }
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.delete(id);
        return "redirect:/product/list";
    }

}
