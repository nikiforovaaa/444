package barBossHouse;

import java.time.LocalDateTime;

public interface Order {

    Customer getCustomer();
    void setCustomer(Customer customer);
    boolean add(MenuItem dish);
    boolean remove(MenuItem menuItem);
    int removeAll(MenuItem menuItem);
    int dishQuantity0(MenuItem allItem);
    int dishQuantity(String DishName);
    MenuItem[] getDishes();
    double costTotal();
    String[] dishesNames();
    MenuItem[] sortedDishesByCostDesc();
    String toString();
    boolean equals(Object obj);
    int hashCode();
    public void setToday();
    public LocalDateTime getToday();
}
