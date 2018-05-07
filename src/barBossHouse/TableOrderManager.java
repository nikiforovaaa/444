package barBossHouse;

import java.time.LocalDate;

public class TableOrderManager implements OrdersManager {
    private TableOrder[] orders;
    private static final int SIZE = 8;

    public TableOrderManager(int size) {
        if (size<0) throw new NegativeSizeException ("Size of the array must be >0");
        this.orders = new TableOrder[SIZE];
    }

    //Возвращающий количество заказов, выполненных в заданный день (принимает
    //экземпляр LocalDate в качестве параметра)
    public int quantityOrdersToday (LocalDate day){
        int count = 0;
        for(int j=0; j<SIZE; j++) {
            if (orders[j]!=null && orders[j].getToday().equals(day))
                count++;
        }
        return count;
    }

    //Возвращающий список заказов, выполненных в заданный день (принимает
    // экземпляр LocalDate в качестве параметра)
    public Order[] ordersToday (LocalDate day){
        int count = 0;
        for(int j=0; j<SIZE; j++) {
            if (orders[j]!=null && orders[j].getToday().equals(day))
                count++;
        }
        Order[] ordersToday = new TableOrder[count];
        for(int i =0,j=0; j<SIZE; i++){
            if (orders[j].getToday().equals(day) && orders!=null)
                ordersToday[j]=orders[j];
            j++;
        }
        return ordersToday;
    }

    //Возвращающий список заказов заданного клиента (принимает клиента в
    //качестве параметра).
    public Order[] customerOrders (Customer customer){
        int count=0;
        for(int j=0; j<SIZE; j++){
            if (customer.equals(orders[j].getCustomer()) && orders!=null)
                count++;
        }
        Order[] customerOrders = new TableOrder[count];
        for(int i =0,j=0; j<SIZE; i++) {
            if (customer.equals(orders[i].getCustomer()) && orders != null) {
                customerOrders[i] = orders[j];
                j++;
            }
        }
        return customerOrders;
    }

    //добавления заказа столику. В качестве параметров принимает номер столика и ссылку на
    //заказ
    public void add(TableOrder order, int tableNumber) {
        if(orders[tableNumber]!=null) throw new AlreadyAddedException("THIS TABLE IS BUSY!!!!!");
        orders[tableNumber] = order;
        System.out.println("Заказ+");
    }
    //получения заказа столика по его номеру. В качестве параметра принимает номер столика
    public TableOrder getOrder(int tableNumber) {
        return orders[tableNumber];
    }

    //метод добавления блюда к заказу. В качестве параметра принимает номер столика и блюдо
    public void addDish (MenuItem dish, int tableNumber)
    {
        for (int i=0;i<SIZE;i++)
        {
            orders[tableNumber].add(dish);
            //System.out.println("цып");
            break;
        }
    }

    public void add (MenuItem menuItem, int tableNumber){
        if(orders!=null)
            orders[tableNumber].add(menuItem);
    }


    //освобождения столика (по сути устанавливает значение соответствующего элемента
    //массива в null). В качестве параметра принимает номер столика.
    public void removeOrder (int tableNumber)
    {
        orders[tableNumber-1]=null;
        System.out.println("-");
    }

    //возвращающий номер первого найденного свободного столика.
    public int freeTableNumber ()
    {
        int freeTable=10;
        System.out.println("Свободный столик");
        for (int i=0;i<orders.length;i++)
        {
            if(orders[i]==null){
                freeTable=i;
                return freeTable;
            }
            if(orders[i]!=null) throw new NoFreeTableException("THIS TABLE IS BUSY!!!!!");
        }
        return freeTable;
    }

    //возвращающий массив номеров свободных столиков.
    public int[] freeTableNumbers ()
    {
        int quantityFreeTable=0;
        for (int i=0;i<orders.length;i++)
        {
            if(orders[i]==null)
                quantityFreeTable++;
        }
        int [] freeTable=new int[quantityFreeTable];
        for (int i=0,j=0; i<freeTable.length;i++)
        {
            if(orders[i]==null)
            {freeTable[j]=i; j++;}
        }
        return freeTable;
    }
    public int busyTables ()
    {
        int quantityBusyTable=0;
        for (int i=0;i<orders.length;i++)
        {
            if(orders[i]!=null)
                quantityBusyTable++;
        }
        return quantityBusyTable;
    }

    //возвращающий массив имеющихся на данный момент заказов.
    public TableOrder[] getOrders ()
    {
        int count = 0;
        for(int i =0; i<orders.length;i++){
            if(orders[i]!= null) {
                count++;
            }
        }
        TableOrder[] Orders = new TableOrder[SIZE];
        for(int i =0,j=0; j<count; i++){
            Orders[j]=orders[i];
            j++;
        }
        return Orders;
    }

    //возвращающий суммарную стоимость имеющихся на данный момент заказов
    public double ordersCostSummary ()
    {
        double cost =0.0;
        for (int i = 0; i < SIZE; i++) {
            if (orders[i] != null)
                cost += orders[i].costTotal();
        }
        return cost;
    }

    //возвращающий общее среди всех занятых столиков количество заказанных порций
    // заданного блюда по его имени. Принимает имя блюда в качестве параметра
    public int dishOuantity (String dishName)
    {
        int numDish = 0;
        for (int i = 0; i < SIZE; i++) {
            if (orders[i] != null) {
                numDish+=orders[i].dishQuantity(dishName);
            }
        }
        return numDish;
    }

    @Override
    public boolean add(Order order) {
        return false;
    }

    @Override
    public Order order() {
        return null;
    }


    @Override
    public int dishQuantity() {
        return 0;
    }

    public int dishOuantity (MenuItem menuItem)
    {
        int numDish = 0;
        for (int i = 0; i < SIZE; i++) {
            if (orders[i] != null) {
                numDish+=orders[i].dishQuantity(menuItem);
            }
        }
        return numDish;
    }

    public int remove (Order order){
        for (int i=0;i<SIZE;i++)
        {
            if(orders[i]!=null && orders[i].equals(order)){
                orders[i] = null;
                return i + 1;
            }

        }
        return -1;
    }
    public int removeAll (Order order){
        int count=0;
        for (int i=0;i<SIZE;i++)
        {
            if(orders[i]!=null && orders[i].equals(order)){
                orders[i] = null;
                count++;
            }

        }
        if (count==0)
            return -1;
        else return count;
    }

}
