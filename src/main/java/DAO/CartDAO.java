package DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import Entity.CartItem;
import Entity.Product;

public class CartDAO {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Product drink) {
        if (drink == null || drink.getProductId() == null) {
            return;
        }

        for (CartItem item : items) {
            // Compare ProductId (Integer) with ProductId (Integer)
            if (item.getDrink() != null && item.getDrink().getProductId().equals(drink.getProductId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(drink, 1));
    }

    public void deleteItem(int productId) {
        items.removeIf(i -> i.getDrink() != null && i.getDrink().getProductId().equals(productId));
    }

    public void updateItem(int productId, int qty) {
        if (qty <= 0) {
            deleteItem(productId);
            return;
        }

        for (CartItem item : items) {
            if (item.getDrink() != null && item.getDrink().getProductId().equals(productId)) {
                item.setQuantity(qty);
                return;
            }
        }
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            if (item.getDrink() != null && item.getDrink().getPrice() != null) {
                BigDecimal itemTotal = item.getDrink().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()));
                total = total.add(itemTotal);
            }
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}
