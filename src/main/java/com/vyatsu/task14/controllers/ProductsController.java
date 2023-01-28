package com.vyatsu.task14.controllers;

import com.vyatsu.task14.entities.Product;
import com.vyatsu.task14.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
//@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    @RequestMapping(method = RequestMethod.GET)
    public String showProductsList(Model model, @RequestParam(value = "titleSearch", required = false) String title, @RequestParam(value = "priceFrom", required = false) String priceFrom, @RequestParam(value = "priceTo", required = false) String priceTo) {
        if (
                (title != null && !title.isEmpty())
                        || (priceFrom != null && !priceFrom.isEmpty())
                            || (priceTo != null && !priceTo.isEmpty())
        )
            model.addAttribute("products", productsService.getFilteredProducts(title, priceFrom, priceTo));
        else
            model.addAttribute("products", productsService.getAll());
        Product product = new Product();

        model.addAttribute("stitle", title);

        model.addAttribute("product", product);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.add(product);
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        productsService.remove(id);
        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Optional<Product> product = productsService.getById(id);
        if (product.isPresent())
            model.addAttribute("product", product.get());
        else
            model.addAttribute("product", null);
        return "product-page";
    }

    @PostMapping("/edit/")
    public String changeProduct(@ModelAttribute(value = "product") Product product) {
        if (product != null && product.getId() != null) {
            productsService.change(product);
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") Long id) {
        Optional<Product> product = productsService.getById(id);
        if (product.isPresent())
            model.addAttribute("product", product.get());
        else
            model.addAttribute("product", null);
        return "product-edit-page";
    }

}
