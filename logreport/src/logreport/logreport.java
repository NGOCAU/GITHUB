package logreport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Scanner;

import de.gerdiproject.json.GsonUtils;
import org.json.JSONObject;

public class logreport {
	

	 public static void main(String[] args){
	        String string = "";
	        String file = "D:\\LOGNEAC\\Thang6\\tokenws.log.2020-06-";
	        String stfilter1="DatabaseImpl";
	        String stfilter2="jackrabbit";
	        String sn="";
	        String err="tokenws";
	        int i=1;
	        String serial = "";
	        Scanner inScanner = new Scanner(System.in);
	        //inScanner.useDelimiter("\n");
	        System.out.print("Nhập ngày sinh file log: ");
	        String inFile = inScanner.next();
	        //String directoryName = file.concat(this.getClassName());
	        File directory = new File("./Log/thang6/"+inFile+"/");
	        if (! directory.exists()){
		        directory.mkdir();
		        // If you require it to make the entire directory path including parents,
		        // use directory.mkdirs(); here instead.
		        //FileWriter myWriter = new FileWriter("./Log/thang4/"+inFile+"/"+i+++"_" +serial);
	        }
	        System.out.println(directory);
	          // Gson gson = new Gson();
	        //JSONParser parser = new JSONParser();
	        // Reading
	        try{
	            InputStream ips = new FileInputStream(file+inFile);
	            InputStreamReader ipsr = new InputStreamReader(ips);
	            BufferedReader br = new BufferedReader(ipsr);
	            String line;
	            while ((line = br.readLine()) != null){
	                if (!line.contains(stfilter1) && !line.contains(stfilter2)) {
	                	int index= line.indexOf("54010C");
                		int length=32;
                		
                		if(index > 0) {
                			serial=line.substring(index, index+length);
                			System.out.println(serial);

                		}

	                	if(line.contains(err)) {
	                		int breakline = sn.indexOf("\n", sn.indexOf("XmlData:"));
	                		sn = sn.substring(0, breakline);
	                		 line="";
	                		 System.out.println(sn);
	                		// System.out.println(line);
//	                		 FileWriter myWriter = new FileWriter(directory+"/"+i+++"_" +serial);
//	                		
//	                	     myWriter.write(sn.trim());
//	                	     myWriter.close();
//	                	     System.out.println("\nSuccessfully wrote to the file."+i);
	                	     sn="";
	                	}
	                    sn += line + "\n";
	                    
	                }

	            }
	            br.close();
	        }
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
	 
	     
	    }
}
