package barBossHouse;

import java.util.Formatter;

public class Dish extends MenuItem {

    public Dish(String nameOfTheDish, String dishDescription) {
        this(nameOfTheDish, DISH_PRICE_DEFAULT, dishDescription);
    }

    public Dish(String nameOfTheDish, double dishPrice, String dishDescription) {
        this.nameOfTheDish = nameOfTheDish;
        this.dishPrice = dishPrice;
        this.dishDescription = dishDescription;
    }

    @Override
    public String toString() {
        StringBuilder fullDish = new StringBuilder();
        Formatter fmt = new Formatter(fullDish);
        fmt.format("Dish: %s %f %s", nameOfTheDish,dishPrice,dishDescription);
        return fullDish.toString();
        /*if (nameOfTheDish != null && !nameOfTheDish.isEmpty()) fullDish.append(nameOfTheDish + " ");
        if (dishPrice != DISH_PRICE_DEFAULT) fullDish.append(dishPrice + " p.");
        if (dishDescription != null && !dishDescription.isEmpty()) fullDish.append(dishDescription);
        return fullDish.toString();*/
    }

    @Override
    public boolean equals(Object obj) {
        MenuItem equalsCheck = (MenuItem) obj;
        if ((this.nameOfTheDish.equals(equalsCheck.nameOfTheDish) && (this.dishPrice == (equalsCheck.dishPrice)))) return true;
        else return false;
    }
    @Override
    public int hashCode(){
        return nameOfTheDish.hashCode()^Double.hashCode(dishPrice)^dishDescription.hashCode();
    }
}
