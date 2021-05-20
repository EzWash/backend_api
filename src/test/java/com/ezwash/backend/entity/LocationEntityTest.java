package com.ezwash.backend.entity;

import com.ezwash.backend.domain.model.geographic.Location;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
public class LocationEntityTest {
    @TestConfiguration
    static class LocationEntityTestConfiguration{
        @Bean
        public Location location() {
            return new Location();
        }
    }

    @Test
    @DisplayName("when getDistance with Valid Lattitude And Longitude Then Returns Distance in Kilometer")
    public void whenGetDistanceWithValidLattitudeAndLongitudeThenReturnsDistanceInKilometer(){
        // Arrange
        double lattitude = -12.104061;
        double longitude = -76.962902;
        Location location1 = new Location()
                .setAddress("Av. Oscar R. Benavides 2564, Cercado de Lima 15081, Peru")
                .setLattitude(-12.0494482)
                .setLongitude(-77.07557489999999);

        // Act
        double distance = location1.getDistance(lattitude, longitude);

        //Assert
        assertThat(distance).isEqualTo(13.674);
    }
}
