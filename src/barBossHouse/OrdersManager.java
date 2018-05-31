package barBossHouse;

import java.time.LocalDate;
//todo посмотри внимательно какие методы должны быть в OrderManager-ре
public interface OrdersManager {
    void add(MenuItem menuItem, int tableNumber);
    int busyTables ();
    TableOrder[] getOrders ();
    double ordersCostSummary ();
    int dishOuantity (String dishName);
    boolean add(Order order);
    Order order();
    int dishQuantity();
    //todo опять today
    int quantityOrdersToday (LocalDate day);
    Order[] ordersToday (LocalDate day);
    Order[] customerOrders (Customer customer);
}