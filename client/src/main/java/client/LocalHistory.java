package client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LocalHistory {

    private static BufferedWriter fileHistory;

    private static String getPath(String login){
        return "client/history/history_"+login+".txt";
    }

    public static void startWriteHistori(String login){
        try {
            fileHistory = new BufferedWriter(new FileWriter(getPath(login),true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeWriteHistori(){
        if (fileHistory != null) {
            try {
                fileHistory.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeMsg(String msg){
        try {
            fileHistory.write(msg+System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getHistoryChat(String login){
        if (!Files.exists(Paths.get(getPath(login)))){
            return null;
        }
        String s = new String();
        List<String> stringList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getPath(login)))) {
            while ((s = reader.readLine()) != null ) {
                stringList.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return stringList;
    }



//    public class WriterAndReaderDemoApp {
//        public static void main(String[] args) {
//            try (BufferedWriter writer = new BufferedWriter( new
//                    FileWriter( "demo.txt" ))) {
//                for ( int i = 0 ; i < 20 ; i++) {
//                    writer.write( "Java\n" );
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try (BufferedReader reader = new BufferedReader( new
//                    FileReader( "demo.txt" ))) {
//                String str;
//                while ((str = reader.readLine()) != null ) {
//                    System.out.println(str);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }




}
