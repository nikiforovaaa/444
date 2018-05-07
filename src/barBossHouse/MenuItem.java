package barBossHouse;

import java.util.Formatter;

abstract public class MenuItem {

    public double dishPrice;
    public String nameOfTheDish;
    public String dishDescription;

    public static final int DISH_PRICE_DEFAULT=0;
    public static final String DISH_DESCRIPTION_DEFAULT="";


    protected MenuItem(String nameOfTheDish, String dishDescription){

        this.nameOfTheDish=nameOfTheDish;
        this.dishDescription=dishDescription;
    }
    protected MenuItem(String nameOfTheDish, String dishDescription, double dishPrice){
        if (dishPrice<0) throw new IllegalArgumentException("Price must be >0");
        this.dishPrice=dishPrice;
        this.nameOfTheDish=nameOfTheDish;
        this.dishDescription=dishDescription;
    }

    protected MenuItem() {
    }

    public double getPrice () {
        return dishPrice;
    }

    public String getName ()
    {
        return nameOfTheDish;
    }

    public String getDescription ()
    {
        return dishDescription;
    }

    @Override
    public String toString(){
        StringBuilder dish = new StringBuilder();
        Formatter fmt = new Formatter(dish);
        fmt.format("%s %f", nameOfTheDish, dishPrice);
        return dish.toString();
      /*if (nameOfTheDish!=null && !nameOfTheDish.isEmpty()) dish.append(nameOfTheDish+" ");
      if(dishPrice!=DISH_PRICE_DEFAULT) dish.append(dishPrice+" p.");
      return dish.toString();*/
    }
    @Override
    public boolean equals(Object obj){
        MenuItem tmp = (MenuItem)obj;
        if((this.nameOfTheDish.equals(tmp.nameOfTheDish) && (this.dishPrice==(tmp.dishPrice)))) return true;
        else return false;

    }
    @Override
    public int hashCode(){
        return nameOfTheDish.hashCode()^Double.hashCode(dishPrice)^dishDescription.hashCode();
    }

}
