package barBossHouse;

import java.time.LocalDateTime;

public interface Order {
    //todo посмотри внимательно по
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
    //todo это что еще за Today такой? today-сегодня, а тебе нужна дата заказа.
    public void setToday();  //todo а почему у тебя set без параметра ?!?!?!?!?!
    public LocalDateTime getToday();
}
