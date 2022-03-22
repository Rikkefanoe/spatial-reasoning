package annotations.ast;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VDMGeometry extends Value{
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

    public String toString(){
        String res = name +" "+ type + " ";
        List<String> attributeList = new ArrayList<>(attributes.keySet());
        String lastKey = attributeList.get(attributeList.size()-1);
        res = res + "(";
        for (String key : attributeList){
            res = res + key + " = " + attributes.get(key).toString();
            if(!key.equals(lastKey)){
                res = res + ", ";
            }
        }
        res = res + ")";
        return res;
    }
}
