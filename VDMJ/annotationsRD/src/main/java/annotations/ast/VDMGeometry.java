package annotations.ast;

import java.util.Map;
import java.util.HashMap;

public class VDMGeometry {
    private String name;
    private String type;
    private Map<String, Value> attributes = new HashMap<String,Value>();

    public void setType(String t){
        type = t;
    }

    public void setName(String n){
        name = n;
    }

    public String getType(){
        return type;
    }

    public String getName(){
        return name;
    }

    public void addAttribute(String s, Value v){
        attributes.put(s, v);
    }

    public String getValue(String key){
        return attributes.get(key).toString();
         
    }

    public String toString(){
        String res = "\n" + name +" "+ type + " : ";
 
        for (String key : attributes.keySet())
            res = res + key + " : " + attributes.get(key).toString() + " ";
            // res = res + attributes.get(key).toString() + "\n";
 
        // attributes.forEach((k, v) -> res = res + k + " " + v + "\n");
        return res;
    }
}
