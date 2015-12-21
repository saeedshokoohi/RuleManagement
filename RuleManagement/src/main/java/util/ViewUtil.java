package util;

//import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by saeed on 07/01/2016.
 */
public class ViewUtil {
    public static String ObjectToJsonStr(Object obj){
        ObjectMapper mapper=new ObjectMapper();
        try {
            String retStr = mapper.writeValueAsString(obj);
            return retStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Object JsonToObject(String jsonStr,Class<?> classType) {
        Object retObject=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            retObject=mapper.readValue(jsonStr,classType);
        } catch (IOException e) {
                e.printStackTrace();
        }
        return  retObject;
    }
}
