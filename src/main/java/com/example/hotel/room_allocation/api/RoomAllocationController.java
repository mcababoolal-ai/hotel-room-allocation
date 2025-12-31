package com.example.hotel.room_allocation.api;

import com.example.hotel.room_allocation.service.RoomAllocationService;
import com.example.hotel.room_allocation.domain.RoomAllocationRequest;
import com.example.hotel.room_allocation.domain.RoomAllocationResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/occupancy")
public class RoomAllocationController {

    private final RoomAllocationService roomAllocationService;

    public RoomAllocationController(
            RoomAllocationService roomAllocationService
    ) {
        this.roomAllocationService = roomAllocationService;
    }

    @PostMapping
    public RoomAllocationResult allocateRooms(
            @RequestBody RoomAllocationRequest request
    ) {
        return roomAllocationService.allocateRooms(request);
    }
}
