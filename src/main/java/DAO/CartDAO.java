package DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Entity.CartItem;
import Entity.Product;

public class CartDAO {
	
	List<CartItem> items = new ArrayList<CartItem>();
	
	public List<CartItem> getItem(){
			
		return items;
	}
	
	
	
	public void addItem(Product drink, String size, int quantity) {

	    for (CartItem item : items) {

	        if (item.getDrink().getProductId().equals(drink.getProductId())
	                && java.util.Objects.equals(item.getSize(), size)) {

	            item.setQuantity(item.getQuantity() + quantity);
	            return;
	        }
	    }

	    items.add(new CartItem(drink, quantity, size));
	}


	public void deleteItem(int id, String size) {

	    items.removeIf(item ->
	        item.getDrink().getProductId().equals(id)
	        && java.util.Objects.equals(item.getSize(), size)
	    );
	}


	public void updateItem(int id, int qty, String size) {

	    for (CartItem item : items) {

	        if (item.getDrink().getProductId().equals(id)
	                && java.util.Objects.equals(item.getSize(), size)) {

	            item.setQuantity(qty);
	            return;
	        }
	    }
	}
	

	public BigDecimal getTotal() {

	    BigDecimal total = BigDecimal.ZERO;

	    for (CartItem item : items) {

	        BigDecimal price;

	        if ("S".equals(item.getSize())) {
	            price = item.getDrink().getSizePrices().getSizeS();

	        } else if ("M".equals(item.getSize())) {
	            price = item.getDrink().getSizePrices().getSizeM();

	        } else if ("L".equals(item.getSize())) {
	            price = item.getDrink().getSizePrices().getSizeL();

	        } else {
	            price = item.getDrink().getPrice();
	        }

	        total = total.add(
	            price.multiply(
	                BigDecimal.valueOf(item.getQuantity())
	            )
	        );
	    }

	    return total;
	}
	
	public void clear() {
        items.clear();
    }
}
