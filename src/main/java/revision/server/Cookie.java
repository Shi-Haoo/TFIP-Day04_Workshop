package revision.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {
    
    public static String getRandomCookie(String cookiePath, String cookieResultPath){

        List<String> cookies = new LinkedList<>();
        List<String> cookiesResult = new ArrayList<>();
        String randomCookie = "";

        try{
            cookies = getDataFromTxt(cookiePath);

            //need to store cookie alr in cookieResult.txt into cookiesResult ArrayList. Else, 
            //empty arraylist will always be passed into writeCookieNametoResultFile and we wont be
            //able to verify if any cookie stored in cookieResult arraylist is duplicate

            cookiesResult = getDataFromTxt(cookieResultPath);
            System.out.println(cookiesResult.size());

        if(cookiesResult.size() == 0){

            //Need add empty strings so that when we use .set subsequently, the program 
            //has index no. to refer to put in the right index position
            for(int i = 0; i < cookies.size(); i++){
                cookiesResult.add("");
            }
            
        }

        Random rand = new Random();
        int randIdx = rand.nextInt(0, cookies.size());
        randomCookie = cookies.get(randIdx);

        writeCookieNameToResultFile(randomCookie, randIdx, cookiesResult, cookieResultPath);

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        return randomCookie;
    }

    public static void writeCookieNameToResultFile(String cookieName, int cookieIndex, 
    List<String> cookiesResultList, String resultPath)throws IOException{

        System.out.println("cookiesResult >>>"+cookiesResultList);

        if(!(cookiesResultList.contains("cookieName"))){
            cookiesResultList.set(cookieIndex,cookieName);
        }

        System.out.println(cookiesResultList);

        FileWriter fw = new FileWriter(resultPath);
        
        for(String cookieNameFromArrayList : cookiesResultList){
            fw.write(cookieNameFromArrayList + "\n");
        }
        fw.close();
    }

    public static List<String> getDataFromTxt(String filePath) throws FileNotFoundException, IOException{
    
        List<String> list = new LinkedList<>();
        
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;

        while((line = br.readLine()) != null){
            list.add(line);

        }
        return list;
    }

}