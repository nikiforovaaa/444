package barBossHouse;

import java.time.LocalDateTime;

public class InternetOrder implements Order {
    //todo поля приватные
    int size;
    ListNode head;
    ListNode tail;
    private Customer customer;
    MenuItem[] menuItems; //todo А че здесь делает массив?!?!?! никаких массивов, храним только head и tail
    private static final int SIZE_DEFAULT = 16;
    LocalDateTime today = LocalDateTime.now();

    InternetOrder() {
        this.today = today; //todo Oh my gosh!
    }

    InternetOrder(MenuItem[] menuItems, Customer customer) {
        this.menuItems = menuItems; //todo пробегаешься по элементам menuItems и добавляешь в список
        this.customer = customer;
        this.today = today; //todo Oh my gosh!
    }

    public void setToday() {
        this.today = today;
    }

    public LocalDateTime getToday() {
        return today;
    }

    //добавляет блюдо в заказ(массив)
    public boolean add(MenuItem dish) {
        ListNode node = new ListNode();

        if (head == null)

        {
            head = node;
            tail = node;
        } else

        {
            tail.setNext(node);
            tail = node;
        }

        return true;
    }


    //удаляющий блюдо из заказа по его названию (принимает название блюда в качестве
//параметра). Если блюд с заданным названием несколько, удаляется первое найденное. Возвращает
//логическое значение (true, если элемент был удален)

    public boolean remove(MenuItem menuItem)
    {
        ListNode previous = null;
        ListNode current = head;if (current.getValue().equals(menuItem))
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                //todo ошибка. Здесь сравниваешь имя блюда сблюдом, а нужно блюдо с блюсдом
                if (current.value.getName().equals(menuItem)) {
                    current.Remove(previous, current);
                    return true;
                }
                previous=current;
                current=current.getNext();
            }
        }
        return false;
    }

    //удаляющий все блюда с заданным именем (принимает название блюда в качестве
//параметра). Возвращает число удаленных элементов.

    public int removeAll(MenuItem menuItem) {
        int count =0;
        ListNode current = head;
        ListNode previous = null;

        while (current!=null)
        {
            if (current.value.equals(menuItem))
            {
                if (previous != null)
                {
                    previous.next = current.next;
                    current=null;
                    if (current.next == null)
                    {
                        tail = previous;
                    }
                }
                else {
                    head = head.next;
                    if (head == null)
                    {
                        tail = null;
                    }
                }
                size--;
                count++;
            }

            previous = current;
            current = current.next;
        }

        return count;
    }

    public int removeAll(String menuItemName) {
        ListNode current = head;
        ListNode previous = null;
        int count =0;

        while (current!=null)
        {
            if(current.value.getName().equals(menuItemName)){
                current.Remove(previous,current);
                count++;
            }
            previous=current;
            current=current.getNext();
        }
        return count;
    }

    //возвращающий общее число блюд
    public int dishQuantity0(MenuItem allItem) { //todo это что за сферический метод в вакууме?!?! Он должен делать почти тоже самое, что и следующий метод, но вместо имени проерять сами блюда equals()
        return size;
    } //todo и 0 убери из имени. Есть такая штука - называется перегрузка


    //возвращающий число заказанных блюд (принимает название блюда в качестве параметра)
    public int dishQuantity(String menuItemName) {
        int count = 0;
        ListNode current = head;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (current.value.getName().equals(menuItemName))
                    count++;
                current = current.getNext();
            }
        }
        return count;
    }

    public MenuItem[] getDishes() {
        MenuItem[] itemsClear = new MenuItem[size];
        ListNode current = head;
        for (int i = 0; i < size; i++) {
            itemsClear[i] = current.value;
            current = current.getNext();
        }
        return itemsClear;
    }

    //возвращающий цену
    public double costTotal() {
        double cost = 0;
        ListNode current = head;
        for (int i = 0; i < size; i++) {
            cost += current.value.getPrice();
            current = current.getNext();
        }
        return cost;
    }

    public String[] dishesNames() {
        int uniqNames = size;
        ListNode current = head;

        int t, unique = 1;
        while (current!=null ){
            t = 0;
            for (int j = 0; j < size - 1; j++)
                if (current.getNext()!= null &&(!current.value.getName().equals(current.getNext().value.getName())))
                    t++;
            current = current.getNext();
            if (t == 1)
                unique++;
        }
        String[] Names = new String[unique];
        current = head;
        for(int i=0;i<size;i++){
            Names[i] = current.value.getName();
            i++;
            current = current.getNext();
        }
        return Names;
    }

    public MenuItem[] sortedDishesByCostDesc() {
        MenuItem[] sortedDishes = getDishes();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (sortedDishes[j].getPrice() < sortedDishes[j + 1].getPrice()) {
                    MenuItem tmp = sortedDishes[j];
                    sortedDishes[j] = sortedDishes[j + 1];
                    sortedDishes[j + 1] = tmp;
                }
            }
        }
        return sortedDishes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString(){
        StringBuilder tableOrder = new StringBuilder("InternetOrder: ");
        tableOrder.append(size+'\n');
        for(MenuItem o : menuItems) {
            tableOrder.append(o.toString()).append('\n');
        }
        return tableOrder.toString();
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj==null || !this.getClass().equals(obj.getClass()))
            return false;

        TableOrder equalsCheck = (TableOrder)obj;
        //todo Это нафига тебе массив? в цикле идешь по нодам
        MenuItem[] items = this.getDishes();
        for(int i =0; i<size; i++){
            if (this.dishQuantity0(items[i]) != equalsCheck.dishQuantity0(items[i]))
                return false;
        }
        return (this.customer.equals(equalsCheck.customer)
                && this.size==equalsCheck.size); //todo вот это обычно проверяют сначала, а уж потом по коллекциям проходят (чтоб более тяжелые проверки проверялись в конце)
    }

    public int hashCode(){
        int hash = 0;

        for (MenuItem i: menuItems) {
            hash^=i.hashCode();
        }
        return size^customer.hashCode()^hash;
    }
}

class ListNode {

    public MenuItem value;
    // private ListNode head;
    // private ListNode tail;
    // private ListNode previous;
    public ListNode next;



    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public void setValue(){
        this.value=value;
    }
    public MenuItem getValue(){
        return value;
    }

    public void Remove(ListNode previous, ListNode current)
    {
        InternetOrder io = new InternetOrder();
        if (previous != null) {
            if (current.getNext() == null) {
                io.tail = previous;
            }
            previous.setNext(current.getNext());

        }
        else {
            if (io.head.next == null) {
                io.head = null;
                io.tail = null;
            }
            io.head = io.head.next;
        }
        io.size--;
    }
}