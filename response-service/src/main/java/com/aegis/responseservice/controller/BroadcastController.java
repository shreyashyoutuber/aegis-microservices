package com.aegis.responseservice.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * BroadcastController - Server-Sent Events (SSE) Controller for real-time 
 * Cell Broadcast Alerts to connected mobile browsers.
 */
@RestController
@RequestMapping("/api/broadcast")
@CrossOrigin(origins = "*")
public class BroadcastController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * Mobile phones connect to this endpoint to listen for emergency alerts.
     */
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(0L); // Infinite timeout
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError((e) -> emitters.remove(emitter));

        try {
            // Send initial connection heartbeat
            emitter.send(SseEmitter.event()
                    .name("CONNECTED")
                    .data(Map.of("message", "AEGIS Emergency Listener Connected", "timestamp", System.currentTimeMillis())));
        } catch (IOException e) {
            emitters.remove(emitter);
        }

        return emitter;
    }

    /**
     * Command Dashboard triggers emergency cell broadcast to all connected phones.
     */
    @PostMapping("/dispatch")
    public Map<String, Object> dispatchAlert(@RequestBody(required = false) Map<String, String> payload) {
        String title = (payload != null && payload.containsKey("title")) 
                ? payload.get("title") 
                : "Extremely severe alert";

        String message = (payload != null && payload.containsKey("message")) 
                ? payload.get("message") 
                : "India, launches Cell Broadcast using indigenous technology, for instant disaster alerting service for its citizens. Alert citizens, safe nation. No action is required by the public upon receipt of this message. This is a test message. - Government of India.\n\nभारत ने स्वदेशी तकनीक का उपयोग करते हुए सेल ब्रॉडकास्ट सेवा शुरू की है, जिससे नागरिकों को आपदा की तत्काल सूचना मिल सकेगी। सतर्क नागरिक, सुरक्षित राष्ट्र। इस संदेश को प्राप्त करने पर जनता को कोई कार्रवाई करने की आवश्यकता नहीं है। यह एक परीक्षण संदेश है। - भारत सरकार";

        Map<String, Object> alertData = Map.of(
                "title", title,
                "message", message,
                "timestamp", System.currentTimeMillis(),
                "severity", "EXTREME_SEVERE"
        );

        List<SseEmitter> deadEmitters = new ArrayList<>();

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("EMERGENCY_ALERT")
                        .data(alertData));
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        }

        emitters.removeAll(deadEmitters);

        return Map.of(
                "status", "SUCCESS",
                "broadcastCount", emitters.size(),
                "alert", alertData
        );
    }

    /**
     * Get live subscriber count.
     */
    @GetMapping("/subscribers")
    public Map<String, Integer> getSubscribersCount() {
        return Map.of("activeListeners", emitters.size());
    }
}
