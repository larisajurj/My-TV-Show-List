package utilities;
import java.io.*;


public class CSVReader {
    private String file;
    private BufferedReader reader;
    private String line;

    public CSVReader(String file) {
        this.file = file;
        BufferedReader reader = null;
        String line = "";

    }
    //CSV = Comma-Separated Values
            //   text file that uses a comma to separate values

    public void Read(){
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {

                //String[] row = line.split(",");
                String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                //use this if your values already contain commas
                for(String index : row) {
                    System.out.printf("%-10s", index);
                }
                System.out.println();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
