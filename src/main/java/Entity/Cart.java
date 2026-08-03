package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
	List<CartItem> items = new ArrayList<CartItem>();
	
	public List<CartItem> getItem(){
			
		return items;
	}
	
	public void addItem(Drink drink) {
		for(CartItem item: items) {
			if(item.getDrink().getIDDrink().equals(drink)) {
				item.setQuantity(item.getQuantity()+1);
				return;
			}}
			items.add(new CartItem(drink,1));
		
	}
	
	public void deleteItem(String id) {
		items.removeIf(i -> i.getDrink().equals(id));
	}
	
	public void updateItem(String id,int qty) {
		for (CartItem item : items) {
			if(item.getDrink().getIDDrink().equals(id)) {
				item.setQuantity(qty);
			}
		}
	}
	
	
	public double getTotal() {
		double total = 0;
		for(CartItem item: items) {
			total += (item.getDrink().getDrinkPrice())*(item.getQuantity());
		}
		return total;
	}
}
