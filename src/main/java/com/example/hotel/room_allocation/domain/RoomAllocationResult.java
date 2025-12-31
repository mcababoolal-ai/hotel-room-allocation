package com.example.hotel.room_allocation.domain;

import java.math.BigDecimal;

public record RoomAllocationResult(
        int occupiedPremiumRooms,
        BigDecimal totalPremiumRevenue,
        int occupiedEconomyRooms,
        BigDecimal totalEconomyRevenue
) {}
