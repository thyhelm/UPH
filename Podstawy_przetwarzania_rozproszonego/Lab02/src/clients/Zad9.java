package clients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Zad9 {
    static List<String> res = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<String>();
            List<Integer> listLoc = new ArrayList<Integer>();
            Random rand = new Random();

            Scanner scanner = new Scanner(System.in);
            int number = 0;
            int koniec = 0;

            System.out.println("Podaj wielkosc tablicy");
            number = scanner.nextInt();

            System.out.println("Podaj koniec zakresu");
            koniec = scanner.nextInt();

            for(int i=0; i<number; i++) {
                int r = rand.nextInt(koniec);
                list.add(Integer.toString(r));
                listLoc.add(r);
            }


            long startTime = System.nanoTime();
            Collections.sort(listLoc);
            long endTime = System.nanoTime();

            long durationLoc = (endTime - startTime)/1000000000;

            int subSize = (int) Math.floor(list.size()/3);

            List<String> subList1 = list.subList(0, subSize);
            List<String> subList2 = list.subList(subSize, 2* subSize);
            List<String> subList3 = list.subList(2* subSize, 3 * subSize);

            startTime = System.nanoTime();
            postRequest("8090", subList1);
            postRequest("8091", subList2);
            postRequest("8092", subList3);


            List<Integer> numbers = new ArrayList<Integer>();

            for(int i=0; i<res.size(); i++) {
                numbers.add(Integer.parseInt(res.get(i)));
            }
            Collections.sort(numbers);
            endTime = System.nanoTime();

            long durationSer = (endTime - startTime)/1000000;

            for(int i=0; i<numbers.size(); i++) {

                System.out.println(numbers.get(i));
            }
            System.out.println("Czas sortowania localnie: "+ durationLoc+"ms");
            System.out.println("Czas sortowania zdalnie: "+ durationSer+"ms");
        } catch (Exception ex) {
            System.err.println("Wystąpił błąd: "+ex.getLocalizedMessage());
        }
    }

    private static void postRequest(String port, List list) throws Exception {

        String url = "http://localhost:"+port+"/zad7";
        URL obj = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");


        conn.setDoOutput(true);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            for(int i=0; i<list.size(); i++) {
                if(i==0) {
                    wr.writeBytes("number"+i+"=" + list.get(i));
                }
                else {

                    wr.writeBytes("&number"+i+"=" + list.get(i));
                }
            }

            wr.flush();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("\nWysyłanie żądania 'POST' pod URL: " + url);
        System.out.println("Kod odpowiedzi: " + responseCode);

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            String line;
            response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                res.add(line);
            }
        }

        //print response
        System.out.println(response.toString());

    }

}
