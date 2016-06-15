package triplemzimindustries.foodbank;

/**
 * Created by Zim on 6/6/2016.
 */
public class Food {
    String id;
    String name;
    String menuType;
    String price;
    int quantity;
    Food(String a,String b,String c,String d){
        id = a;
        name = b;
        menuType = c;
        price = d;
    }
    Food(){}
}
