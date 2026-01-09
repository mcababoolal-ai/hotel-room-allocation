package com.example.hotel.room_allocation.domain;

import java.math.BigDecimal;

public record RoomAllocationResult(
        Integer occupiedPremiumRooms,
        BigDecimal totalPremiumRevenue,
        Integer occupiedEconomyRooms,
        BigDecimal totalEconomyRevenue
) {}
