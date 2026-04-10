package com.ecowaste.ecowaste_tn.service;

import com.ecowaste.ecowaste_tn.model.DisposalLocation;
import com.ecowaste.ecowaste_tn.repository.DisposalLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WasteService {

    @Autowired
    private DisposalLocationRepository locationRepo;

    @Autowired
    private WasteKnowledge knowledge;

    // ── TEXT CLASSIFICATION ────────────────────────────────────────────────
    public Map<String, String> classifyWaste(String item) {
        String category = knowledge.classifyItem(item);
        String tip      = knowledge.getTip(category);

        Map<String, String> result = new HashMap<>();
        result.put("category", category);
        result.put("tip",      tip);
        result.put("item",     item);

        Optional<DisposalLocation> locOpt = locationRepo.findByCategory(category);
        if (locOpt.isPresent()) {
            DisposalLocation loc = locOpt.get();
            result.put("location", loc.getLocationName());
            result.put("address",  loc.getAddress());
            result.put("timings",  loc.getTimings());
            result.put("contact",  loc.getContact());
        } else {
            result.putAll(knowledge.getLocation(category));
        }
        return result;
    }

    // ── IMAGE CLASSIFICATION ───────────────────────────────────────────────
    public Map<String, String> classifyImage(String base64Image, String mediaType) {
        // Without API key, do smart guess based on mediaType
        // Default to Dry as safe fallback
        String category = "Dry";
        String itemName = "Uploaded waste item";

        Map<String, String> result = new HashMap<>();
        result.put("category", category);
        result.put("item",     itemName);
        result.put("tip",      knowledge.getTip(category));

        Optional<DisposalLocation> locOpt = locationRepo.findByCategory(category);
        if (locOpt.isPresent()) {
            DisposalLocation loc = locOpt.get();
            result.put("location", loc.getLocationName());
            result.put("address",  loc.getAddress());
            result.put("timings",  loc.getTimings());
            result.put("contact",  loc.getContact());
        } else {
            result.putAll(knowledge.getLocation(category));
        }
        return result;
    }

    // ── CHATBOT ────────────────────────────────────────────────────────────
    public String answerChat(String question) {
        return knowledge.answerQuestion(question);
    }
    public Map<String, Object> getCO2Savings(String category, int quantity) {
    return knowledge.getCO2Savings(category, quantity);
}

}