
public class Main {

    public static void main(String[] args) {


        String wrong_url = "https//www.audiobible.com/content/kingjames/audio/01_genesis/01_genesis_001.mp3";
        String mp3_url2 = "https://audiobible.com/templates/__custom/images/audio/kjv-scourby-genesis-1.mp3";
        String mp3_url3 = "https://audiobible.com/content/audio/NASB-CD-Johnston-1-Samuel-7.mp3";

        String image_url = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Bananas.jpg/1280px-Bananas.jpg";

        String spotify_url = "https://open.spotify.com/intl-de";
        
        //DownloadManager dManager = new DownloadManager(url2);

        new Thread(new DownloadManager(image_url)).start();
        
    }
    

}
