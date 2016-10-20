package io.spring.cloud.samples.commerce.ui.services.items;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ItemService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Map<Item,String> items() {
        Items items = restTemplate.getForObject("http://items", Items.class);
        Prices prices = restTemplate.getForObject("http://prices", Prices.class);
        Iterable<Item> itemsTemp = items.getItems();
        Map<String,String> pricesTemp = prices.getPrices();
        Map<Item,String> output = new HashMap<Item,String>();
        while(itemsTemp.iterator().hasNext()) {
        	Item item = itemsTemp.iterator().next();
        	String price = pricesTemp.get(Long.toString(item.getId()));
        	output.put(item,price);
        }
        return output;
    }
    
    @HystrixCommand(fallbackMethod = "fallback")
    public Map<Item,String> categoryItems(String category) {
        Items items = restTemplate.getForObject("http://category/" + category, Items.class);
        Prices prices = restTemplate.getForObject("http://prices", Prices.class);
        Iterable<Item> itemsTemp = items.getItems();
        Map<String,String> pricesTemp = prices.getPrices();
        Map<Item,String> output = new HashMap<Item,String>();
        while(itemsTemp.iterator().hasNext()) {
        	Item item = itemsTemp.iterator().next();
        	String price = pricesTemp.get(Long.toString(item.getId()));
        	output.put(item,price);
        }
        return output;
    }    
    
    @HystrixCommand(fallbackMethod = "fallback")
    public Map<Item,String> item(String itemId) {
        Items items = restTemplate.getForObject("http://items", Items.class);
        Prices prices = restTemplate.getForObject("http://prices/" + itemId, Prices.class);
        long id = Long.parseLong(itemId);     
        Iterable<Item> itemsTemp = items.getItems();
        Map<String,String> pricesTemp = prices.getPrices();
        Map<Item,String> output = new HashMap<Item,String>();
        Item item = new Item();
        String price = "";
        while(itemsTemp.iterator().hasNext()) {
        	Item i = itemsTemp.iterator().next();
        	if(i.getId() == id) {
        		item = i;
        		price = pricesTemp.get(Long.toString(id));
        	}        	
        }
    	output.put(item,price);
        return output;
    }        

   private Map<Item,String> fallback() {
        Item item = new Item();
    	item.setId((long) 0);
    	item.setName("fallback");
    	item.setDescription("fallback");
    	item.setCategory("fallback");
        String price = "fallback";
        Map<Item,String> output = new HashMap<Item,String>();
        output.put(item, price);
        return output;
   }
}