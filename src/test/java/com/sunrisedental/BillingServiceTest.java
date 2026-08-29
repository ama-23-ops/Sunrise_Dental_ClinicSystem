package com.sunrisedental;
import com.sunrisedental.service.BillingService;
import java.math.BigDecimal;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {
    @Test void calculatesTotalCorrectly(){
        BillingService s=new BillingService();
        assertEquals(new BigDecimal("7000.00"),
                s.calculateTotal(new BigDecimal("2000"),
                        new BigDecimal("5000")));
    }
}
