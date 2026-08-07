package Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cart {
	List<CartItem> items = new ArrayList<CartItem>();
	
	public List<CartItem> getItem(){
			
		return items;
	}
	
	public void addItem(Product drink) {
		for(CartItem item: items) {
			if(item.getDrink().getProductId().equals(drink)) {
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
			if(item.getDrink().getProductId().equals(id)) {
				item.setQuantity(qty);
			}
		}
	}
	
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(CartItem item: items) {
			total = total.add(item.getDrink().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
		}
		return  total;
	}
}
