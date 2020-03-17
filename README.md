# Description #

Users will be able to enter online store and browse a variety of items and add them to their shopping cart. Upon user requests they will be able to checkout and their order will be processed. Users will be able to review their order history and credentials will be persistently saved. Upon logging in if they saved items into their cart then they will remain there until items are removed.  

## Unique Features

* shop.User shall be able to review order history.
* Cart shall automatically update to keep track of order total.
* Items shall provide information on the following:
  * Price
  * Product Description 
  * Photo 
* Upon signing back in, the online store shall persist data on items that were previously added into the cart.


## Classes


### shop.User Class 
 * Shopping Cart
 * Name
 * Email
 * ID
 
### Shopping Cart
 * Items Map<shop.itemlists.shop.itemlists, Quantity>
 * Total Items List
 
### shop.itemlists.shop.itemlists
 * ItemID
 * shop.itemlists.shop.itemlists Price
 * Category
 * shop.Brand
 
### shop.Order
 * UserID
 * Shopping Cart
 * Date
 
### shop.Brand
 * BrandName
 * shop.itemlists.shop.itemlists
 
