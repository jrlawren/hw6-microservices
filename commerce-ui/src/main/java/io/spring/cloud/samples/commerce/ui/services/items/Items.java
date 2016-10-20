package io.spring.cloud.samples.commerce.ui.services.items;

import java.util.ArrayList;

public class Items {

  Iterable<Item> items = new ArrayList<>();

  public Iterable<Item> getItems() {
    return items;
  }

  public void setItems(Iterable<Item> items) {
    this.items = items;
  }

  public Item getItem(Long id) {
	Item item = new Item();
	for(Item i : items) {
		if(i.getId() == id) {
			return item = i;
		}
	}
	return item;
  }

  public void putItem(Long id, String n, String d, String c) {
	ArrayList<Item> itemsTemp = new ArrayList<>();
	while(items.iterator().hasNext()) {
		itemsTemp.add(items.iterator().next());
	}
	Item item = new Item();
	item.setId(id);
	item.setName(n);
	item.setDescription(d);
	item.setCategory(c);
    itemsTemp.add(item);
    items = new ArrayList<>();
    items = itemsTemp;
  }
}
