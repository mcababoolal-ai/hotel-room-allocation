package com.example.hotel.room_allocation;

import com.example.hotel.room_allocation.domain.RoomAllocationRequest;
import com.example.hotel.room_allocation.domain.RoomAllocationResult;
import com.example.hotel.room_allocation.service.RoomAllocationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoomAllocationServiceTest {

    private final RoomAllocationService service =
            new RoomAllocationService();

    private static final List<BigDecimal> GUEST_OFFERS = List.of(
            new BigDecimal("23"),
            new BigDecimal("45"),
            new BigDecimal("155"),
            new BigDecimal("374"),
            new BigDecimal("22"),
            new BigDecimal("99.99"),
            new BigDecimal("100"),
            new BigDecimal("101"),
            new BigDecimal("115"),
            new BigDecimal("209")
    );

    @Test
    void shouldAllocateRoomsCorrectly() {

        RoomAllocationResult result =
                service.allocateRooms(
                        new RoomAllocationRequest(7, 5, GUEST_OFFERS)
                );

        assertEquals(6, result.usagePremium());
        assertEquals(new BigDecimal("1054"), result.revenuePremium());
        assertEquals(4, result.usageEconomy());
        assertEquals(new BigDecimal("189.99"), result.revenueEconomy());
    }
}
