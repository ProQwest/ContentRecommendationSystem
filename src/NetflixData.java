import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by spandem on 9/30/2017.
 */
public class NetflixData {

    public static void main(String[] args) {

        try {
            File movie_user = new File("C:\\Users\\spandem\\cmu\\AppliedDataScience\\Projects\\netflix-prize-data"
                                               + "\\netflix-user-movie.txt");
            BufferedWriter output = new BufferedWriter(new FileWriter(movie_user, true));
            for(int fileid=1; fileid<=1 ; fileid++){
                File file = new File("C:\\Users\\spandem\\cmu\\AppliedDataScience\\Projects\\netflix-prize-data"
                                             + "\\combined_data_"+fileid+".txt");
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuffer stringBuffer = new StringBuffer();
                String movieline = bufferedReader.readLine();
                while (movieline != null) {
                    Pattern p = Pattern.compile("[0-9]*:");
                    Matcher m = p.matcher(movieline);
                    String userline = null;
                    if(m.find()){
                        while ((userline = bufferedReader.readLine()) != null){
                            Pattern ratingPattern = Pattern.compile("[0-9]*,[0-9]*,[0-9]*-[0-9]*-[0-9]");
                            Matcher ratingMatcher = ratingPattern.matcher(userline);
                            if(ratingMatcher.find()){
                                output.append(movieline.substring(0,movieline.length()-1)+","+userline+"\n");
                                System.out.println(movieline+userline);
                                output.flush();
                            }else{
                                movieline = userline;
                                break;
                            }
                        }
                        if(bufferedReader.readLine() == null){
                            break;
                        }
                    }
                }
                fileReader.close();
            }
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
