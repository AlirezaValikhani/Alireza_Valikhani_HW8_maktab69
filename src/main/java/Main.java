import model.human.Customer;
import model.thing.Category;
import model.thing.Product;
import model.thing.ShoppingCart;
import repository.Singleton;
import service.*;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    static Connection connection = Singleton.getInstance().getConnection();
    static Scanner scanner = new Scanner(System.in);
    static CategoryService categoryService = new CategoryService(connection);
    static CustomerService customerService = new CustomerService(connection);
    static OrderService orderService = new OrderService(connection);
    static OrderToProductService orderToProductService = new OrderToProductService(connection);
    static ProductService productService = new ProductService(connection);
    static ShoppingCartToProductService shoppingCartToProductService = new ShoppingCartToProductService(connection);
    static ShoppingCartService shoppingCartService = new ShoppingCartService(connection);
    static ManagerService managerService = new ManagerService(connection);


    public static void main(String[] args) {

        System.out.println("Welcome, signUp or logIn? \n" +
                "1. signUp \n" +
                "2. logIn \n" +
                "3. Show product categories \n" +
                "4. Exit");
        Integer input = scanner.nextInt();
        switch (input) {
            case 1:
                signUp();
                break;
            case 2:
                logIn();
                break;
            case 3:
                showProductCategory();
                break;
            case 4:
                System.out.println("Thank you for your shopping.");
                break;


        }
    }


    public static void signUp() {
        System.out.println("Please choose your username: ");
        String username = scanner.nextLine();
        scanner.nextLine();
        if (customerService.readByUsername(username) == null) {
            System.out.println("Please enter a password: ");
            String password = scanner.nextLine();
            System.out.println("Please enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Please enter your last name: ");
            String lastName = scanner.nextLine();
            System.out.println("Please enter your email: ");
            String email = scanner.nextLine();
            System.out.println("Please enter your address: ");
            String address = scanner.nextLine();
            System.out.println("How much do you charge your account? ");
            Double balance = scanner.nextDouble();
            Customer newCustomer = new Customer(null, firstName, lastName, username,
                    password, email, address, balance, new ShoppingCart(null, 0.0));
            Integer shoppingCartId = shoppingCartService.insert(newCustomer.getShoppingCart());
            newCustomer.getShoppingCart().setId(shoppingCartId);
            Integer customerId = customerService.insert(newCustomer);
            System.out.println("Your ID number is: " + customerId);
        }
    }

    public static void logIn() {
        String username, password;
        do {
            System.out.println("Enter your username: ");
            username = scanner.next();
            System.out.println("Enter your password: ");
            password = scanner.next();
            if ((managerService.readByUsername(username) != null) &&
                    (managerService.readByUsername(username).getPassword().equals(password))) {
                managerMenu();
                break;
            } else if ((customerService.readByUsername(username) != null) &&
                    (customerService.readByUsername(username).getPassword().equals(password))) {
                //customerMenu();
                break;
            } else
                System.out.println("Wrong username or password!");
        } while (true);
    }

    public static void showProductCategory() {
        System.out.println(categoryService.readAll().toString());
        Integer id;
        do {
            System.out.println("Choose the category id: ");
            id = scanner.nextInt();
            System.out.println(categoryService.readAllById(id).toString());
        } while (categoryService.readAllById(id).size() != 0);

    }

    public static void managerMenu() {
        System.out.println("1. Add product: \n" +
                "2. Add category: \n" +
                "3. Add stock");
        Integer managerChoice = scanner.nextInt();
        switch (managerChoice) {
            case 1:
                addProduct();
                break;
            case 2:
                //addCategory();
                break;
            case 3:
                //addStock();
                break;
        }
    }

    public static void addProduct() {
        System.out.println("*******************\n Product information\n*******************");
        System.out.print("Please enter product name : ");
        String productName = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Please enter product price : ");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Please enter product description : ");
        String description = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Please enter product quantity : ");
        Integer quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter category Id : ");
        Integer categoryId = scanner.nextInt();
        Product product = new Product(0, productName, description, price,
                quantity, new Category(categoryId));
                productService.insert(product);
    }

}
