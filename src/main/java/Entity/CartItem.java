package Entity;

import java.util.List;

public class CartItem {
 Drink drink;
 int quantity;
 public Drink getDrink() {
	return drink;
 }
 public void setDrink(Drink drink) {
	this.drink = drink;
 }
 public int getQuantity() {
	return quantity;
 }
 public void setQuantity(int quantity) {
	this.quantity = quantity;
 }
 public CartItem(Drink drink, int quantity) {
	super();
	this.drink = drink;
	this.quantity = quantity;
 }
 public CartItem() {
	super();
 }
 
 
}
