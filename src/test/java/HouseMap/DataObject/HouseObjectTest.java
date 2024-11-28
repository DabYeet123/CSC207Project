package HouseMap.DataObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HouseObjectTest{

    private HouseObject house;

    @BeforeEach
    void init() {
        house = new HouseObject("Great House", "182 Ave", 10, 20, 312000, "Bob");
    }

    @Test
    void getName() {
        Assertions.assertEquals("Great House", house.getName());
    }

    @Test
    void getX() {
        Assertions.assertEquals(10, house.getX());
    }

    @Test
    void getY() {
        Assertions.assertEquals(20, house.getY());
    }

    @Test
    void getPrice() {
        Assertions.assertEquals(312000, house.getPrice());
    }

    @Test
    void getOwner() {
        Assertions.assertEquals("Bob", house.getOwner());
    }


}