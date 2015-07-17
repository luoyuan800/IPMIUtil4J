package param;

/**
 * Cipher suite is use for Lan plus, and current this IPMIUtil4J just support the lan plus.
 * Each cipher suite have it own combination, it was define in the IPMI2.0
 */
public enum CipherSuite {
	cs0(0,"NONE", "NONE", "NONE"),
    cs1(1,"SHA1", "NONE", "NONE"),
    cs2(2,"SHA1", "SHA1_96", "NONE"),
    cs3(3,"SHA1", "SHA1_96", "AES_CBC128"),
    cs4(4,"SHA1", "SHA1_96", "XRC4_128"),
    cs5(5,"SHA1", "SHA1_96", "XRC4_40"),
    cs6(6,"MD5", "NONE", "NONE"),
    cs7(7,"MD5", "MD5_128", "NONE"),
    cs8(8,"MD5", "MD5_128", "AES_CBC128"),
    cs9(9,"MD5", "MD5_128", "XRC4_128"),
    cs10(10,"MD5", "MD5_128", "XRC4_40"),
    cs11(11,"MD5", "MD5_128", "NONE"),
    cs12(12,"MD5", "MD5_128", "AES_CBC128"),
    cs13(13,"MD5", "MD5_128", "XRC4_128"),
    cs14(14,"MD5", "MD5_128", "XRC4_40");
	private String aa, ia,ca;
	private int id;
    private CipherSuite(int id,  String aa, String ia, String ca) {
        this.aa = aa;
        this.ia = ia;
        this.ca = ca;
        this.id = id;
    }
    public String getAuthenticationAlgorithm(){
    	return aa;
    }
    public String getIntegrityAlgorithm(){
    	return ia;
    }
    public String getConfidentialityAlgorithm(){
    	return ca;
    }
    public int getId(){
    	return id;
    }
}
