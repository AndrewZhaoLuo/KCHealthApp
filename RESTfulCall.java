/*
By andrew luo
12/7/15

Desc:
This class makes a restful query to some website in order to gain information.

This is specifically directed toward KCLS's public health/restaurant inspection databases
*/

import java.util.*;
import java.io.*;
import java.net.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class RESTfulCall{

   private static String PARAMS = "params.txt";
   private static String BASE_URL = "http://info.kingcounty.gov/health/ehs/foodsa" +
      "fety/inspections/XmlRest.aspx?";

   public static void main(String[] args){
      ArrayList<Parameter> params = new ArrayList<Parameter>();
   
      //read from a list of parameters and what they do
      try{
         Scanner fin = new Scanner(new File(PARAMS));
         while(fin.hasNext()){
            String paramName = fin.next();
            int paramType = fin.nextInt();
            params.add(new Parameter(paramName, paramType));
         }
      }catch(FileNotFoundException e){
         System.out.println("Could not find file: " + PARAMS);
      }
      Collections.sort(params);
      
      //gain user input on what field they should assign to each parameter
      //null means the field is not included in the query
      Scanner s = new Scanner(System.in);
      for(int i = 0; i < params.size(); i++){
         System.out.println("Would you like to include information for " 
                              + params.get(i).paramName + "?");
         if(s.nextLine().toLowerCase().charAt(0) == 'y'){
            System.out.println("Please write your field");
            try{
               params.get(i).value = URLEncoder.encode(s.nextLine(), "UTF-8");
            }catch(UnsupportedEncodingException e){
               System.out.println("Unable to encode string!");
            }
         }
      }
      
      //encode the query information into the url we will later connect to
      String url = BASE_URL;
      for(int i = 0; i < params.size(); i++){
         if(params.get(i).value != null){
            url += params.get(i).paramName + "=" + params.get(i).value + "&";
         }
      }
      System.out.println("Connecting to: " + url);
      
      //gain a connection to the database
      URLConnection connection = null;
      InputStream input = null;
      try{
         connection = (new URL(url)).openConnection();
         input = connection.getInputStream();
      }catch(Exception e){
         System.out.println("Connection failed!");
      }
      
      //format xml string into something more usable using java's built in DOM parser
      //create the document builder
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = null;
      try{
         builder = factory.newDocumentBuilder();
      } catch(ParserConfigurationException e){
         System.out.println("Something went wrong with DocumentBuilder!");
      }
      
      //use document builder to create a nice document object model (DOM) easy to traverse
      Document document = null;
      try{
         //System.out.println(input);
         document = builder.parse(input);
      } catch(Exception e){
         System.out.println("Something went wrong with parsing the xml!");
         e.printStackTrace();
      }
      
      //Answer, not stored as an attribute, use getTextContent()
      System.out.println("First element: " + document.getDocumentElement());
      NodeList nodes = document.getDocumentElement().getChildNodes();
      for(int i = 1; i < nodes.getLength(); i++){  //ignore disclaimer node
         NodeList business = nodes.item(i).getChildNodes();
         for(int a = 0; a < business.getLength(); a++){
            Node element = business.item(a);
            System.out.println(element.getTextContent());
         }
      }
      
   }
  
}

