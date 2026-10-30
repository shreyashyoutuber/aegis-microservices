package com.aegis.responseservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;

/**
 * CaptivePortalController - Automatically catches all mobile Wi-Fi connectivity 
 * probe requests (Android generate_204, iOS hotspot-detect.html, Windows ncsi) 
 * and forces the OS to pop up the Cell Broadcast window automatically!
 */
@RestController
public class CaptivePortalController {

    /**
     * Intercept Android, iOS, Windows Captive Portal Probes
     */
    @GetMapping({
        "/generate_204", 
        "/gen_204", 
        "/connecttest.txt",
        "/hotspot-detect.html", 
        "/canonical.html", 
        "/ncsi.txt", 
        "/library/test/success.html",
        "/redirect"
    })
    public ResponseEntity<Void> handleWifiProbe(HttpServletRequest request) {
        String host = request.getServerName();
        HttpHeaders headers = new HttpHeaders();
        // Redirect to alert page
        headers.setLocation(URI.create("/alert.html"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND); // 302 Found forces OS to auto-popup window!
    }
}
