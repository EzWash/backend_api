package com.ezwash.backend.domain.service.geographic;

import com.ezwash.backend.domain.model.geographic.Location;

public interface LocationService {
    Location getLocationById(Long Id);
    Location createLocation(Location location);
    Location getLocationByUser(Long userId);
}
