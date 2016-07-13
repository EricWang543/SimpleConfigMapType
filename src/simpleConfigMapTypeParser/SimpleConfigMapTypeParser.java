package simpleConfigMapTypeParser;

import simpleConfigMapType.SimpleConfigMapType;

public class SimpleConfigMapTypeParser {
    private static final int MEET_END_UNEXPECTLY = 1;
    private static final int MEET_AT = 2;
    private static final int ALL_IS_WELL = 0;
    
    
    int catchContentStatus = ALL_IS_WELL;
    int catchContentPosition = 0;       
    
    String content = null;
/*    
    private String eliminateComments(String _input){
        String output = "";
        int position = 0;
        while (position < _input.length()){
            if (_input.charAt(position) != '#'){
                output += _input.charAt(position);
                ++position;
                continue;
            }
            else
                while (position < _input.length() &&_input.charAt(position) != '\n')
                    ++ position;
            ++position;
        }
        return output;
    }
    */
    
    //start search at contentposition, stop at the index right after next " met
    private String catchContent(){
        String result = "";
        while (catchContentPosition < content.length()){
            
            
            ++catchContentPosition;
        }
        
        
        return result;
    }
    
    
    public SimpleConfigMapTypeParser(String _content){
        assert _content != null;
        content = _content;
    }
    
    public SimpleConfigMapType constructConfigMap(){
        SimpleConfigMapType result = new SimpleConfigMapType();
        
        
        return result;
    }
    

}
