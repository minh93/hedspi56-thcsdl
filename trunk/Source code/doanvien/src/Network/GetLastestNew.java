package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Minh
 */
public class GetLastestNew {

    private String urlString;
    private String pattern;
    private String content = null;

    public GetLastestNew(String urlString, String pattern) {
        this.urlString = urlString;
        this.pattern = pattern;
    }

    public String getHTML() {
        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line; 
            while ((line = br.readLine()) != null) {                
                sb.append(line);                
            }
            content = sb.toString();            
            return content;

        } catch (MalformedURLException ex) {
            Logger.getLogger(GetLastestNew.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetLastestNew.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public ArrayList<String> getNewsContents(int group) {
        ArrayList<String> listContents = new ArrayList<>();
        if (content != null) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(content);
            while (m.find()) {
                listContents.add(m.group(group));
            }
        }
        return listContents;
    }
}
