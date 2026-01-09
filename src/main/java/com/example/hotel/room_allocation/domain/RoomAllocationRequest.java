package com.example.hotel.room_allocation.domain;

import java.math.BigDecimal;
import java.util.List;

public record RoomAllocationRequest(
        Integer premiumRooms,
        Integer economyRooms,
        List<BigDecimal> potentialGuests
) {}
