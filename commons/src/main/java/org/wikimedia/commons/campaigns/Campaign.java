package org.wikimedia.commons.campaigns;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Campaign {
    private boolean enabled;

    private String autoAddWikitext;
    private ArrayList<String> autoAddCategories;

    private String name;
    private String ownWorkLicenseDefault;

    private String defaultDescription;

    private JSONObject config;

    public boolean isEnabled() {
        return enabled;
    }

    public String getAutoAddWikitext() {
        return autoAddWikitext;
    }

    public ArrayList<String> getAutoAddCategories() {
        return autoAddCategories;
    }

    public String getName() {
        return name;
    }

    public String getOwnWorkLicenseDefault() {
        return ownWorkLicenseDefault;
    }

    public String getDefaultDescription() {
        return defaultDescription;
    }

    public JSONObject getConfig() {
        return config;
    }

    public Campaign(JSONObject config) {
        this.config = config;
        if(config.has("autoAdd")) {
            this.autoAddWikitext = config.optJSONObject("config").optString("wikitext", null);
            if(config.optJSONObject("config").has("categories")) {
                this.autoAddCategories = new ArrayList<String>();
                JSONArray catsArray = config.optJSONObject("config").optJSONArray("categories");
                for(int i=0; i < catsArray.length(); i++) {
                    autoAddCategories.add(catsArray.optString(i));
                }
            }
        }
    }
}
