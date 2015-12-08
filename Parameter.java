//describes a parameter and its valid behaviors
public class Parameter implements Comparable<Parameter>{
   /*
   Parameter types:
   1 = string
   2 = MM/DD/YYYY
   3 = Yes, No, All
   4 = Integer < 999
   */
   
   public String paramName;
   public int paramType;
   
   public String value;
   
   public Parameter(String paramName, int paramType){
      this.paramName = paramName;
      this.paramType = paramType;
   }
   
   public void setValue(String value){
      this.value = value;
   }
   
   public int compareTo(Parameter other){
      return this.paramType - other.paramType;
   }
   
   public String toString(){
      return paramName + "=" + value;
   }
   
}
