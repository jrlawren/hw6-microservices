package io.spring.cloud.samples.commerce.ui.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.cloud.samples.commerce.ui.services.items.Item;
import io.spring.cloud.samples.commerce.ui.services.items.ItemService;

@RestController
public class UiController {

    @Autowired
    ItemService service;

    @RequestMapping("/items")
    public Map<Item,String> items() {
        return service.items();
    }
    
    @RequestMapping("/category/{cat}")
    public Map<Item,String> categoryItems(@PathVariable("cat") String category) {
        return service.categoryItems(category);
    }    
    
    @RequestMapping("/item/{itemId}")
    public Map<Item,String> item(@PathVariable("itemId") String itemId) {
        return service.item(itemId);
    }   
}