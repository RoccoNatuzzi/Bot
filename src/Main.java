import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    public static URL finalulr;

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new MyBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void initImage() {
        //The url of the website. This is just an example
        ArrayList<String> site = new ArrayList<>();
        site.add("http://thegifer.tumblr.com/");//0
        site.add("http://fuckmeharder-please.tumblr.com/");
        site.add("http://addictedtofuckingandsex.tumblr.com/");
        site.add("http://only-the-best-stuff-xxx.tumblr.com/");
        site.add("http://deliciousnights.tumblr.com/");
        site.add("http://acidgifer.tumblr.com/");
        site.add("http://sfigtohsmuc.tumblr.com/");
        site.add("https://finegirlsdailycumshots.tumblr.com/");
        site.add("https://boobsarethegreatest.tumblr.com/");
        site.add("https://playboynudesforever.tumblr.com/");
        site.add("http://girlofpb.tumblr.com/");
        site.add("http://thethingsilove-playboygirls.tumblr.com/");//11

        int random =(int) (Math.random() * 12);
        //System.out.println(random);
        String webSiteURL = site.get(random);

        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(webSiteURL).get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("img");
            int cont = (int) (Math.random() * 10);
            //System.out.println(cont+"-------------");
            int i = 0;
            for (Element el : img) {
                if (cont == i) {
                    //for each element get the srs url
                    String src = el.absUrl("src");

                    System.out.println("Image Found!");
                    System.out.println("src attribute is : " + src);

                    getImages(src);
                    break;
                } else {
                    i++;
                }

            }

        } catch (IOException ex) {
            System.err.println("There was an error");
            Logger.getLogger(DownloadImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void getImages(String src) throws IOException {
        //String folderPath = "/C:/Users/rocco/Desktop/Nuova";

        // String folder = null;
        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());

        // System.out.println(name);

        //Open a URL Stream
        URL url = new URL(src);
        finalulr = url;
        if ((!finalulr.toString().endsWith("jpg") && !finalulr.toString().endsWith("gif")) || finalulr.toString().endsWith("logo.jpg")) {
            initImage();
        }
        /*InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();*/
    }


}
