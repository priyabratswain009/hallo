package com.sunknowledge.dme.rcm.utils;

public final class ApplicationConstants {
	private ApplicationConstants()
	{
	}
	/*===============================NPPES SERVICE=================================*/
	public static final String NPPES_URL = "https://npiregistry.cms.hhs.gov/api/";
	public static final String NPPES_VERSION = "2.1";

	/*===============================USPS SERVICE==================================*/
	public static final String USPS_URL = "https://secure.shippingapis.com/ShippingAPI.dll";
	public static final String USPS_API_NAME = "Verify";
	public static final String USPS_USERID = "405SUNKN1705";
	public static final String USPS_PASSWORD = "684EM04RY256";
	public static final String charset = "UTF-8";


	/*===============================FILE PATH=====================================*/
	public static final String PATH = "";

	/*===============================ICD 10========================================*/
	public static final String ICD_TOKENENDPOINT = "https://icdaccessmanagement.who.int/connect/token";
	public static final String ICD_CLIENTID = "bd38ee97-d14e-4c44-b3d0-971709c30cf2_ae2814b7-1245-4d33-916c-db7fbcb73f12";
	public static final String ICD_CLIENTSECRET = "fwjNRySdD/yWslLBDfyMP/caRFnn19vm08HD0w3lkPE=";
	public static final String ICD_SCOPE = "icdapi_access";
	public static final String ICD_GRANT_TYPE = "client_credentials";
	public static final String ICD_URI = "https://id.who.int/icd/release/10";
	public static final String ICD_API_VERSION = "v2";
	public static final String ICD_ACCEPT_LANGUAGE = "en";
    public static final String ICD_CLINICAL_ICD9CM_DX_URI = "https://clinicaltables.nlm.nih.gov/api/icd9cm_dx/v3/search";
    public static final String ICD_CLINICAL_ICD10CM_URI = "https://clinicaltables.nlm.nih.gov/api/icd10cm/v3/search";
    public static final String ICD_CLINICAL_ICD9CM_SG_URI = "https://clinicaltables.nlm.nih.gov/api/icd9cm_sg/v3/search";
}


