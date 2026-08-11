package Entity;

import java.util.List;

public class CartItem {
 Product drink;
 int quantity;
 String Size;
 
 public String getSize() {
	 return Size;
 }
 
 public void setSize(String size) {
	 Size = size;
 }
 
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
 public CartItem(Product drink, int quantity, String size) {
	super();
	this.Size = size;
	this.drink = drink;
	this.quantity = quantity;
 }
 public CartItem() {
	super();
 }
 
 
}
