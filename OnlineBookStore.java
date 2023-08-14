import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OnlineBookStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Bookstore!");
        boolean isAdmin = false;
        User loggedInUser = null;
        UserManager userManager = new UserManager();
        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Add Book to Catalog\n4. View Book Catalog");
            System.out.println("5. Add Book to Cart\n6. Remove Book from Cart\n7. View Shopping Cart");
            System.out.println("8. Process Order\n9. Restock Book\n10. Search Books\n11. Add to Wishlist\n12. View Wishlist");
            System.out.println("13. Add Review and Rating\n14. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter username: ");
                String username = scanner.next();
                System.out.println("Enter password: ");
                String password = scanner.next();
                System.out.println("Enter email: ");
                String email = scanner.next();

                boolean success = userManager.registerUser(username, password, email);
                if (success) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Username already exists. Please choose a different username.");
                }
            } else if (choice == 2) {
                System.out.println("Enter username: ");
                String username = scanner.next();
                System.out.println("Enter password: ");
                String password = scanner.next();

                loggedInUser = userManager.loginUser(username, password);
                if (loggedInUser != null) {
                    System.out.println("Login successful!");
                    isAdmin = username.equals("admin"); // Assume "admin" is the admin username
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            } else if (choice == 3) {
                if (isAdmin) {
                    System.out.println("Enter book title: ");
                    String title = scanner.next();
                    System.out.println("Enter author: ");
                    String author = scanner.next();
                    System.out.println("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.println("Enter availability: ");
                    int availability = scanner.nextInt();
                    System.out.println("Enter stock quantity: ");
                    int stockQuantity = scanner.nextInt();

                    Book newBook = new Book(title, author, price, availability, stockQuantity);
                    userManager.addBookToCatalog(newBook);
                } else {
                    System.out.println("You must be logged in as an admin to add books to the catalog.");
                }
            } else if (choice == 4) {
                userManager.displayBookCatalog();
            } else if (choice == 5) {
                if (loggedInUser != null) {
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.next();
                    userManager.addToCart(loggedInUser, bookTitle);
                } else {
                    System.out.println("You must be logged in to add books to your cart.");
                }
            } else if (choice == 6) {
                if (loggedInUser != null) {
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.next();
                    userManager.removeFromCart(loggedInUser, bookTitle);
                } else {
                    System.out.println("You must be logged in to remove books from your cart.");
                }
            } else if (choice == 7) {
                if (loggedInUser != null) {
                    userManager.displayCart(loggedInUser);
                } else {
                    System.out.println("You must be logged in to view your cart.");
                }
            } else if (choice == 8) {
                if (loggedInUser != null) {
                    Order order = userManager.processOrder(loggedInUser);
                    if (order != null) {
                        order.setStatus("Processed");
                        System.out.println("Payment successful! Order status: " + order.getStatus());
                    }
                } else {
                    System.out.println("You must be logged in to place an order.");
                }
            } else if (choice == 9) {
                if (isAdmin) {
                    System.out.println("Enter book title: ");
                    String bookTitle = scanner.next();
                    System.out.println("Enter quantity to restock: ");
                    int quantity = scanner.nextInt();
                    userManager.restockBook(bookTitle, quantity);
                } else {
                    System.out.println("You must be logged in as an admin to restock books.");
                }
            } else if (choice == 10) {
                System.out.println("Enter search keyword (title or author): ");
                String keyword = scanner.next();
                List<Book> searchResults = userManager.searchBooks(keyword);
                if (searchResults.isEmpty()) {
                    System.out.println("No matching books found.");
                } else {
                    System.out.println("Search Results:");
                    for (Book book : searchResults) {
                        System.out.println(book);
                    }
                }
            } else if (choice == 11) {
                if (loggedInUser != null) {
                    System.out.print("Enter book title to add to wishlist: ");
                    String bookTitle = scanner.next();
                    userManager.addToWishlist(loggedInUser, bookTitle);
                } else {
                    System.out.println("You must be logged in to add books to your wishlist.");
                }
            } else if (choice == 12) {
                if (loggedInUser != null) {
                    userManager.displayWishlist(loggedInUser);
                } else {
                    System.out.println("You must be logged in to view your wishlist.");
                }
            } else if (choice == 13) {
                if (loggedInUser != null) {
                    System.out.print("Enter book title for review: ");
                    String bookTitle = scanner.next();
                    System.out.print("Enter review comment: ");
                    String comment = scanner.next();
                    System.out.print("Enter rating (1 to 5): ");
                    int rating = scanner.nextInt();
                    userManager.addReview(loggedInUser, bookTitle, comment, rating);
                } else {
                    System.out.println("You must be logged in to add reviews.");
                }
            } else if (choice == 14) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    static class Book {
        private String title;
        private String author;
        private double price;
        private int availability;
        private int stockQuantity;
        private List<Review> reviews;

        public Book(String title, String author, double price, int availability, int stockQuantity) {
            this.title = title;
            this.author = author;
            this.price = price;
            this.availability = availability;
            this.stockQuantity = stockQuantity;
            this.reviews = new ArrayList<>();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getAvailability() {
            return availability;
        }

        public void setAvailability(int availability) {
            this.availability = availability;
        }

        public int getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
        }
        public void addReview(Review review) {
            reviews.add(review);
            System.out.println("Review added successfully!");
        }

        public List<Review> getReviews() {
            return new ArrayList<>(reviews);
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author + ", Price: $" + price + ", Availability: " + availability
                    + ", Stock Quantity: " + stockQuantity;
        }
    }

    static class ShoppingCart {
        private List<Book> cartItems;

        public ShoppingCart() {
            this.cartItems = new ArrayList<>();
        }

        public synchronized void addToCart(Book book) {
            cartItems.add(book);
        }

        public synchronized void removeFromCart(Book book) {
            cartItems.remove(book);
        }

        public synchronized void clearCart() {
            cartItems.clear();
        }

        public synchronized List<Book> getCartItems() {
            return new ArrayList<>(cartItems);
        }

        // Calculate the total price of the books in the cart
        public synchronized double calculateTotalPrice() {
            double total = 0.0;
            for (Book book : cartItems) {
                total += book.getPrice();
            }
            return total;
        }

        public void displayCart() {
            if (cartItems.isEmpty()) {
                System.out.println("Your shopping cart is empty.");
            } else {
                System.out.println("Shopping Cart:");
                for (Book book : cartItems) {
                    System.out.println(book);
                }
            }
        }
    }

    static class UserManager {
        private Map<String, User> users;
        private Map<String, Book> bookCatalog;
        private Map<User, Wishlist> userWishlists;
        public UserManager() {
            this.users = new HashMap<>();
            this.bookCatalog = new HashMap<>();
            this.userWishlists = new HashMap<>();
        }
        public synchronized boolean registerUser(String username, String password, String email) {
            if (!users.containsKey(username)) {
                User newUser = new User(username, password, email);
                users.put(username, newUser);
                System.out.println("Registration successful!");
                return true;
            } else {
                System.out.println("Username already exists. Please choose a different username.");
                return false;
            }
        }

        public synchronized User loginUser(String username, String password) {
            User user = users.get(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return user;
            } else {
                System.out.println("Invalid credentials. Please try again.");
                return null;
            }
        }

        public synchronized void addToCart(User user, String bookTitle) {
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                user.getShoppingCart().addToCart(book);
                System.out.println("Book added to cart successfully!");
            } else {
                System.out.println("Book not found in the catalog.");
            }
        }

        public synchronized void removeFromCart(User user, String bookTitle) {
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                user.getShoppingCart().removeFromCart(book);
                System.out.println("Book removed from cart successfully!");
            } else {
                System.out.println("Book not found in the cart.");
            }
        }

        public synchronized void displayCart(User user) {
            user.getShoppingCart().displayCart();
        }

        public synchronized void addBookToCatalog(Book book) {
            bookCatalog.put(book.getTitle(), book);
            System.out.println("Book added to catalog successfully!");
        }

        public synchronized void displayBookCatalog() {
            System.out.println("\nBook Catalog:");
            for (Book book : bookCatalog.values()) {
                System.out.println(book);
            }
        }

        public synchronized Book findBookByTitle(String title) {
            return bookCatalog.get(title);
        }

        public synchronized void restockBook(String bookTitle, int quantity) {
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                book.setStockQuantity(book.getStockQuantity() + quantity);
                System.out.println("Book restocked successfully!");
            } else {
                System.out.println("Book not found in the catalog.");
            }
        }
        public synchronized void addReview(User user, String bookTitle, String comment, int rating) {
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                Review review = new Review(user, book, comment, rating);
                book.addReview(review);
            }
            else {
                System.out.println("Book not found in the catalog.");
            }
        }
        public synchronized List<Book> searchBooks(String keyword) {
            List<Book> searchResults = new ArrayList<>();
            for (Book book : bookCatalog.values()) {
                if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())|| book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(book);
                }
            }
            return searchResults;
        }

        public synchronized Order processOrder(User user) {
            List<Book> cartItems = user.getShoppingCart().getCartItems();
            if (cartItems.isEmpty()) {
                System.out.println("Your cart is empty. Unable to process order.");
                return null;
            }

            double totalAmount = user.getShoppingCart().calculateTotalPrice();
            System.out.println("Total amount for the order: $" + totalAmount);

            // Simulate actual payment processing
            // Here, we assume the payment amount is equal to the total amount
            double paymentAmount = totalAmount;
            boolean paymentSuccessful = user.processPayment(paymentAmount);

            if (paymentSuccessful) {
                Order order = new Order(user, cartItems);
                user.getShoppingCart().clearCart();
                return order;
            } else {
                System.out.println("Payment failed. Unable to process order.");
                return null;
            }
        }
        public synchronized void addToWishlist(User user, String bookTitle) {
            Book book = findBookByTitle(bookTitle);
            if (book != null) {
                Wishlist wishlist = userWishlists.getOrDefault(user, new Wishlist());
                wishlist.addToWishlist(book);
                userWishlists.put(user, wishlist);
                System.out.println("Book added to wishlist successfully!");
            } else {
                System.out.println("Book not found in the catalog.");
            }
        }

        public synchronized void displayWishlist(User user) {
            Wishlist wishlist = userWishlists.getOrDefault(user, new Wishlist());
            wishlist.displayWishlist();
        }
    }

    static class Wishlist {
        private List<Book> wishlistItems;

        public Wishlist() {
            this.wishlistItems = new ArrayList<>();
        }

        public synchronized void addToWishlist(Book book) {
            wishlistItems.add(book);
        }

        public synchronized void removeFromWishlist(Book book) {
            wishlistItems.remove(book);
        }

        public synchronized void clearWishlist() {
            wishlistItems.clear();
        }

        public synchronized List<Book> getWishlistItems() {
            return new ArrayList<>(wishlistItems);
        }

        public void displayWishlist() {
            if (wishlistItems.isEmpty()) {
                System.out.println("Your wishlist is empty.");
            } else {
                System.out.println("Wishlist:");
                for (Book book : wishlistItems) {
                    System.out.println(book);
                }
            }
        }
    }

    static class Order {
        private static int nextOrderId = 1;
        private int orderId;
        private User user;
        private List<Book> orderedBooks;
        private double totalAmount;
        private String status;

        public Order(User user, List<Book> orderedBooks) {
            this.orderId = nextOrderId++;
            this.user = user;
            this.orderedBooks = new ArrayList<>(orderedBooks);
            calculateTotalAmount();
            this.status = "Pending";
        }

        public int getOrderId() {
            return orderId;
        }

        public User getUser() {
            return user;
        }

        public List<Book> getOrderedBooks() {
            return new ArrayList<>(orderedBooks);
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public String getStatus() {
            return status;
        }

        private void calculateTotalAmount() {
            totalAmount = 0.0;
            for (Book book : orderedBooks) {
                totalAmount += book.getPrice();
            }
        }

        public synchronized void generateInvoice() {
            System.out.println("--------- Invoice ---------");
            System.out.println("Order ID: " + orderId);
            System.out.println("User: " + user.getUsername());
            System.out.println("Books Purchased:");
            for (Book book : orderedBooks) {
                System.out.println("   " + book.getTitle() + " - $" + book.getPrice());
            }
            System.out.println("Total Amount: $" + totalAmount);
            System.out.println("---------------------------");
        }

        // Process payment for the order
        public synchronized boolean processPayment(double amount) {
            if (amount >= totalAmount) {
                System.out.println("Payment processed successfully!");
                return true;
            } else {
                System.out.println("Insufficient payment. Please try again.");
                return false;
            }
        }

        // Set the status of the order
        public synchronized void setStatus(String status) {
            this.status = status;
        }
    }

    static class User {
        private String username;
        private String password;
        private String email;
        private ShoppingCart shoppingCart;

        public User(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.shoppingCart = new ShoppingCart();
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public ShoppingCart getShoppingCart() {
            return shoppingCart;
        }

        public synchronized boolean processPayment(double amount) {
            if (amount >= 0) {
                System.out.println("Payment processed successfully!");
                return true;
            } else {
                System.out.println("Insufficient payment. Please try again.");
                return false;
            }
        }
    }
    static class Review {
        private User user;
        private Book book;
        private String comment;
        private int rating;

        public Review(User user, Book book, String comment, int rating) {
            this.user = user;
            this.book = book;
            this.comment = comment;
            this.rating = rating;
        }

        public User getUser() {
            return user;
        }

        public Book getBook() {
            return book;
        }

        public String getComment() {
            return comment;
        }

        public int getRating() {
            return rating;
        }
    }
}
