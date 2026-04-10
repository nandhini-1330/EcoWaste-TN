package com.ecowaste.ecowaste_tn.controller;

import com.ecowaste.ecowaste_tn.model.WasteRequest;
import com.ecowaste.ecowaste_tn.repository.WasteRequestRepository;
import com.ecowaste.ecowaste_tn.service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WasteController {

    @Autowired
    private WasteRequestRepository requestRepo;

    @Autowired
    private WasteService wasteService;

    @PostMapping("/classify")
    public Map<String, String> classify(@RequestBody Map<String, String> body) {
        try {
            return wasteService.classifyWaste(body.get("item"));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return error;
        }
    }

    @PostMapping("/classify-image")
    public Map<String, String> classifyImage(@RequestBody Map<String, String> body) {
        try {
            String base64 = body.get("image");
            String mediaType = body.get("mediaType");
            if (mediaType == null) mediaType = "image/jpeg";
            return wasteService.classifyImage(base64, mediaType);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return error;
        }
    }

    @PostMapping("/request")
    public WasteRequest submitRequest(@RequestBody WasteRequest req) {
        return requestRepo.save(req);
    }

    @GetMapping("/admin/requests")
    public List<WasteRequest> getAllRequests() {
        return requestRepo.findAll();
    }

    @PutMapping("/admin/requests/{id}/status")
    public WasteRequest updateStatus(@PathVariable Long id,
                                      @RequestBody Map<String, String> body) {
        WasteRequest req = requestRepo.findById(id).orElseThrow();
        req.setStatus(body.get("status"));
        return requestRepo.save(req);
    
    }
    @PostMapping("/chat")
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        Map<String, String> result = new HashMap<>();
        try {
            String question = body.get("message");
            if (question == null || question.trim().isEmpty()) {
                result.put("reply", "Please ask a question about waste disposal.");
                return result;
            }
            String reply = wasteService.answerChat(question);
            result.put("reply", reply);
            return result;
        } catch (Exception e) {
            result.put("reply", "I can help with waste disposal in Hosur! Ask me about any waste type.");
            return result;
        }
    }
    @GetMapping("/admin/map-data")
public List<Map<String, Object>> getMapData() {
    List<WasteRequest> allRequests = requestRepo.findAll();
    
    // Hosur zones with coordinates
    Map<String, double[]> zoneCoords = new HashMap<>();
    zoneCoords.put("SIPCOT", new double[]{12.7409, 77.8253});
    zoneCoords.put("Gandhi Nagar", new double[]{12.7350, 77.8190});
    zoneCoords.put("Mathigiri", new double[]{12.7480, 77.8310});
    zoneCoords.put("Hosur Bus Stand", new double[]{12.7368, 77.8256});
    zoneCoords.put("Industrial Area", new double[]{12.7430, 77.8350});
    zoneCoords.put("Nehru Nagar", new double[]{12.7320, 77.8220});
    zoneCoords.put("Anna Nagar", new double[]{12.7390, 77.8180});
    zoneCoords.put("Denkanikotta Road", new double[]{12.7290, 77.8290});

    // Count pending requests per zone
    Map<String, Integer> pendingCount = new HashMap<>();
    Map<String, Integer> totalCount = new HashMap<>();

    for (WasteRequest req : allRequests) {
        String address = req.getAddress() != null ? req.getAddress().toLowerCase() : "";
        String matchedZone = "Gandhi Nagar"; // default zone

        if (address.contains("sipcot")) matchedZone = "SIPCOT";
        else if (address.contains("mathigiri")) matchedZone = "Mathigiri";
        else if (address.contains("bus stand")) matchedZone = "Hosur Bus Stand";
        else if (address.contains("industrial")) matchedZone = "Industrial Area";
        else if (address.contains("nehru")) matchedZone = "Nehru Nagar";
        else if (address.contains("anna")) matchedZone = "Anna Nagar";
        else if (address.contains("denkanikotta")) matchedZone = "Denkanikotta Road";

        totalCount.merge(matchedZone, 1, Integer::sum);
        if ("Pending".equals(req.getStatus())) {
            pendingCount.merge(matchedZone, 1, Integer::sum);
        }
    }

    // Build response
    List<Map<String, Object>> result = new ArrayList<>();
    for (Map.Entry<String, double[]> entry : zoneCoords.entrySet()) {
        String zone = entry.getKey();
        double[] coords = entry.getValue();
        int pending = pendingCount.getOrDefault(zone, 0);
        int total = totalCount.getOrDefault(zone, 0);

        Map<String, Object> zoneData = new HashMap<>();
        zoneData.put("zone", zone);
        zoneData.put("lat", coords[0]);
        zoneData.put("lng", coords[1]);
        zoneData.put("pending", pending);
        zoneData.put("total", total);
        zoneData.put("collected", total - pending);
        zoneData.put("risk", pending >= 3 ? "High" : pending >= 1 ? "Medium" : "Low");
        result.add(zoneData);
    }
    return result;
}
@GetMapping("/co2-savings")
public Map<String, Object> getCO2Savings(
        @RequestParam String category,
        @RequestParam(defaultValue = "1") int quantity) {
    return wasteService.getCO2Savings(category, quantity);
}
    
}
