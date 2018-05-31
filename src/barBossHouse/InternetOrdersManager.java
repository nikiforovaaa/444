package barBossHouse;

import java.time.LocalDate;

public  class InternetOrdersManager implements OrdersManager {

    private QueueNode head;
    private QueueNode tail;
    private int size;
    // private int count;

    public InternetOrdersManager() {
        size=0;
    }
    public InternetOrdersManager(MenuItem order) {

    }
    //Возвращающий количество заказов, выполненных в заданный день (принимает
    //экземпляр LocalDate в качестве параметра)
    public int quantityOrdersToday (LocalDate day){
        QueueNode node = head;
        int count = 0;
        for(int j=0; j<size; j++) {
            if (node.getValue().getToday().toLocalDate().equals(day))//todo Вот блин здесь то нормально сделала, а в TableOrderManager - нет.
                count++;
            node = node.getNext();

        }

        return count;
    }

    //Возвращающий список заказов, выполненных в заданный день (принимает
    // экземпляр LocalDate в качестве параметра)
    public Order[] ordersToday (LocalDate day){
        QueueNode node = head;
        int count = 0;
        for(int j=0; j<size; j++) {
            if (node.getValue().getToday().toLocalDate().equals(day))
                count++;
            node = node.getNext();
        }
        Order[] ordersToday = new TableOrder[count];
        for(int i=0,j=0; i<size; i++) {
            if (node.getValue().getToday().toLocalDate().equals(day)) {
                ordersToday[i] = node.getValue();
                j++;
            }
            node=node.getNext();
        }
        return ordersToday;
    }

    //Возвращающий список заказов заданного клиента (принимает клиента в
    //качестве параметра).
    public Order[] customerOrders (Customer customer){
        QueueNode node = head;
        int count=0;
        for(int j=0; j<size; j++){
            if (customer.equals(node.getValue().getCustomer()))
                count++;
        }
        Order[] customerOrders = new TableOrder[count];
        for(int i=0,j=0; j<size; i++){
            if (customer.equals(node.getValue().getCustomer()))
                customerOrders[j]=node.getValue();
            j++;
        }
        return customerOrders;
    }

    public boolean add(Order order){

        QueueNode node = new QueueNode();
        //todo - нет, нужно пройтись по каждому ноду и у каждого заказа проверить время и кастомера
        if(node.getValue().getCustomer().equals(node.getNext().getValue().getCustomer()) && node.getValue().getToday().equals(node.getNext().getValue().getToday()))
            throw new AlreadyAddedException("Order exist");
        if (head == null)
        {
            head = node;
            tail = node;
        }
        else
        {
            tail.setNext(node);
            tail = node;
        }

        return true;
    }
    public Order order()
    {
        return head.getValue();
    }

    public int dishQuantity(){
        return size;
    }

    public Order remove(){
        Order dish = head.getValue();
        if (size != 0) {
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return dish;
        }
        return dish;
    }

    public double costTotal() {
        double cost =0.0;
        QueueNode node = head;
        while (node!=null)
        {
            cost+=node.getValue().costTotal();
            node=node.getNext();
        }
        return cost;
    }

    //возвращающий общее среди всех занятых столиков количество заданных позиций.
    //Принимает в качестве параметра объект по ссылке типа MenuItem
    public int ordersQuantity(MenuItem item) {
        int count = 0;
        QueueNode node = head;
        if (size !=0) {
            for (int i = 0; i < size; i++) {
                count += node.getValue().dishQuantity0(item);
                node = node.getNext();
            }
            return count;
        }
        else return 0;
    }

    //возвращающий общее среди всех занятых столиков количество заказанных порций
    //заданного блюда по его имени. Принимает имя блюда в качестве параметра.
    public int itemsQuantity(String item) {
        int count = 0;
        QueueNode node = head;
        if (size !=0) {
            for (int i = 0; i < size; i++) {
                count += node.getValue().dishQuantity(item);
                node = node.getNext();
            }
            return count;
        }
        else return 0;
    }


    @Override
    public void add(MenuItem menuItem, int tableNumber) {
    }

    @Override
    public int busyTables() {
        return 0;
    }

    @Override
    public TableOrder[] getOrders() {
        return new TableOrder[0];
    }

    @Override
    public double ordersCostSummary() {
        return 0;
    }

    @Override
    public int dishOuantity(String dishName) {
        return 0;
    }
}

class QueueNode {
    private Order value;
    private QueueNode previous;
    private QueueNode next;



    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public void setValue(){
        this.value=value;
    }
    public Order getValue(){
        return value;
    }

    public void setPrevious(QueueNode previous) {
        this.previous=previous;
    }

    public QueueNode getPrevious() {
        return previous;
    }

}

