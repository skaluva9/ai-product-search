package com.sunanda.aiproductsearch.controller;

import com.sunanda.aiproductsearch.model.Product;
import com.sunanda.aiproductsearch.service.ProductSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productSearchService.getAllProducts();
    }

    @GetMapping("/search")
    public String search(@RequestParam String q) {
        return productSearchService.semanticSearch(q);
    }
}
