package persistence;

import org.json.JSONObject;

// Codes are based on the WorkRoom Demo provided in CPSC210
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}