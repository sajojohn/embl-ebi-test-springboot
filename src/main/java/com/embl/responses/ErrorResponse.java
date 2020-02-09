package com.embl.responses;

import java.util.List;
@SuppressWarnings("unused")
public class ErrorResponse {
    
	private String message;
 
    private List<String> details;
    
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }
 
}
