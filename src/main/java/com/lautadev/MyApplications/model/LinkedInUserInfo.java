package com.lautadev.MyApplications.model;

import java.util.Map;

public class LinkedInUserInfo {
    private Map<String, Object> attributes;

    public LinkedInUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }
}
