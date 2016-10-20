package io.spring.cloud.samples.commerce.ui.services.items;

import java.util.HashMap;
import java.util.Map;

public class Prices {

  Map<String,String> prices = new HashMap<>();

  public Map<String, String> getPrices() {
    return prices;
  }

  public void setPrices(Map<String, String> prices) {
    this.prices = prices;
  }

  public String getPriceForItem(String itemId) {
    return prices.get(itemId);
  }

  public void putPrice(String itemId, String price) {
    prices.put(itemId, price);
  }
}