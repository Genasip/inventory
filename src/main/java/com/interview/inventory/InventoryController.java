package com.interview.inventory;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    final ProductService productService;

    @GetMapping("/getAllProducts")
    public List<Product> getAll(){
        return productService.findAll();
    }

    @GetMapping("/getProduct")
    public List<Product> getProduct(@RequestParam String id){
        return productService.findAllById(id);
    }

    @RequestMapping(value = "saveProduct", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String saveProduct(@RequestBody List<Product> products){
        String response = productService.save(products);
        return response;
    }

    @RequestMapping(value = "takeProduct", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String takeProduct(@RequestBody Product products){
        String response = productService.take(products);
        return response;
    }

    public InventoryController(ProductService productService) {
        this.productService = productService;
    }
}
