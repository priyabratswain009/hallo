package com.sunknowledge.dme.rcm.config.appconfiguration;

public class ApplicationConstants {
	private ApplicationConstants() {
	}

	public static final String CLIENT_ID = "e65c1ebc-5bfd-45b7-846f-c96208165bc3";
	public static final String CLIENT_SECRET = "yD5nA3yU4vQ7aB5sN0tG6vR2rH7xE0oY2sR7pS6nA4cX2wN2uJ";
	public static final String GRANT_TYPE = "client_credentials";
	public static final String SCOPE = "hipaa";
	public static final String TOKEN_GENERATION_URL = "https://api.availity.com/v1/token";
	public static final String VERIFY_BENEFITCOVERAGE_URL = "https://api.availity.com/availity/v1/coverages";
	public static final String SERVICE_REVIEW_URL = "https://api.availity.com/availity/v2/service-reviews";
	public static final String VERIFY_BENEFITCOVERAGE_NEW_URL = "https://api.availity.com/availity/development-partner/v1/coverages";
	public static final String SERVICE_REVIEW_NEW_URL = "https://api.availity.com/availity/development-partner/v2/service-reviews";
}
