package first;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SecantTest {

    private final Secant secant = new Secant();
    private static final double PRECISION = 1e-6;

    @ParameterizedTest(name = "sec(0) = 1")
    @CsvSource({
            "0.0, 1.0",
            "-0.000001, 1.0",
            "0.000001, 1.0",
            "0.3, 1.046752",
            "-0.3, 1.046752",
            "0.5, 1.139493",
            "-0.5, 1.139493",
            "0.8, 1.435324",
            "-0.8, 1.435324"
    })
    @DisplayName("Тестирование табличных значений")
    void testValidSecantValues(double x, double expected) {
        double actual = secant.calculate(x, PRECISION);
        assertEquals(expected, actual, 0.0001, "Значение рассчитано неверно");
    }

    @ParameterizedTest(name = "x = 0 должно вызывать исключение")
    @ValueSource(doubles = {
            1.5708, -1.5708,
            999.0, -999.0,
            Double.NaN,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    @DisplayName("Исключения для граничных значений, NaN и бесконечностей")
    void testOutOfBoundsException(double x) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> secant.calculate(x, PRECISION)
        );
        assertEquals("Значение x вне радиуса сходимости (-pi/2, pi/2)", exception.getMessage());
    }

    @Test
    @DisplayName("Исключение при отрицательной точности")
    void testInvalidPrecisionException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> secant.calculate(0.5, -0.1)
        );
        assertEquals("Точность должна быть положительным числом", exception.getMessage());
    }

}