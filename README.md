
#  Online Bookstore
# Description

The Online Bookstore application is a Java-based console application that allows users to register, log in, and manage an online bookstore. Features include book catalog management, shopping cart, wishlist, order processing, and book reviews. The application supports both user and admin functionalities, including adding and restocking books, adding reviews, and processing orders.
## Features

- User Management:
  - Register new users
  - Login for existing users
- Book Management (Admin only):
  - Add books to the catalog
  - Restock books
- Book Catalog:
  - View available books
  - Search books by title or author
- Shopping Cart:
  - Add books to the cart
  - Remove books from the cart
  - View cart contents
  - Process orders
- Wishlist:
  - Add books to the wishlist
  - View wishlist items
- Reviews:
  - Add reviews and ratings for books

## Getting Started

1. Clone the Repository:

```bash
git clone <repository-url>
cd <repository-directory>
```
2. Compile the Application:

```bash
javac OnlineBookStore.java
```
3. Run the Application:

```bash
java OnlineBookStore
```

## Usage


- Registration:

  - Select option 1 to register a new user. Provide a username, password, and email.
- Login:

  - Select option 2 to log in. Enter the username and password.
- Admin Functions:

  - Admins can select option 3 to add a new book to the catalog and option 9 to restock books.
- Book Catalog:

  - View the catalog by selecting option 4. Search for books using option 10.
- Shopping Cart:

  - Add books to your cart with option 5, remove books with option 6, view your cart with option 7, and process orders with option 8.
- Wishlist:

  - Add books to your wishlist with option 11 and view your wishlist with option 12.
- Reviews:

  - Add reviews for books with option 13.
- Exit:

  - Select option 14 to exit the application.


## Classes
- OnlineBookStore: Main class to run the application and handle user interactions.
- User: Represents a user with a username, password, email, and shopping cart.
- Book: Represents a book with attributes such as title, author, price, availability, and stock quantity. Includes methods for adding reviews.
- ShoppingCart: Manages the user's shopping cart, including adding, removing, and displaying items.
- Wishlist: Manages the user's wishlist, including adding, removing, and displaying items.
- Order: Represents an order with attributes such as order ID, user, ordered books, total amount, and status. Includes methods for processing payment and generating an invoice.
- Review: Represents a review with attributes such as user, book, comment, and rating.
- UserManager: Manages user registration, login, book catalog, cart operations, wishlist, and order processing.
## Dependencies
- Java Development Kit (JDK) 8 or higher
## Contributing

Feel free to fork the repository and submit pull requests with improvements or new features.


## License
This project is licensed under the MIT License. See the [LICENSE](https://choosealicense.com/licenses/mit/) file for details.


