/**
 * Created by spandem on 9/26/2017.
 */
public class imdb {
    public int id;
    public String Title;
    public String Year;
    public String Rated;
    public String Released;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Awards;
    public String Director;
    public String Genre;
    public String imdbRating;
    public String Runtime;
    public String Language;
    public String Country;
    public String imdbVotes;
    public String imdbID;
    public String Type;
    public String totalSeasons;
    public String Response;
    public String Metascore;

    public static String getHeaders(){
        return  "id|Title|Year|Rated|Released|Writer|Actors|Plot|Awards|Director|Genre|imdbRating|Runtime|Language"
                + "|Country|imdbVotes|imdbID|Type|totalSeasons|Response|Metascore";
    }
    @Override
    public String toString() {

        return id +"|" +
                Title +"|" +
                Year +"|" +
                Rated +"|" +
                Released +"|" +
                Writer +"|" +
                Actors +"|" +
                Plot +"|" +
                Awards +"|" +
                Director +"|" +
                Genre +"|" +
                imdbRating +"|" +
                Runtime +"|" +
                Language +"|" +
                Country +"|" +
                imdbVotes +"|" +
                imdbID + "|" +
                Type + "|" +
                totalSeasons + "|" +
                Response + "|" +
                Metascore ;
    }
}
