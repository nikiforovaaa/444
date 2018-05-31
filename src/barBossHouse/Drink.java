package barBossHouse;

import java.util.Formatter;

//todo замечания аналогично Dish
public class Drink extends MenuItem implements Alcoholable {
    double alcoholVol;
    DrinkTypeEnum type;
    public static final double ALCOHOL_VOL_DEFAULT=0.0;

    Drink(String nameOfTheDish, DrinkTypeEnum type){
        this(nameOfTheDish, ALCOHOL_VOL_DEFAULT, type, DISH_DESCRIPTION_DEFAULT, DISH_PRICE_DEFAULT);

    }
    Drink(String nameOfTheDish, double dishPrice, DrinkTypeEnum type, String dishDescription){
        this.nameOfTheDish=nameOfTheDish;
        this.dishPrice=dishPrice;
        this.type=type;
        this.dishDescription=dishDescription;
        this.alcoholVol=ALCOHOL_VOL_DEFAULT;
    }
    Drink(String nameOfTheDish, double dishPrice, DrinkTypeEnum type, String dishDescription, double alcoholVol){
        this.nameOfTheDish=nameOfTheDish;
        this.dishPrice=dishPrice;
        this.type=type;
        this.dishDescription=dishDescription;
        if (alcoholVol<0 & alcoholVol>100) throw new IllegalArgumentException("Alcohol volume should be in the range from 0 to 100");
        this.alcoholVol=alcoholVol;
    }

    public DrinkTypeEnum getType() {
        return type;
    }

    public boolean isAlcoholicDrink() {
        return false; //todo почему false то? Смотри по заданию
    }
    public double getAlcoholVol() {
        return alcoholVol;
    }



    public StringBuilder getString() {
        StringBuilder builder = new StringBuilder();
        Formatter fmt = new Formatter(builder);
        fmt.format("Drink: %s, Alcohol: %d, %s", type, getAlcoholVol(), getDescription());
        return builder;
        /*builder.insert(0, "Drink: ");
        builder.append(type);
        if (isAlcoholicDrink())
            builder.append(" Alcohol: ").append(getAlcoholVol()).append("%. ").append(getDescription());
        return builder;*/
    }

    @Override
    public boolean equals(Object obj){
        //Drink tmp = (Drink) obj;
        Drink equalsCheck = (Drink) obj;
        return super.equals(obj) && (this.alcoholVol == equalsCheck.alcoholVol);
        //  if ((this.nameOfTheDish.equals(tmp.nameOfTheDish) && (this.dishPrice == (tmp.dishPrice)))) return true;
        // else return false;
    }
    @Override
    public int hashCode(){
        return type.hashCode()^nameOfTheDish.hashCode()^Double.hashCode(dishPrice)^Double.hashCode(alcoholVol)^dishDescription.hashCode();
    }


}
