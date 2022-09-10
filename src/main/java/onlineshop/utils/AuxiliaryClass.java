package onlineshop.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AuxiliaryClass {
	
	
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	
	// PUTANJA DO FAJLA (u folderu images) ZA SLIKE
	public static String ReturnTheRelativePathToFile(String fileName) {
		String separatorTravel = System.getProperty("file.separator");
		String relativeTravels = "." + separatorTravel + "images" + separatorTravel + fileName;
			return relativeTravels;
		}
	

	// Upisi sadasnji Sql datum i vreme 
	public static java.sql.Timestamp  EntriesPresentDateAndTimeSql() {
		Date date = new Date();  
        Timestamp dateTimeNow=new Timestamp(date.getTime());     
	  return dateTimeNow;
	}
	
	
	// Konvertuj Sql datum i vreme u String
	public static String  ViewsTextualDateTime(Timestamp dateTime) {
		String text = DATE_TIME_FORMAT.format(dateTime);
		// String text = dateTime.toString(); 
		return text;
	}
	
	
	// Konvertuj String u Sql datum i vreme
	public static java.sql.Timestamp  ConvertStringToSqlDateAndTime(String text){
		java.sql.Timestamp dateTime = null;
		try {
			Date date = (Date) DATE_TIME_FORMAT.parse(text);
			dateTime = new Timestamp(date.getTime());
			return dateTime;
		} 
		catch (Exception ex) {
			System.out.println("ERROR");
		}

		return dateTime;
	}
	
	
	// Racunanje broja dana
	public static double TheNumberOfDays(String entrance, String exit) {
		Date Date1 = null;
		Date Date2 = null;
		try {
			Date1 = DATE_TIME_FORMAT.parse(entrance);
			Date2 = DATE_TIME_FORMAT.parse(exit);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		long start = Date1.getTime();
		long end = Date2.getTime();
		long difference = end - start;
		int countDays = (int)(difference/(1000 * 60 * 60 * 24));
		
		return (double)(countDays );
    
  }
	

	// Dodeli sifru
	public static String AssignCode() {
		Random r = new Random();
		int min = 100;
		int max = 999;
		
		char randomCharLetter;
		int randomNumber;
		randomCharLetter = (char) (r.nextInt(26) + 'A');
		randomNumber = (int) ((Math.random() * (max - min)) + min);

		if(randomCharLetter=='\u0000' || randomNumber==0 ) {
			randomCharLetter = (char) (r.nextInt(26) + 'A');
			randomNumber = (int) ((Math.random() * (max - min)) + min);
		}
		
		String code = randomCharLetter + " " + randomNumber;
		return code;
		
	}
	
	
	//  Priprema Stringa za Enum
	public static String PrepareStringForEnum(String text) {
		String uppercaseLetters = text.toUpperCase();
		String removeExcessBlanks = uppercaseLetters.trim();
		String preparedString = removeExcessBlanks.replace(' ', '_');
		return preparedString;	
	}
	

	
}
