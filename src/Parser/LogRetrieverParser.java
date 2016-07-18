package Parser;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LogRetrieverParser {
    // take format: XXX:YYY,ZZZZ     space will be eliminated
    public static HashMap<String, HashSet<String>> parserToString_Set(String _content){
        HashMap<String, HashSet<String>> result = new HashMap<String, HashSet<String>>();
        BufferedReader text;
        try {
            text = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(_content.getBytes("UTF-8")), "UTF-8"));
            
            String currentLine;    
            while ((currentLine = text.readLine()) != null){
                
                if (!currentLine.contains(":"))
                    continue;
                
                String[] parts = currentLine.split(Pattern.quote(":"));
                if (parts.length != 2)
                    continue;
                
                String source = parts[0];
                HashSet<String> targets = new HashSet<String>(Arrays.asList(parts[1].split(",")));
                
                result.put(source, targets);             
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static HashMap<String, HashSet<String>> parserToString_Set(File _file) throws FileNotFoundException{
        assert _file != null;
        String content = "";
        Scanner sc;
        sc = new Scanner(_file, "UTF-8");
        String newLine = "";
        while (sc.hasNextLine()){
            newLine = sc.nextLine();
            content = content + newLine + "\n";
        }
        sc.close();
        return parserToString_Set(content);
    }


}