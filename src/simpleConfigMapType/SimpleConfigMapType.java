package simpleConfigMapType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class SimpleConfigMapType {
    HashMap<HashSet<String>, HashSet<String>> mapContent;
    List<String> parameterList;
    
    public SimpleConfigMapType(){
        mapContent = new HashMap<HashSet<String>, HashSet<String>>();
        parameterList = new ArrayList<String>();
    }
    
    //return true if [X] -> [..f(X)] i.e. map is equivalent to Map<Sting, Set<String>>
    public boolean isSetInjective(){
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().size() > 1)
                return false;
        return true;
    }
    
    //return true if [..f(X)] -> [X] i.e. map is equivalent to Map<Set<Sting>, String>
    public boolean isSetSurjective(){
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getValue().size() > 1)
                return false;
        return true;
    }
    
    //return true if [X] -> [f(X)] i.e. map is equivalent to Map<Sting, String>
    public boolean isSetBijective(){
        return isSetInjective() && isSetSurjective();
    }
    
    //return true if first sets don't have any common elements with each other
    public boolean isElementInjective(){
        HashSet<String> firstSetUnion = getFirstSetUnion();
        int totalElementsInFirst = 0;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            totalElementsInFirst += entry.getKey().size();
        assert totalElementsInFirst >= firstSetUnion.size();
        if (totalElementsInFirst > firstSetUnion.size())
            return false;
        return true;
    }
    
    //return true if second sets don't have any common elements with each other
    public boolean isElementSurjective(){
        HashSet<String> secondSetUnion = getSecondSetUnion();
        int totalElementsInSecond = 0;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            totalElementsInSecond += entry.getValue().size();
        assert totalElementsInSecond >= secondSetUnion.size();
        if (totalElementsInSecond > secondSetUnion.size())
            return false;
        return true;
    }
    
    public boolean isElementBijective(){
        return isElementSurjective() && isElementInjective();
    }
    
    @SuppressWarnings("unchecked")
    public boolean addPair(HashSet<String> _first, HashSet<String> _second){
        assert _first != null;
        assert _second != null;
        mapContent.put((HashSet<String>) _first.clone(), (HashSet<String>) _second.clone());
        return true;
    }
    
    public boolean havingElementInFirstSet(String _elementOfFirst){
        assert _elementOfFirst != null;
        if (getFirstSetUnion().contains(_elementOfFirst))
            return true;
        return false; 
    }
    
    public boolean havingElementInSecondSet(String _elementOfSecond){
        assert _elementOfSecond != null;
        if (getSecondSetUnion().contains(_elementOfSecond))
            return true;
        return false; 
    }

    public void addParameter(String _p){
        assert _p != null;
        parameterList.add(_p);
    }
    
    /*
     * find and delete sets by first series
     */
    //useful when you want to confirm if a set exists in the map
    @SuppressWarnings("unchecked")
    public HashSet<String> getFirstSetByFirst(HashSet<String> _first){
        assert _first != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().equals(_first))
                return (HashSet<String>) entry.getKey().clone();
        return null;
    }

    //only return the first found
    @SuppressWarnings("unchecked")
    public HashSet<String> getFirstSetByFirst(String _elementOfFirst){
        assert _elementOfFirst != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().contains(_elementOfFirst))
                return (HashSet<String>) entry.getKey().clone();
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public HashSet<String> getSecondSetByFirst(HashSet<String> _first){
        assert _first != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().equals(_first))
                return (HashSet<String>) entry.getValue().clone();
        return null;
    }
    
    //only return the first found
    @SuppressWarnings("unchecked")
    public HashSet<String> getSecondSetByFirst(String _elementOfFirst){
        assert _elementOfFirst != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().contains(_elementOfFirst))
                return (HashSet<String>) entry.getValue().clone();
        return null;
    }
    
    public boolean deletePairByFirst(HashSet<String> _first){
        assert _first != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getKey().equals(_first))
                return mapContent.remove(entry.getKey()) != null;
        return false;
    }
    
    //only can be used when the map isElementInjective
    public boolean deletePairByFirst(String _elementOfFirst){
        assert isElementInjective();
        assert _elementOfFirst != null;
        HashSet<String> firstSet = getFirstSetByFirst(_elementOfFirst);
        if (firstSet != null)
            return deletePairByFirst(firstSet);
        return false;
    }
    

    /*
     *  find and delete sets by second series
     */
    @SuppressWarnings("unchecked")
    public HashSet<String> getFirstSetBySecond(HashSet<String> _second){
        assert _second != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getValue().equals(_second))
                return (HashSet<String>) entry.getKey().clone();
        return null;
    }
    
    //only return the first found
    @SuppressWarnings("unchecked")
    public HashSet<String> getFirstSetBySecond(String _elementOfSecond){
        assert _elementOfSecond != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getValue().contains(_elementOfSecond))
                return (HashSet<String>) entry.getKey().clone();
        return null;
    }
    
    //useless unless you want to confirm a set exists
    @SuppressWarnings("unchecked")
    public HashSet<String> getSecondSetBySecond(HashSet<String> _second){
        assert _second != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getValue().equals(_second))
                return (HashSet<String>) entry.getValue().clone();
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public HashSet<String> getSecondSetBySecond(String _elementOfSecond){
        assert _elementOfSecond != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getValue().contains(_elementOfSecond))
                return (HashSet<String>) entry.getValue().clone();
        return null;
        
    }
    
    public boolean deletePairBySecond(HashSet<String> _second){
        assert _second != null;
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            if (entry.getValue().equals(_second))
                return mapContent.remove(entry.getKey()) != null;
        return false;
    }
    
    //only can be used when the map isElementSerjective
    public boolean deletePairBySecond(String _elementOfSecond){
        assert isElementSurjective();
        assert _elementOfSecond != null;
        HashSet<String> secondSet = getSecondSetBySecond(_elementOfSecond);
        if (secondSet != null)
            return deletePairBySecond(secondSet);
        return false;
    }
    
    @SuppressWarnings("unchecked")
    public HashMap<HashSet<String>, HashSet<String>> getMapContent(){
        return (HashMap<HashSet<String>, HashSet<String>>) mapContent.clone();
    }
    
    public HashSet<String> getFirstSetUnion(){
        HashSet<String> result = new HashSet<String>();
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            result.addAll(entry.getKey());
        return result;
    }
    
    public HashSet<String> getSecondSetUnion(){
        HashSet<String> result = new HashSet<String>();
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            result.addAll(entry.getValue());
        return result;
    }
    
    public List<String> getParameterSet(){
        List<String> result = new ArrayList<String>();
        result.addAll(parameterList);
        return result;
    }
    
    public int getMapSize(){
        return mapContent.size();
    }
    
    public int getParameterSetSize(){
        return parameterList.size();
    }

    public String toString(){
        StringBuffer result = new StringBuffer();
        result.append("Parameter Set = " + parameterList.toString() + "\n");
        result.append("Map Content = \n");
        for (Map.Entry<HashSet<String>, HashSet<String>> entry : mapContent.entrySet())
            result.append(entry.getKey().toString() + "\n  -> " + entry.getValue().toString() + "\n");
        return result.toString();
    }
}