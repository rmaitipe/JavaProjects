package core;

import java.io.*;
import java.util.*;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CssJsonReader {

    public static void main (String[] args){
        CssJsonReader test= new CssJsonReader();
        test.printSecret("https://docs.google.com/document/d/e/2PACX-1vTER-wL5E8YC9pxDx43gk8eIds59GtUUk4nJo_ZWagbnrH0NFvMXIw6VWFLpf5tWTZIT9P9oLIoFJ6A/pub");
    }

    public void printSecret(String url){
        List<List<String>> linesList = new ArrayList<>();
        try {
            linesList= readJsonSoupFromUrl(url);
            //JSONObject linesList1 = readJsonFromUrl(url);
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        Map<Map.Entry<Integer,Integer>,Character> map =new HashMap<>();
        int maxX=0;
        int maxY=0;
        for (List<String> strList: linesList){
            String strX= strList.getFirst();
            int x = Integer.parseInt(strX);
            maxX=Math.max(x,maxX);
            Character character= strList.get(1).charAt(0);
            String strY= strList.getLast();
            int y= Integer.parseInt(strY);
            maxY=Math.max(y,maxY);
            map.put(Map.entry(x,y),character);
        }

        // using maxX and maxy as grid constraints
        for (int j=maxY;j>=0;j--){
            for (int i=0; i<=maxX;i++){
                if (map.containsKey(Map.entry(i,j)))  {
                    Character val=map.get(Map.entry(i,j));
                    if (i<maxX){
                        System.out.print(val);
                    }
                    else{
                        System.out.println(Character.toUpperCase(val));
                    }
                } else{
                    if (i<maxX){
                        System.out.print(" ");
                    }
                    else{
                        System.out.println(" ");
                    }
                }
            }
        }
    }

    public static List<List<String>> readJsonSoupFromUrl(String url) throws IOException, JSONException {
        List<List<String>> retVal=new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table").first();
        //table.select("tr").removeFirst();
        Elements tbody = table.select("tr:not(:first-child)");
        tbody.forEach(e ->addToList(e,retVal));
        return retVal;
    }

    public static void addToList(Element row, List<List<String>> retVal){
        List<String> list =new ArrayList<>();
        Elements dataCells = row.select("td");
        for (Element cell : dataCells) {
            list.add(cell.text());
        }
        retVal.add(list);
    }
}
