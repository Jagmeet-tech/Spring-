package com.elearn.app.start_learn_back.dtos;

import lombok.Data;
import org.springframework.core.io.Resource;

@Data
public class ResourceContentType {
    public Resource getResource() {
        return resource;
    }

    public ResourceContentType() {
    }

    public ResourceContentType(Resource resource, String contentType) {
        this.resource = resource;
        this.contentType = contentType;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    Resource resource;
    private String contentType;
}
