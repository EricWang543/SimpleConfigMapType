package Test;

import java.io.File;
import java.io.FileNotFoundException;

import simpleConfigMapType.SimpleConfigMapType;
import simpleConfigMapTypeParser.SimpleConfigMapTypeParser;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub

        SimpleConfigMapTypeParser parser = new SimpleConfigMapTypeParser(new File("test.txt"));
        SimpleConfigMapType map = parser.constructConfigMap();
        System.out.println(map);
        System.out.println(map.havingString("this is second source"));
        System.out.println(map.havingString("this won't be in the set"));
    }

}
