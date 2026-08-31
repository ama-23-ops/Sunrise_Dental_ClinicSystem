package com.sunrisedental;
import com.sunrisedental.util.Validator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test void acceptsSriLankanPhone(){assertTrue(Validator.validPhone("0712345678"));}
    @Test void rejectsInvalidPhone(){assertFalse(Validator.validPhone("123ABC"));}
}
