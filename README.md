# Description #

Users will be able to enter online store and browse a variety of items and add them to their shopping cart. Upon user requests they will be able to checkout and their order will be processed. 

## Unique Features

* User shall be able to review order history.
* Cart shall automatically update to keep track of order total.
* Items shall provide information on the following:
  * Price
  * Product Description 
  * Price
  * Photo 
* Upon signing back in, the online store shall persist data on items that were previously added into the cart.


## Classes

### User Class 
 * Shopping Cart
 * Name
 * Email
 * ID
 
### Shopping Cart
 * Items Map<Item, Quantity>
 * Total Items List
 
### Item
 * ItemID
 * Item Price
 * Category
 * Brand
 
### Order
 * UserID
 * Shopping Cart
 * Date
 
### Brand
 * BrandName
 * Item
 
