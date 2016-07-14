package simpleConfigMapType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SimpleConfigMapType {
    HashMap<HashSet<String>, HashSet<String>> mapContent;
    HashSet<String> parameterSet;
    
    public SimpleConfigMapType(){
        mapContent = new HashMap<HashSet<String>, HashSet<String>>();
        parameterSet = new HashSet<String>();
    }
    
    @SuppressWarnings("unchecked")
    public boolean addPair(HashSet<String> _fisrt, HashSet<String> _second){
        mapContent.put((HashSet<String>) _fisrt.clone(), (HashSet<String>) _second.clone());
        return true;
    }
    
    public boolean addParameter(String _p){
        if (_p != null){
            parameterSet.add(_p);
            return true;
        }
        return false;
    }
    
    public boolean deletePairByFirst(String _elementOfFirst){
        HashSet<String> firstSet = getFirstSet(_elementOfFirst);
        if (firstSet != null)
            return deletePairByFirst(firstSet);
        return false;
    }
    
    public boolean deletePairByFirst(HashSet<String> _first){
        if (_first == null) return false;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().equals(_first))
                return mapContent.remove(entry.getKey()) != null;
        return false;
    }
    
    public boolean havingString(String _elementOfFirst){
        if (getFirstSet(_elementOfFirst) != null)
            return true;
        return false; 
    }
    
    @SuppressWarnings("unchecked")
    public HashSet<String> getFirstSet(String _elementOfFirst){
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().contains(_elementOfFirst))
                return (HashSet<String>) entry.getKey().clone();
        return null;
    }
    
    public HashSet<String> getSecondSet(String _elementOfFirst){
        return getSecondSet(getFirstSet(_elementOfFirst));
    }
    
    @SuppressWarnings("unchecked")
    public HashSet<String> getSecondSet(HashSet<String> _first){
        if (_first == null)
            return null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().equals(_first))
                return (HashSet<String>) entry.getValue().clone();
        return null;
    }
    
    public int getMapSize(){
        return mapContent.size();
        
    }
    
    public int getParameterSetSize(){
        return parameterSet.size();
    }

    public String toString(){
        String result = "";
        result = result +  "Parameter Set = " + parameterSet.toString() + "\n";
        result = result + "Map Content = \n";
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            result = result + entry.getKey().toString() + ":" + entry.getValue().toString() + "\n";
        return result;
    }
}
