import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void constructor_WhenListIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructorMessage_WhenListIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructor_WhenListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
    }

    @Test
    void constructorMessage_WhenListIsEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses_ShouldCheckAllObjects() {
        List<Horse> expectedList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            String name = String.format("Horse %s", i);
            expectedList.add(new Horse(name, 3.0));
        }

        Hippodrome hippodrome = new Hippodrome(expectedList);

        List<Horse> result = hippodrome.getHorses();

        assertEquals(expectedList, result);
    }

    @Test
    void move_ShouldVerifyAllMethods() {
        List<Horse> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(list);

        hippodrome.move();

        for (Horse horse : hippodrome.getHorses()) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    void getWinner_ShouldReturnMaxDistance() {

        List<Horse> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add(new Horse(String.format("Horse %s", i), 2.5, i));
        }

        Hippodrome hippodrome = new Hippodrome(list);

        double expectedNumber = 10.0;
        double result = hippodrome.getWinner().getDistance();

        assertEquals(expectedNumber, result);

    }

}