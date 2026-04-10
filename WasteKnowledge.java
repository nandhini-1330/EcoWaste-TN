package com.ecowaste.ecowaste_tn.service;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class WasteKnowledge {

    public String classifyItem(String item) {
        String i = item.toLowerCase();

        if (i.contains("phone") || i.contains("mobile") || i.contains("laptop") ||
            i.contains("computer") || i.contains("battery") || i.contains("charger") ||
            i.contains("tablet") || i.contains("keyboard") || i.contains("mouse") ||
            i.contains("printer") || i.contains("television") || i.contains("tv") ||
            i.contains("monitor") || i.contains("cable") || i.contains("wire") ||
            i.contains("headphone") || i.contains("speaker") || i.contains("camera") ||
            i.contains("calculator") || i.contains("remote") || i.contains("bulb") ||
            i.contains("tubelight") || i.contains("led") || i.contains("circuit") ||
            i.contains("hard disk") || i.contains("pen drive") || i.contains("usb") ||
            i.contains("router") || i.contains("modem") || i.contains("chip"))
            return "E-Waste";

        if (i.contains("paint") || i.contains("chemical") || i.contains("medicine") ||
            i.contains("pesticide") || i.contains("fertilizer") || i.contains("bleach") ||
            i.contains("acid") || i.contains("solvent") || i.contains("motor oil") ||
            i.contains("engine oil") || i.contains("thinner") || i.contains("varnish") ||
            i.contains("insecticide") || i.contains("rat poison") || i.contains("phenyl") ||
            i.contains("expired") || i.contains("injection") || i.contains("syringe") ||
            i.contains("thermometer") || i.contains("mercury") || i.contains("asbestos") ||
            i.contains("mosquito coil") || i.contains("floor cleaner"))
            return "Hazardous";

        if (i.contains("vegetable") || i.contains("fruit") || i.contains("food") ||
            i.contains("peel") || i.contains("rice") || i.contains("bread") ||
            i.contains("meat") || i.contains("fish") || i.contains("egg") ||
            i.contains("milk") || i.contains("leaf") || i.contains("leaves") ||
            i.contains("flower") || i.contains("grass") || i.contains("garden") ||
            i.contains("kitchen") || i.contains("leftover") || i.contains("rotten") ||
            i.contains("spoiled") || i.contains("tea") || i.contains("coffee") ||
            i.contains("coconut") || i.contains("banana") || i.contains("mango") ||
            i.contains("onion") || i.contains("tomato") || i.contains("compost"))
            return "Wet";

        return "Dry";
    }

    public String getTip(String category) {
        switch (category) {
            case "E-Waste":
                return "Remove batteries before disposing. Never mix e-waste with regular bins. Wipe data from devices before disposal.";
            case "Hazardous":
                return "Never pour down drains or mix with regular waste. Store safely in original containers until collection. Keep away from children.";
            case "Wet":
                return "Can be composted at home to make manure. Keep separate from dry waste. Use a covered bin to avoid smell.";
            default:
                return "Clean before disposing. Remove food residue. Flatten boxes to save space. Many dry waste items can be recycled.";
        }
    }

    public Map<String, String> getLocation(String category) {
        Map<String, String> loc = new HashMap<>();
        switch (category) {
            case "E-Waste":
                loc.put("location", "SIPCOT E-Waste Drop Centre");
                loc.put("address",  "SIPCOT Main Road, Hosur");
                loc.put("timings",  "Mon-Fri 9AM-5PM");
                loc.put("contact",  "04344-345678");
                break;
            case "Hazardous":
                loc.put("location", "Hosur Hazardous Waste Facility");
                loc.put("address",  "Industrial Area, Hosur");
                loc.put("timings",  "Mon-Fri 9AM-4PM");
                loc.put("contact",  "04344-456789");
                break;
            case "Wet":
                loc.put("location", "Hosur Municipality Compost Yard");
                loc.put("address",  "Near Bus Stand, Hosur");
                loc.put("timings",  "Mon-Sat 7AM-6PM");
                loc.put("contact",  "04344-123456");
                break;
            default:
                loc.put("location", "Hosur Dry Waste Collection Centre");
                loc.put("address",  "SIPCOT Phase 1, Hosur");
                loc.put("timings",  "Mon-Sat 8AM-5PM");
                loc.put("contact",  "04344-234567");
        }
        return loc;
    }

    public String answerQuestion(String question) {
        String q = question.toLowerCase();

        if (q.contains("battery") || q.contains("batteries"))
            return "Batteries are E-Waste and highly toxic. Never throw in regular bins. Remove from devices before disposal. Drop at SIPCOT E-Waste Centre, SIPCOT Main Road, Hosur. Timings: Mon-Fri 9AM-5PM. Contact: 04344-345678.";

        if (q.contains("phone") || q.contains("mobile"))
            return "Old phones are E-Waste. Wipe your data first, then remove the battery. Drop at SIPCOT E-Waste Centre, SIPCOT Main Road, Hosur. Timings: Mon-Fri 9AM-5PM. Contact: 04344-345678.";

        if (q.contains("laptop") || q.contains("computer"))
            return "Laptops and computers are E-Waste. Wipe your hard drive data first. Drop at SIPCOT E-Waste Centre, SIPCOT Main Road, Hosur. They contain valuable metals that can be recovered through proper recycling.";

        if (q.contains("bulb") || q.contains("tubelight") || q.contains("led"))
            return "Old bulbs and tubelights are E-Waste. CFL bulbs contain mercury — never break them. Drop at SIPCOT E-Waste Centre, SIPCOT Main Road, Hosur. Timings: Mon-Fri 9AM-5PM.";

        if (q.contains("ewaste") || q.contains("e-waste") || q.contains("electronic"))
            return "E-Waste includes phones, laptops, batteries, chargers, TVs, and bulbs. Drop at SIPCOT E-Waste Centre, SIPCOT Main Road, Hosur. Timings: Mon-Fri 9AM-5PM. Contact: 04344-345678. Never mix with regular waste.";

        if (q.contains("compost") || q.contains("composting"))
            return "Home composting is easy! Collect vegetable peels, fruit waste, tea leaves and garden waste in a bin. Add dry leaves in layers. Water lightly. Turn every 3 days. In 6-8 weeks you get rich compost for your garden!";

        if (q.contains("vegetable") || q.contains("food waste") || q.contains("kitchen waste"))
            return "Kitchen and vegetable waste is Wet Waste. Best option is home composting. Otherwise drop at Hosur Municipality Compost Yard near Bus Stand. Timings: Mon-Sat 7AM-6PM. Contact: 04344-123456.";

        if (q.contains("wet waste") || q.contains("wet"))
            return "Wet waste includes food scraps, vegetable peels, fruit waste, garden waste, tea/coffee grounds. Drop at Hosur Municipality Compost Yard near Bus Stand. Timings: Mon-Sat 7AM-6PM. Contact: 04344-123456.";

        if (q.contains("plastic"))
            return "Plastic is Dry Waste. Clean it before disposal — remove food residue. Drop at Hosur Dry Waste Collection Centre, SIPCOT Phase 1. Timings: Mon-Sat 8AM-5PM. Contact: 04344-234567.";

        if (q.contains("paper") || q.contains("cardboard") || q.contains("newspaper"))
            return "Paper and cardboard are Dry Waste and fully recyclable. Keep dry — wet paper cannot be recycled. Drop at Hosur Dry Waste Collection Centre, SIPCOT Phase 1. Contact: 04344-234567.";

        if (q.contains("glass"))
            return "Glass is Dry Waste and fully recyclable. Wrap broken glass in newspaper before disposal to avoid injury. Drop at Hosur Dry Waste Collection Centre, SIPCOT Phase 1. Contact: 04344-234567.";

        if (q.contains("dry waste") || q.contains("dry"))
            return "Dry waste includes paper, cardboard, plastic, glass, metal cans and packaging. Drop at Hosur Dry Waste Collection Centre, SIPCOT Phase 1. Timings: Mon-Sat 8AM-5PM. Contact: 04344-234567.";

        if (q.contains("medicine") || q.contains("tablet") || q.contains("capsule"))
            return "Expired medicines are Hazardous Waste. Never flush down toilet or throw in regular bins. Drop at Hosur Hazardous Waste Facility, Industrial Area. Timings: Mon-Fri 9AM-4PM. Contact: 04344-456789.";

        if (q.contains("paint"))
            return "Paint is Hazardous Waste. Never pour down drains. Let leftover paint dry before disposal. Drop at Hosur Hazardous Waste Facility, Industrial Area. Timings: Mon-Fri 9AM-4PM. Contact: 04344-456789.";

        if (q.contains("chemical") || q.contains("pesticide") || q.contains("insecticide"))
            return "Chemicals and pesticides are Hazardous Waste. Store in original containers, never mix chemicals. Drop at Hosur Hazardous Waste Facility, Industrial Area. Timings: Mon-Fri 9AM-4PM. Contact: 04344-456789.";

        if (q.contains("hazardous") || q.contains("dangerous"))
            return "Hazardous waste includes paints, chemicals, medicines, motor oil and pesticides. Drop at Hosur Hazardous Waste Facility, Industrial Area. Timings: Mon-Fri 9AM-4PM. Contact: 04344-456789. Never mix with regular waste.";

        if (q.contains("where") || q.contains("location") || q.contains("centre") || q.contains("center"))
            return "Hosur disposal centres: E-Waste → SIPCOT E-Waste Centre, SIPCOT Main Road (04344-345678). Dry Waste → Collection Centre, SIPCOT Phase 1 (04344-234567). Wet Waste → Compost Yard, Near Bus Stand (04344-123456). Hazardous → Waste Facility, Industrial Area (04344-456789).";

        if (q.contains("recycle") || q.contains("recycling"))
            return "In Hosur you can recycle paper, cardboard, plastic bottles, glass and e-waste metals. Drop dry items at SIPCOT Phase 1 Collection Centre. Drop e-waste at SIPCOT E-Waste Centre. Proper segregation at source is the key first step!";

        if (q.contains("collect") || q.contains("pickup") || q.contains("pick up"))
            return "Request home collection using this app! Click Check Waste, classify your waste, then click Request Home Collection. Fill your name and address. Municipality staff will contact you within 24 hours for pickup.";

        if (q.contains("tamil nadu") || q.contains("government") || q.contains("law"))
            return "Tamil Nadu has banned single-use plastics since 2019. The Tamil Nadu Pollution Control Board (TNPCB) regulates hazardous and e-waste disposal. For policy queries contact TNPCB Hosur office or Hosur Municipality at 04344-123456.";

        if (q.contains("hello") || q.contains("hi") || q.contains("help") || q.contains("what can you do"))
            return "Hello! I am EcoWaste TN Assistant. Ask me about waste segregation rules, disposal locations in Hosur, composting tips, recycling guidance, or how to request a collection. I am here to help!";

        if (q.contains("thank"))
            return "You are welcome! Proper waste disposal keeps Hosur clean and healthy. Every small step counts towards a sustainable Tamil Nadu!";

        if (q.contains("segregat") || q.contains("separate") || q.contains("how to"))
            return "Segregate waste into 4 bins: GREEN for wet waste (food/vegetable), BLUE for dry waste (paper/plastic/glass), RED for hazardous (chemicals/medicines), YELLOW for e-waste (phones/batteries). Keep bins covered and dispose weekly.";

        if (q.contains("bin") || q.contains("dustbin") || q.contains("container"))
            return "Use colour coded bins: Green bin for Wet waste, Blue bin for Dry waste, Red bin for Hazardous waste, Yellow bin for E-Waste. Keep bins covered to avoid smell and pests. Clean bins weekly with water.";

        if (q.contains("smell") || q.contains("odour") || q.contains("odor"))
            return "To reduce waste smell: Keep wet waste in a covered green bin. Add dry leaves or newspaper over wet waste daily. Empty wet waste bin every 2 days. Clean bins with water and baking soda weekly. Never mix wet and dry waste.";

        if (q.contains("hosur"))
            return "Hosur is an industrial city in Krishnagiri district, Tamil Nadu. EcoWaste TN specifically serves Hosur citizens with 4 disposal centres: SIPCOT E-Waste Centre, SIPCOT Dry Waste Centre, Municipality Compost Yard near Bus Stand, and Hazardous Waste Facility in Industrial Area.";

        return "Good question! For waste disposal in Hosur — Wet waste goes to Compost Yard near Bus Stand. Dry waste goes to SIPCOT Phase 1. E-Waste goes to SIPCOT Main Road. Hazardous waste goes to Industrial Area. Use the Classify feature above for instant guidance on any specific item!";
    }
    public Map<String, Object> getCO2Savings(String category, int quantity) {
    Map<String, Object> data = new HashMap<>();
    double co2PerKg = 0;
    String impact = "";
    String equivalent = "";
    String tip = "";

    switch (category) {
        case "E-Waste":
            co2PerKg = 20.0;
            impact = "E-waste recycling prevents toxic metals like lead and mercury from entering soil and saves massive energy used in mining new materials.";
            equivalent = "Equivalent to planting " + (int)(quantity * 0.8) + " trees!";
            tip = "One recycled mobile phone saves enough energy to charge a laptop for 44 hours.";
            break;
        case "Wet":
            co2PerKg = 0.5;
            impact = "Composting wet waste prevents methane emission from landfills. Methane is 25x more potent than CO2 as a greenhouse gas.";
            equivalent = "Equivalent to not driving " + (int)(quantity * 2.5) + " km by car!";
            tip = "1 kg of composted wet waste produces 300g of rich fertilizer for your garden.";
            break;
        case "Hazardous":
            co2PerKg = 5.0;
            impact = "Proper hazardous waste disposal prevents soil and groundwater contamination that would affect thousands of Hosur residents.";
            equivalent = "Prevents contamination of " + (int)(quantity * 500) + " litres of groundwater!";
            tip = "One disposed paint can prevents 10 sq metres of soil from becoming toxic for 50 years.";
            break;
        default: // Dry
            co2PerKg = 2.5;
            impact = "Recycling dry waste saves energy, water, and raw materials. Paper recycling saves 17 trees per tonne.";
            equivalent = "Equivalent to saving " + (int)(quantity * 1.5) + " litres of water!";
            tip = "Recycling 1 plastic bottle saves enough energy to power a 60W bulb for 6 hours.";
            break;
    }

    double totalCO2 = co2PerKg * quantity;
    data.put("category", category);
    data.put("quantity", quantity);
    data.put("co2PerKg", co2PerKg);
    data.put("totalCO2Saved", Math.round(totalCO2 * 100.0) / 100.0);
    data.put("impact", impact);
    data.put("equivalent", equivalent);
    data.put("tip", tip);
    return data;
}
}