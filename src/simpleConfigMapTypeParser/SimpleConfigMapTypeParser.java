package simpleConfigMapTypeParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import simpleConfigMapType.SimpleConfigMapType;

public class SimpleConfigMapTypeParser {
    
    //number indicates the priority
    public static final int MEET_END_UNEXPECTLY = 3;
    public static final int MEET_END = 2;
    public static final int MEET_AT = 1;
    public static final int ALL_IS_WELL = 0;
    
    
    private int catchContentStatus = ALL_IS_WELL;
    private int catchContentPosition = 0;       
    private String content = null;
    
    //start search at catchContentPosition, stop at the index right after next " met
    private String catchNextContent(){
        assert catchContentPosition <= content.length();
        StringBuffer result = new StringBuffer();
        catchContentStatus = ALL_IS_WELL;
            
        //point to next "
        while (catchContentPosition < content.length() && content.charAt(catchContentPosition) != '"'){
            if (content.charAt(catchContentPosition) == '@')
                catchContentStatus = MEET_AT;
            ++catchContentPosition;
        }
        if (catchContentPosition  >= content.length()){
            catchContentStatus = MEET_END;
            return null;
        }
        
        //extract content to result until next "
        ++catchContentPosition;
        while (catchContentPosition < content.length() && content.charAt(catchContentPosition) != '"'){
            //escape once
            if (content.charAt(catchContentPosition) == '/'){
                if (catchContentPosition != content.length() - 1){
                    ++ catchContentPosition;
                    result.append(content.charAt(catchContentPosition));
                    ++catchContentPosition;
                    continue;
                }
                else{
                    catchContentStatus = MEET_END_UNEXPECTLY;
                    return null;
                }
            } 
            //else just append
            else {
                result.append(content.charAt(catchContentPosition));
                ++catchContentPosition;
                continue;
            }
        }
        if (catchContentPosition == content.length()){
            catchContentStatus = MEET_END_UNEXPECTLY;
            return null;
        }
        
        //point to char next to "
        ++catchContentPosition;
        return result.toString();
    }

    public SimpleConfigMapTypeParser(String _content){
        assert _content != null;
        content = _content;
    }
    
    public SimpleConfigMapTypeParser(File _inputFile) throws FileNotFoundException{
        assert _inputFile != null;
        content = "";
        Scanner sc;
        sc = new Scanner(_inputFile, "UTF-8");
        String newLine = "";
        while (sc.hasNextLine()){
            newLine = sc.nextLine();
            content = content + newLine + "\n";
        }
        sc.close();
    }
    
    public SimpleConfigMapType constructConfigMap(){
        SimpleConfigMapType result = new SimpleConfigMapType();
        catchContentPosition = 0;
        catchContentStatus = ALL_IS_WELL;
        String nextItem = null;
        //get parameters
        do{
            nextItem = catchNextContent();
            switch (catchContentStatus){
            case MEET_END: 
                return result;
            case MEET_AT:
                break;
            case MEET_END_UNEXPECTLY:
                throw new RuntimeErrorException(null);
            case ALL_IS_WELL:
                result.addParameter(nextItem);
                break;
            default:
                throw new RuntimeErrorException(null);          //should never be here!
            }
        }
        while (catchContentStatus != MEET_AT);
        
        //go get map
        while (catchContentStatus != MEET_END){
            HashSet<String> first = new HashSet<String>();
            HashSet<String> second = new HashSet<String>();
            
            //continue to catch second_set
            while (catchContentStatus == MEET_AT){
                second.add(nextItem);
                nextItem = catchNextContent();
            }
            switch (catchContentStatus){
            case MEET_END: 
                throw new RuntimeErrorException(null);
            case MEET_AT:
                assert catchContentStatus != MEET_AT;           //should never be here
                throw new RuntimeErrorException(null);
            case MEET_END_UNEXPECTLY:
                throw new RuntimeErrorException(null);
            case ALL_IS_WELL:
                break;
            default:
                throw new RuntimeErrorException(null);          //should never be here!
            }
            
            //continue to catch first_set
            while (catchContentStatus == ALL_IS_WELL){
                first.add(nextItem);
                nextItem = catchNextContent();
            }
            switch (catchContentStatus){
            case MEET_END:
                result.addPair(first, second);
                return result;
            case MEET_AT:
                result.addPair(first, second);
                continue;       //goto next round
            case MEET_END_UNEXPECTLY:
                throw new RuntimeErrorException(null);
            case ALL_IS_WELL:
                assert catchContentStatus != ALL_IS_WELL;       //should never be here
                throw new RuntimeErrorException(null);
            default:
                throw new RuntimeErrorException(null);          //should never be here!
            }
        }

        //return result  //actually never should be here
        return result;
    }
    

}