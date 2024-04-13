package onlineshop.utils;

public class PasswordContainsLowercaseUppercaseSpecialCharacterDigit {
	
	
		public static boolean passwordContainsLowercaseUppercaseSpecialCharacterDigit(String input) {
				
				char currentCharacter;
			    
			    boolean containsAll = false;
				
				String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
			    boolean numberPresent = false;
			    boolean upperCasePresent = false;
			    boolean lowerCasePresent = false;
			    boolean specialCharacterPresent = false;
		
			    for (int i = 0; i < input.length(); i++) {
			    	
			        currentCharacter = input.charAt(i);
			        
			        if (Character.isDigit(currentCharacter)) {
			            numberPresent = true;
			        } 
			        else if (Character.isUpperCase(currentCharacter)) {
			            upperCasePresent = true;
			        } 
			        else if (Character.isLowerCase(currentCharacter)) {
			            lowerCasePresent = true;
			        } 
			        else if (specialChars.contains(String.valueOf(currentCharacter))) {
			            specialCharacterPresent = true;
			        }
			    }
			    
			    if( numberPresent==true && upperCasePresent==true && lowerCasePresent==true && specialCharacterPresent==true ) {
			    	containsAll = true;
			    }
			    return containsAll;
			    
			}
		

}
