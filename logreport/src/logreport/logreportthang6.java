package logreport;

import java.util.*;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class logreportthang6 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		String file = "./Log/thang/tokenws.log.2020-06-";
		String stfilter1 = "DatabaseImpl";
		String stfilter2 = "[INFO] [vn.mobile_id.raconnector.RAConnectorImpl] [getCertificate]";
		String sn = "";
		String err = "TokenWS";
		int i = 1;
		String serial = "";
		Scanner inScanner = new Scanner(System.in);

		// inScanner.useDelimiter("\n");
		System.out.print("Nhập ngày sinh file log: ");
		String inFile = inScanner.next();
		// String directoryName = file.concat(this.getClassName());
		File directory = new File("./Log/thang/new/" + inFile + "/");
		if (!directory.exists()) {
			directory.mkdir();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
			// FileWriter myWriter = new FileWriter("./Log/thang4/"+inFile+"/"+i+++"_"
			// +serial);
		}
		System.out.println(directory);
		// Gson gson = new Gson();
		// JSONParser parser = new JSONParser();
		// Reading
		try {
			InputStream ips = new FileInputStream(file + inFile);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String line;
			List<String> listSerial = new ArrayList<String>();
			int countSerial = 0;
			int filter2counter = 0;
			while ((line = br.readLine()) != null) {

				if (!line.contains(stfilter1)) {
					int index = line.indexOf("54010C");
					int length = 32;

					if (index > 0) {
						serial = line.substring(index, index + length);
//						System.out.println(serial);
						// Check list serial
						for (String filterSerial : listSerial) {
							if (!filterSerial.equals(serial))
								countSerial++;
						}
						if (countSerial == listSerial.size()) {
							listSerial.add(serial);
							// System.out.println("Serial: "+serial);
						}
						countSerial = 0;

					}

					if (line.contains(stfilter2) || line.contains("[toPdf] Feature:")) {
						filter2counter++;

						// line = "";
						// System.out.println(line);

						// System.out.println(line);
//	                		 FileWriter myWriter = new FileWriter(directory+"/"+i+++"_" +serial);
//	                		
//	                	     myWriter.write(sn.trim());
//	                	     myWriter.close();
//	                	     System.out.println("\nSuccessfully wrote to the file."+i);

					}
					if (line.contains("</OrderNumber></GCN>")) {
						sn += line + "\n";
						filter2counter = 0;

//						 System.out.println(line);
						FileWriter myWriter = new FileWriter(directory + "/" + serial);

						myWriter.write(sn.trim());
						myWriter.close();
						System.out.println("\nSuccessfully wrote to the file." + i++);
						sn = "";

					}
					if (filter2counter > 0) {
						sn += line + "\n";
					}

				} else {
					filter2counter = 0;
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}
