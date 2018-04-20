package org.krishna.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.krishna.demo.model.Metadata;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class DefaultErrorMessage extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest requestAttributes, boolean includeStackTrace) {
    	Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
    	Map<String, Object> response = new HashMap<String, Object>();
        Metadata metadata = new Metadata();
        
        metadata.setStatus((Integer) errorAttributes.get("status"));
        metadata.setError((String) errorAttributes.get("error"));
        metadata.setMessage((String) errorAttributes.get("message"));
        response.put("metadata", metadata);
        
        return response;
    }
}
