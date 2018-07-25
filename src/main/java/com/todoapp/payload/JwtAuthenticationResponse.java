package com.todoapp.payload;

public class JwtAuthenticationResponse {
	private String accessToken;
    private String tokenType = "Bearer";
    private Long userId;
    private String username;

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public JwtAuthenticationResponse(String accessToken, Long userId) {
    	this.accessToken = accessToken;
    	this.userId = userId;
    }
    
    public JwtAuthenticationResponse(String accessToken, Long userId, String username) {
    	this.accessToken = accessToken;
    	this.userId = userId;
    	this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
