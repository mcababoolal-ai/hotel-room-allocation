package com.example.hotel.room_allocation.domain;

import java.math.BigDecimal;

public record RoomAllocationResult(
        Integer usagePremium,
        BigDecimal revenuePremium,
        Integer usageEconomy,
        BigDecimal revenueEconomy
) {}
