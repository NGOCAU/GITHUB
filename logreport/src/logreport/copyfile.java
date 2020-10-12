package logreport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class copyfile {
	public static void main(String[] args) throws IOException {
		try {
			File myObj = new File("./Log/thang1.txt");
			Scanner myReader = new Scanner(myObj);
//			List<String> results = new ArrayList<String>();
			Map<String, File> results = new HashMap<String, File>();
			File[] files = new File("./Log/thang1done").listFiles();
			// If this pathname does not denote a directory, then listFiles() returns null.
			for (File file : files) {
				if (file.isFile()) {
					results.put(file.getName(), file);
				} else {
					File[] subFiles = file.listFiles();
					for (File subFile : subFiles) {
						System.out.println(subFile);
						if (subFile.isFile()) {
							results.put(subFile.getName(), subFile);
						}
					}
				}
			}
			int count = 0, flag = 0;
			System.out.println(results.size());

			while (myReader.hasNextLine()) {
				String serial = myReader.nextLine();

				for (Map.Entry<String, File> entry : results.entrySet()) {
					String fileName = entry.getKey();
//				    Label value = entry.getValue();
					if (serial.contains(fileName)) {
						//System.out.println(serial);
						System.out.println(fileName);
						File value = entry.getValue();
						copyFileUsingStream(value, new File("./Log/thang1danhso/" + serial));

						count++;

					} else {
						flag++;
					}

				}
				System.out.println("flag: " + flag);
				if (flag == results.size())
					System.out.println("Serial abc: " + serial);
				flag = 0;

			}
			System.out.println(count);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}
}
