package Test;

import java.io.File;
import java.io.FileNotFoundException;

import Parser.LogRetrieverParser;
import simpleConfigMapType.SimpleConfigMapType;
import simpleConfigMapTypeParser.SimpleConfigMapTypeParser;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        /*
        SimpleConfigMapTypeParser parser = new SimpleConfigMapTypeParser(new File("test.txt"));
        SimpleConfigMapType map = parser.constructConfigMap();
        System.out.println(map);
        System.out.println(map.havingElementInFirstSet("this is second source"));
        System.out.println(map.havingElementInSecondSet("this won't be in the set"));*/
        
        System.out.println(LogRetrieverParser.parserToString_Set(new File("haha.txt")));
    }
}
