package onlineshop.models;

public enum Category {
	
	AUDIO("AUDIO"),
	VIDEO("VIDEO"),
	PHONES("PHONES"),
	COMPUTERS("COMPUTERS"),
	WHITE_GOODS("WHITE GOODS"),
	KITCHEN_APPLIANCES("KITCHEN APPLIANCES"),
	SMALL_HOME_APPLIANCES("SMALL HOME APPLIANCES"),
	TECHNICS("TECHNICS");
	
	 private String displayName;

	 Category(String displayName) {
	        this.displayName = displayName;
	    }

	    public String displayName() { 
	    	return displayName; 
	    	}

	    
	    @Override 
	    public String toString() { 
	    	return displayName; 
	    	}

}
