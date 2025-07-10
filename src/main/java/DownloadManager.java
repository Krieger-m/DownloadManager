
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class DownloadManager implements Runnable{

    private String link;
    private File output;

    private String fileSeparator = System.getProperty("file.separator");
    private String downloadPath ="src" + fileSeparator +"main"+ fileSeparator +"resources"+ fileSeparator +"Downloads";
    //src\main\resources

    private File defaultDownloadFolder = new File(downloadPath);

    public DownloadManager(String _link){

        this.link = _link;

        if(!defaultDownloadFolder.exists()){
            defaultDownloadFolder.mkdirs();


        }

    }

    public String makeFileName(String _url){
        String[] result = _url.split("/");
        System.out.println(result);
        return result[result.length-1];
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);

            HttpURLConnection hConnection = (HttpURLConnection)url.openConnection();

            // InputStream arbeitet immer mit Byte
            BufferedInputStream bInputStream = new BufferedInputStream(hConnection.getInputStream());

            // Datei erstellen / schreiben
            output = new File(defaultDownloadFolder, makeFileName(link));
            // zu schreibende Datei
            OutputStream outputStream = new FileOutputStream(output);
            // 1024 Byte = 1KByte = Puffergröße!
            BufferedOutputStream bOutStream = new BufferedOutputStream(outputStream,1024);
            
            DecimalFormat format = new DecimalFormat("###.###");

            byte[] buffer = new byte[1024];

            int downloaded=0;            // bereits geladen
            int readByte=0;            // aktuell geladen

            int count =0;
            System.out.println("downloading ... : ");
            while((readByte = bInputStream.read(buffer, 0, 1024)) >= 0){
                
                bOutStream.write(buffer,0,readByte);
                downloaded += readByte;
                
                if(count %50==1){
                    System.out.println("\t... "+ format.format(downloaded/Math.pow(10,6)) +" MB");
                }
                count++;
            }
            System.out.println("\t... "+ format.format(downloaded/Math.pow(10,6)) +" MB");

            bOutStream.close();
            bInputStream.close();
            System.out.println("\n\t-> Download successful!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }



}
