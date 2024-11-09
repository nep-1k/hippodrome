import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {


    @Test
    void constructor_WhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));
    }

    @Test
    void constructorMessage_WhenNameIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.5));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "\t",
            "\n",
            "\r",
            "\f"
    })
    void constructor_WhenNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.5));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "\t",
            "\n",
            "\r",
            "\f"
    })
    void constructorMessage_WhenNameIsBlank(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.5));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    
    @Test
    void constructor_WhenSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", -2.5));
    }

    @Test
    void constructorMessage_WhenSpeedIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", -2.5));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructor_WhenDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", 2.5, -5));
    }

    @Test
    void constructorMessage_WhenDistanceIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Spirit", 2.5, -5));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName_ExpectingString() {
        Horse horse = new Horse("Spirit", 2.5);
        String expectedString = "Spirit";
        assertEquals(expectedString, horse.getName());
    }

    @Test
    void getSpeed_ExpectingNumber() {
        Horse horse = new Horse("Spirit", 2.5);
        double expectedNumber = 2.5;
        assertEquals(expectedNumber, horse.getSpeed());
    }

    @Test
    void getDistance_ExpectingNumber() {
        Horse horse = new Horse("Spirit", 2.5, 5);
        double expectedNumber = 5.0;
        assertEquals(expectedNumber, horse.getDistance());
    }

    @Test
    void getDistance_ExpectingZero() {
        Horse horse = new Horse("Spirit", 2.5);
        double expectedNumber = 0.0;
        assertEquals(expectedNumber, horse.getDistance());
    }

    @Test
    void getRandomDouble_ShouldVerifyMethodCall() {
        try(MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Spirit", 2.5);
            horse.move();
            mock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void getRandomDouble_ExpectingCorrectFormula() {

        try(MockedStatic<Horse> mock = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Spirit", 2.5, 5);

            mock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            double expectedDistance = horse.getDistance() + horse.getSpeed() * 0.5;

            horse.move();

            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}