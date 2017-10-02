/**
 * Created by spandem on 9/25/2017.
 */

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @noinspection ALL*/
public class OmdbClient {

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) {

        String[] movieTitles = new String[17770];
        String[] movieYears = new String[17770];
        try {
            File file = new File("C:\\Users\\spandem\\cmu\\AppliedDataScience\\Projects\\netflix-prize-data"
                                         + "\\movie_titles.csv");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {

                Pattern p = Pattern.compile("[0-9]*,[0-9]*,");
                Matcher m = p.matcher(line);
                if(m.find()){
                    String movieTitle = line.substring(m.end());
                    String[] list = m.group().split(",");
                    String movieId = list[0];
                    String movieYear = list[1];
                    movieTitles[Integer.parseInt(movieId.trim())-1] = movieTitle.trim();
                    movieYears[Integer.parseInt(movieId.trim())-1] = movieYear.trim();
                    System.out.println(movieId+" "+movieTitles[Integer.parseInt(movieId.trim())-1]+ " "+movieYears[Integer.parseInt(movieId.trim())-1] );
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {

            File f = new File("C:\\Users\\spandem\\cmu\\AppliedDataScience\\Projects\\netflix-prize-data"
                                      + "\\omdb4369.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(f));
            output.append(imdb.getHeaders()+"\n");
            int i=0;
            while (i<17770){
                System.out.println(i+"-"+movieTitles[i]+"-"+movieYears[i]);
                String selectedItem = movieTitles[i].replace("\\s+", "+");

                String json = IOUtils.toString(new URL("http://www.omdbapi.com/?apikey=<key>&y="+movieYears[i]+"&t=" +
                                                               URLEncoder.encode
                                                                       (selectedItem, "UTF-8")));
                Gson gson = new Gson();
                imdb m = gson.fromJson(json, imdb.class);
                m.id = i+1;
                output.append(m.toString()+"\n");
                output.flush();
                i++;
            }
            output.close();
        } catch (JsonIOException | JsonSyntaxException | IOException e){
            System.out.println(e);
        }
    }
}


