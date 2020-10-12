package logreport;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class logreportthang1 {
	 public static void main(String[] args){
	        String string = "";
	        String file = "D:\\LOGNEAC\\Thang1\\tokenws.log.2020-01-";
	        String stfilter="[vn.mobileid.tokenservice.db.DatabaseImpl]";
	        String stfilter2="[revoke]";
	        String stfilter3="[enrollCertificate]";
	        String sn="";
	        String err="DMSJackRabbit";
	        int i=1;
	        String serial = "";
	        Scanner inScanner = new Scanner(System.in);
	        //inScanner.useDelimiter("\n");
	        System.out.print("Nhập ngày sinh file log: ");
	        String inFile = inScanner.next();
	       
	       // Gson gson = new Gson();
	        //JSONParser parser = new JSONParser();
	        // Reading
	        try{
	            InputStream ips = new FileInputStream(file+inFile);
	            InputStreamReader ipsr = new InputStreamReader(ips);
	            BufferedReader br = new BufferedReader(ipsr);
	            String line;
	            while ((line = br.readLine()) != null){
	                if (!line.contains(stfilter) ) {
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
	                		 //System.out.println(sn);
	                		 FileWriter myWriter = new FileWriter("./Log/thang1/01"+inFile+"_"+i+++"_" +serial);
	                	     myWriter.write(sn);
	                	     myWriter.close();
	                	     System.out.println("Successfully wrote to the file."+i);
	                	     sn="";
	                	}
	                    sn += line + "\n";
	                    
//	                    System.out.println(sn.trim());
	                 
//	                    JSONObject obj = new JSONObject(line);
//	                    //System.out.println(obj);
//	                    String name = (String) obj.get("responseCertificate");  
//	                    System.out.println(name);

	                }
//	                System.out.println(line);
//	                string += line + "\n";
	            }
	            br.close();
	        }
	        catch (Exception e){
	            System.out.println(e.toString());
	        }
	 
	        // Writing
//	        try {
//	            FileWriter fw = new FileWriter (file);
//	            BufferedWriter bw = new BufferedWriter (fw);
//	            PrintWriter fileOut = new PrintWriter (bw);
//	                fileOut.println (string+"\n test of read and write !!");
//	            fileOut.close();
//	            System.out.println("the file " + file + " is created!");
//	        }
//	        catch (Exception e){
//	            System.out.println(e.toString());
//	        }
	    }
}
