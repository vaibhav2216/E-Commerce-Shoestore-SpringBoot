package com.shoestore.utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class INConstants {

	public final static String IN = "IN";
	
	public final static Map<String, String> mapOfCitites = new HashMap<String, String>() {
		{
			put("MI", "Mumbai");
			put("CSK", "Chennai");
			put("KKR", "Kolkata");
            put("SRH", "Hyderabad");
            put("RR", "Jaipur");
            put("KXIP", "Punjab");
            put("PW", "Pune");
            put("DC", "Delhi");
           
		}
	};
	
	public final static List<String> listOfcitiesCode = new ArrayList<>(mapOfCitites.keySet());
	public final static List<String> listOfcitiesName = new ArrayList<>(mapOfCitites.values());

}