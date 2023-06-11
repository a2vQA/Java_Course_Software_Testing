package ru.stqa.javaCourse.soap;

import com.lavasoft.*;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class GeoIPServiceTests {

    @Test
    public void testMyIP() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("188.32.219.116");
        assertTrue(geoIP.contains("RU"));
    }
}
