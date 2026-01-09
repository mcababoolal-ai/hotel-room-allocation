package com.example.hotel.room_allocation.service;

import com.example.hotel.room_allocation.domain.RoomAllocationRequest;
import com.example.hotel.room_allocation.domain.RoomAllocationResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RoomAllocationService {

    private static final BigDecimal PREMIUM_THRESHOLD =
            BigDecimal.valueOf(100);

    public RoomAllocationResult allocateRooms(
            RoomAllocationRequest request
    ) {

        List<BigDecimal> premiumGuestOffers =
                request.potentialGuests().stream()
                        .filter(amount -> amount.compareTo(PREMIUM_THRESHOLD) >= 0)
                        .sorted(Comparator.reverseOrder())
                        .toList();

        List<BigDecimal> economyGuestOffers =
                request.potentialGuests().stream()
                        .filter(amount -> amount.compareTo(PREMIUM_THRESHOLD) < 0)
                        .sorted(Comparator.reverseOrder())
                        .toList();

        int remainingPremiumRooms = (request.premiumRooms() == null) ? 0 : request.premiumRooms();
        int remainingEconomyRooms = (request.economyRooms() == null) ? 0 : request.economyRooms();

        List<BigDecimal> allocatedPremiumPayments = new ArrayList<>();
        List<BigDecimal> allocatedEconomyPayments = new ArrayList<>();

        // Allocate premium guests to premium rooms
        for (BigDecimal offer : premiumGuestOffers) {
            if (remainingPremiumRooms-- > 0) {
                allocatedPremiumPayments.add(offer);
            }
        }

        // Allocate economy guests to economy rooms
        for (BigDecimal offer : economyGuestOffers) {
            if (remainingEconomyRooms-- > 0) {
                allocatedEconomyPayments.add(offer);
            }
        }

        // Smart upgrade: highest-paying economy guests
        Iterator<BigDecimal> upgradeEligibleEconomyGuests =
                economyGuestOffers.subList(
                        allocatedEconomyPayments.size(),
                        economyGuestOffers.size()
                ).iterator();

        while (remainingPremiumRooms > 0 && upgradeEligibleEconomyGuests.hasNext()) {
            allocatedPremiumPayments.add(upgradeEligibleEconomyGuests.next());
            remainingPremiumRooms--;
        }

        return new RoomAllocationResult(
                allocatedPremiumPayments.size(),
                sum(allocatedPremiumPayments),
                allocatedEconomyPayments.size(),
                sum(allocatedEconomyPayments)
        );
    }

    private BigDecimal sum(List<BigDecimal> values) {
        return values.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
