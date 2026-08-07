package Entity;

import java.util.List;

public class CartItem {
 Product drink;
 int quantity;
 public Product getDrink() {
	return drink;
 }
 public void setDrink(Product drink) {
	this.drink = drink;
 }
 public int getQuantity() {
	return quantity;
 }
 public void setQuantity(int quantity) {
	this.quantity = quantity;
 }
 public CartItem(Product drink, int quantity) {
	super();
	this.drink = drink;
	this.quantity = quantity;
 }
 public CartItem() {
	super();
 }
 
 
}
