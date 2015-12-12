import java.util.List;
import java.util.ArrayList;

//represents a business with an inspection record
//contains fields representing the location of the restaurants
//such as address, phone number, etc.
public class Business{
   private String name;
   private String programIdentifier;
   private String address;
   private String city;
   private String zipcode;
   private String phone;
   private double longitude;
   private double latitude;
   
   List<Inspection> inspections;
   
   public Business(String name, String programIdentifier, String address,
                   String city, String zipcode, String phone, 
                   double longitude, double latitude){
      this.name = name;
      this.programIdentifier = programIdentifier;
      this.address = address;
      this.city = city;
      this.zipcode = zipcode;
      this.phone = phone;
      this.longitude = longitude;
      this.latitude = latitude;
      
      inspections = new ArrayList<Inspection>();
   }
}