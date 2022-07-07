/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package slangword;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author ANTON
 */
public class slangWordMap {

    /**
     * @param args the command line arguments
     */
    //public HashMap<String, String> map;
    public TreeMap<String, String> map;
    public ArrayList<String> history = new ArrayList<>();
    //Map<String, String> pref = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
    private static final String historyFile = "history.txt";
    private static final String slang = "slang.txt";
    
    public slangWordMap() throws IOException {
        map = nhapXuatFile.readFile(slang);
    }
    
    public void searchByKey(String key){
        String definition = map.get(key.toLowerCase(Locale.ROOT));
        if (definition != null){
            System.out.println("Nghia cua: " + key + " la: " + definition);
        }
        else {
            System.out.println("Khong tim thay keyword nhap vao!");
        }
        history.add(key);
    }
    
    public void searchByDef(String def){
        ArrayList<String> keyList = new ArrayList<>();
        
        for (String key : map.keySet()){
            if (map.get(key).contains(def))
                keyList.add(key);
        }
        for (String key : keyList){
            System.out.println(key + ": " + map.get(key));
        }
        history.add(def);
    }
    
    public void add () {
        Scanner s = new Scanner(System.in);
        System.out.println("Nhap vao slangword: ");
        String key = s.nextLine();
        
        if (checkKey(key) == true){
            System.out.println("Slang word da co trong tu dien!");
        }else{
            System.out.println("Nhap vao dinh nghia: ");
            String def = s.nextLine();

            map.put(key, def);
            System.out.println("Them vao thanh cong: " + key);
            nhapXuatFile.writeFile("slangAdded.txt", map);
        } 
    }
    
    public void delete(){
        Scanner s = new Scanner(System.in);
        System.out.println("Nhap vao slangword: ");
        String key = s.nextLine();
        
        if (checkKey(key) == false){
            System.out.println("Slang word khong co trong tu dien!");
        }else{
            map.remove(key);
            System.out.println("Da xoa thanh cong slang word: " + key);
            nhapXuatFile.writeFile("slang.txt", map);
        } 
    }
    public boolean checkKey(String key) {
        for (String keyChecker : map.keySet()) {
                if (keyChecker.equals(key))
                        return true;
        }
        return false;
    }
}
