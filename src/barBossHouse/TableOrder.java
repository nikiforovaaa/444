package barBossHouse;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TableOrder implements Order {
    private static final int SIZE_DEFAULT = 16;
    private MenuItem[] Dishes;
    public int size = 0;
    public Customer customer;
    LocalDateTime today= LocalDateTime.now();

    public TableOrder() {
        this(SIZE_DEFAULT, Customer.MATURE_UNKNOWN_CUSTOMER);
    }

    public TableOrder(int size, Customer customer) {
        if (size<0) throw new NegativeSizeException ("Size of the array must be >0");
        this.Dishes = new MenuItem[size];
        this.customer=customer;
        this.today=today;
    }

    public TableOrder(MenuItem[] Dishes, Customer customer) {
        System.arraycopy(Dishes, 0, this.Dishes, 0, SIZE_DEFAULT);
    }

    public void setToday(){
        this.today=today;
    }
    public LocalDateTime getToday(){
        return today;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //добавляет блюдо в заказ(массив)
    public boolean add(MenuItem dish) {
        boolean add = false;
        if (size >= SIZE_DEFAULT) {
            MenuItem[] dishesIncreased = new MenuItem[size*2];
            System.arraycopy(Dishes,0,dishesIncreased,0,size);
            Dishes = dishesIncreased;
            //Dishes = Arrays.copyOf(Dishes, 2 * Dishes.length);
        }
        if(dish instanceof Drink)
            if(Customer.getAge(customer.birthDate,customer.currentDate)<18 &&(((Drink)dish).isAlcoholicDrink()))
                throw new UnlawfulActionException("Customer is underage");
        if (getToday().getHour()<8 & getToday().getHour()>22)
            if((((Drink)dish).isAlcoholicDrink())) throw new UnlawfulActionException("Sale of alcohol is prohibited");
        Dishes[size]=dish;
        size++;
        System.out.print("Блюдо добавлено ");
        add = true;
        return add;
    }

    //удаляющий блюдо из заказа по его названию (принимает название блюда в качестве
//параметра). Если блюд с заданным названием несколько, удаляется первое найденное. Возвращает
//логическое значение (true, если элемент был удален)
    public boolean remove(String DishName) {
        boolean remove = false;
        for (int j = 0; j < size; j++) {
            if (Dishes[j].getName().equals(DishName)) {
                System.arraycopy(Dishes, j + 1, Dishes, j, (size-j) );
                remove = true;
                size-=1;
                System.out.print("Блюдо удалено ");
                break;
            }
        }
        return remove;
    }

    //удаляющий все блюда с заданным именем (принимает название блюда в качестве
//параметра). Возвращает число удаленных элементов.
    public int removeAll (String DishName)
    {
        int count=0;
        for(int i=0; i<size;i++) {
            if (Dishes[i].getName().equals(DishName)) {
                count++;
                System.arraycopy(Dishes, i+1, Dishes, i, size-i);
                //  Dishes[i] = Dishes[j];
                size--;
                System.out.println("цып цып цып");
            }
        }
        return count;
    }


    //возвращающий общее число блюд
    public int dishQuantity0(MenuItem allItem) {
        return size;
    }


    //возвращающий число заказанных блюд (принимает название блюда в качестве параметра)
    public int dishQuantity(String DishName) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Dishes[i] != null) {
                if (Dishes[i].getName().equals(DishName))
                    count++;
            }
        }
        return count;
    }
    public int dishQuantity(MenuItem menuItem) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (Dishes[i] != null) {
                if (Dishes[i].getName().equals(menuItem))
                    count++;
            }
        }
        return count;
    }

    //возвращающий массив блюд (значений null в массиве быть не должно)
    public MenuItem[] getDishes() {

        MenuItem[] getDishes = new MenuItem[size];
        System.arraycopy(Dishes, 0, getDishes, 0, size);
        return getDishes;
    }


    //возвращающий общую стоимость заказа.
    public double costTotal() {
        double cost = 0.0;
        for (int i = 0; i < size; i++) {
            if (Dishes[i] != null)
                cost += Dishes[i].getPrice();
        }
        return cost;
    }


    //возвращающий массив названий заказанных блюд (без повторов)
    public String[] dishesNames() {
        int names=0;
        int newSize=size;
        MenuItem[] newDish = new MenuItem[newSize];
        System.arraycopy(Dishes, 0, newDish, 0, size);
        for (int i = 0; i <size; i++) {
            for (int j = i + 1; j <size; j++) {
                if (newDish[i].getName().equals(newDish[j].getName())) {
                    newDish[j]=null;
                    names++;
                    // System.arraycopy(newDish, j + 1, newDish, j, size-j-1);
                }
            }
        }
        newSize-=names;
        String[] nameDish = new String[size];
        for(int i=0, j = 0;i<newSize;i++)
        {
            if (nameDish!=null) {
                nameDish[j] = newDish[i].getName();
                j++;
            }
        }
        return nameDish;
    }

    //возвращающий массив блюд, отсортированный по убыванию цены
    public MenuItem[] sortedDishesByCostDesc() {
        MenuItem[] DishesCost = new MenuItem[Dishes.length];
        System.arraycopy(getDishes(), 0, DishesCost, 0, size);
        for (int i = DishesCost.length - 1; i < Dishes.length; i++) {
            if (DishesCost[i] != null)
                for (int j = 0; j < Dishes.length; j++) {
                    if (DishesCost[j].getPrice() < DishesCost[j + 1].getPrice()) {
                        MenuItem t = DishesCost[j];
                        DishesCost[j] = DishesCost[j + 1];
                        DishesCost[j + 1] = t;
                    }
                }
        }
        return DishesCost;
    }

    @Override
    public String toString(){
        StringBuilder tableOrder = new StringBuilder("TableOrder: ");
        tableOrder.append(size+'\n');
        for(MenuItem o : Dishes) {
            tableOrder.append(o.toString()).append('\n');
        }
        return tableOrder.toString();
    }
    /*“TableOrder: <size>
    <строковое представление item 1>
    <строковое представление item 2>
            …
    <строковое представление item N>”*/

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj==null && !this.getClass().equals(obj.getClass())) return false;

        TableOrder equalsCheck = (TableOrder)obj;
        MenuItem[] allItems = this.getDishes();
        for(int i =0; i<size; i++){
            if (this.dishQuantity0(allItems[i]) != equalsCheck.dishQuantity0(allItems[i]))
                return false;
        }
        return (this.customer.equals(equalsCheck.customer)
                && this.size==equalsCheck.size);
    }

    public int hashCode(){
        int hash = 0;

        for (MenuItem i: Dishes) {
            hash^=i.hashCode();
        }
        return size^customer.hashCode()^hash;
    }
    @Override
    public boolean remove(MenuItem menuItem) {
        return false;
    }

    @Override
    public int removeAll(MenuItem menuItem) {
        return 0;
    }
}