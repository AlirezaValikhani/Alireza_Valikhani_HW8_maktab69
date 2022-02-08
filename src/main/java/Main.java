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
        firstMenu();
    }

    public static void firstMenu(){
        System.out.println("Welcome \n" +
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
        while (true) {
            System.out.println("Please choose your username: ");
            String username = scanner.next();
            if (customerService.readByUsername(username) != null) {
                System.out.println("This username exists please choose another username");
            } else {
                System.out.println("Please enter a password: ");
                String password = scanner.next();
                System.out.println("Please enter your first name: ");
                String firstName = scanner.next();
                System.out.println("Please enter your last name: ");
                String lastName = scanner.next();
                System.out.println("Please enter your email: ");
                String email = scanner.next();
                System.out.println("Please enter your address: ");
                String address = scanner.next();
                System.out.println("How much do you charge your account? ");
                Double balance = scanner.nextDouble();
                Customer newCustomer = new Customer(null, firstName, lastName, username,
                        password, email, address, balance, new ShoppingCart(null, 0.0));
                Integer shoppingCartId = shoppingCartService.insert(newCustomer.getShoppingCart());
                newCustomer.getShoppingCart().setId(shoppingCartId);
                Integer customerId = customerService.insert(newCustomer);
                System.out.println("**********************\n Your ID number is: " + customerId + "\n**********************");
                firstMenu();
            }
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
                customerMenu();
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
            if(categoryService.readAllById(id) == null){
                customerMenu();
            }else System.out.println(categoryService.readAllById(id).toString());
        } while (categoryService.readAllById(id).size() != 0);
    }

    public static void managerMenu() {
        System.out.println("1. Add product and product inventory \n" +
                "2. Add category \n" +
                "3. Update product\n" +
                "4. Back");
        Integer managerChoice = scanner.nextInt();
        switch (managerChoice) {
            case 1:
                addProduct();
                break;
            case 2:
                addCategory();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                firstMenu();
                break;
        }
    }

    public static void addProduct() {
        System.out.println("*******************\n Product information\n*******************");
        showProductCategory();
        System.out.print("Please enter product name : ");
        String productName = scanner.next();
        System.out.print("Please enter product price : ");
        Double price = scanner.nextDouble();
        System.out.print("Please enter product description : ");
        String description = scanner.next();
        System.out.print("Please enter product quantity : ");
        Integer quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter super category id : ");
        Integer superCategoryId = scanner.nextInt();
        Category category =  new Category(productName,(new Category(superCategoryId)));
        Product product1 = new Product(0, productName, description, price,
                quantity, category);
        productService.insert(product1);
        categoryService.insert(category);
        System.out.println("*************************\n Product added successfully\n*************************");
        managerMenu();
    }

    public static void addCategory(){
        System.out.println("If you want to add a super category," +
                " please enter 0 else enter your super category id : ");
        Integer input = scanner.nextInt();
        if(input == 0){
            System.out.println("Enter super category name : ");
            String categoryName = scanner.next();
            Category category = new Category(categoryName,null);
            System.out.println("Super category id is : " + categoryService.insertSuperCategory(category));
        }else {
            if(categoryService.readAllById(input) != null){
                System.out.println("Enter super category name : ");
                String categoryName = scanner.next();
                Category category = new Category(categoryName,new Category(input));
                categoryService.insert(category);
                System.out.println("Category added successfully");
            }
        }
    }

    public static void updateProduct() {
        System.out.println("****************\n Update product\n****************");
        System.out.print("Enter product id : ");
        Integer productId = scanner.nextInt();
        Product product = new Product(productId);
        if (productService.read(product) != null){
            System.out.println("1. Edit product name\n" +
                    "2. Edit product price\n" +
                    "3. Edit product description\n" +
                    "4. Edit product quantity\n" +
                    "5. Edit super category Id");
            Integer input = scanner.nextInt();
            switch (input){
                case 1:
                    editProductName(productId);
                    productService.read(product);
                    break;
                case 2:
                    editPrice(productId);
                    break;
                case 3:
                    editDescription(productId);
                    break;
                case 4:
                    editQuantity(productId);
                    break;
                case 5:
                    editSuperCategoryId(productId);
                    break;
            }
        }
    }

    public static void editSuperCategoryId(Integer productId){
        System.out.print("Edit super category id : ");
        Integer superCategoryId = scanner.nextInt();
        Category category = new Category(new Category(superCategoryId),productId);
        Product product1 = new Product(productId,productService.readById(productId).getProductName(),
                productService.readById(productId).getDescription(),
                productService.readById(productId).getPrice(),
                productService.readById(productId).getStock(),
                new Category(new Category(superCategoryId)));
        productService.updateSuperCategoryId(product1);
        categoryService.updateSuperCategoryId(category);
        System.out.println("************************************\n Product super category id updated successfully\n************************************");
        managerMenu();
    }

    public static void editProductName(Integer productId){
        System.out.print("Edit product name : ");
        String productName = scanner.next();
        Category category = new Category(productId,productName);
        Product product1 = new Product(productName,
                productService.readById(productId).getDescription(),
                productService.readById(productId).getPrice(),
                productService.readById(productId).getStock(),
                productId);
        productService.update(product1);
        categoryService.updateSuperCategoryName(category);
        System.out.println("*****************************\n Product name updated successfully\n*****************************");
        managerMenu();
    }

    public static void editPrice(Integer productId){
        System.out.print("Edit product price : ");
        Double productPrice= scanner.nextDouble();
        Product product1 = new Product(productService.readById(productId).getProductName(),
                productService.readById(productId).getDescription(),
                productPrice,
                productService.readById(productId).getStock(),
                productId);
        productService.update(product1);
        System.out.println("*****************************\n Product price updated successfully\n*****************************");
        managerMenu();
    }

    public static void editDescription(Integer productId){
        System.out.print("Edit product description : ");
        String productDescription= scanner.next();
        Product product1 = new Product(productService.readById(productId).getProductName(),
                productDescription,
                productService.readById(productId).getPrice(),
                productService.readById(productId).getStock(),
                productId);
        productService.update(product1);
        System.out.println("*******************************************\n Product description updated successfully\n*******************************************");
        managerMenu();
    }

    public static void editQuantity(Integer productId){
        System.out.print("Edit product quantity : ");
        Integer productQuantity= scanner.nextInt();
        Product product1 = new Product(productService.readById(productId).getProductName(),
                productService.readById(productId).getDescription(),
                productService.readById(productId).getPrice(),
                productQuantity,productId);
        productService.update(product1);
        System.out.println("*****************************\n Product quantity updated successfully\n*****************************");
        managerMenu();
    }

    public static void customerMenu(){
        System.out.println("1. View categories \n" +
                "2. Add the product to the shopping cart \n" +
                "3. Charge your balance\n" +
                "4. Back");
        Integer customerChoice = scanner.nextInt();
        switch (customerChoice) {
            case 1:
                showProductCategory();
                break;
            case 2:
                addProductIntoShoppingCart();
                break;
            case 3:
                chargeBalance();
                break;
            case 4:
                firstMenu();
                break;
        }
    }

    public static void chargeBalance(){
        System.out.println("Please enter your id : ");
        Integer id = scanner.nextInt();
        System.out.println("How much do you charge your balance?");
        Double balance = scanner.nextDouble();
        if(customerService.readBalance(id) != null){
            Double finalBalance = customerService.readBalance(id).getBalance() + balance;
            customerService.updateBalance(id, finalBalance);
            System.out.println("**************************\n Your balance is : " + finalBalance + "\n\"**************************");
            customerMenu();
        }else{
            customerService.updateBalance(id, balance);
            System.out.println("**************************\n Your balance is : " + balance + "\n\"**************************");
            customerMenu();
        }
    }

    public static void addProductIntoShoppingCart(){
        System.out.println("Choose product id : ");
        Integer productId = scanner.nextInt();
        Product product = new Product(productId);
        if(productService.read(product) != null){
            System.out.println(productService.read(product).getStock());
            chooseProduct(product);
        }else System.out.println("Enter correct id!!!");
    }

    public static void chooseProduct(Product product){
        System.out.println("Number of products in stock is : "
                + productService.read(product).getStock() + " How many do you want : ");
        Integer numberOfProduct = scanner.nextInt();
        if(numberOfProduct <= productService.read(product).getStock()){

        }else System.out.println("This number is not available in stock!!!");
    }
}
