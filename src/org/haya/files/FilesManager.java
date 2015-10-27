package org.haya.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author jlcr
 *
 */
public abstract class FilesManager {

	/**
	 * @param source File to be copied.
	 * @param dest New copy of source File.
	 */
	public static void copy(File source, File dest) {
		
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
	    } catch (FileNotFoundException e) {	    	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
	        try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }	
	}
		
	/**
	 * 
	 * @param resource
	 * @param dest
	 * @throws IOException
	 */
	public void getFileFromURL(String resource, String dest) throws IOException {
		
		URL url = new URL(resource);
		InputStream is = null;
		FileOutputStream fos = null;

		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");

		is = httpcon.getInputStream();
		fos = new FileOutputStream(dest);		
		
		byte[] array = new byte[1000];
		int leido = is.read(array);
		while (leido > 0) {
			fos.write(array, 0, leido);
			leido = is.read(array);
		}

		if ( is != null ) {
			is.close();
		}

		if ( fos != null ) {
			fos.close();
		}		
	}
	
}
