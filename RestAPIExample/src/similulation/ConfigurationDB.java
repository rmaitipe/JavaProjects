package similulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import common.Status;
import resources.Configuration;
 
 
public class ConfigurationDB {
    private static Map<Integer, Configuration> configurationDBMap = new ConcurrentHashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger();
     
    public static Integer createConfiguration(String content, Status status){
        Configuration c = new Configuration();
        c.setId(idCounter.incrementAndGet());
        c.setContent(content);
        c.setStatus(status);
        configurationDBMap.put(c.getId(), c);
         
        return c.getId();
    }
     
    public static Configuration getConfiguration(Integer id){
        return configurationDBMap.get(id);
    }
     
    public static List<Configuration> getAllConfigurations(){
        return new ArrayList<>(configurationDBMap.values());
    }
     
    public static Configuration removeConfiguration(Integer id){
        return configurationDBMap.remove(id);
    }
     
    public static Configuration updateConfiguration(Integer id, Configuration c){
        return configurationDBMap.put(id, c);
    }
}