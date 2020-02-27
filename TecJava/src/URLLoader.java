import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLLoader {
	
	public void execute(String resource) throws Exception {
		URL url = new URL (resource);
		
		URLConnection connection = url.openConnection();
		InputStream input = connection.getInputStream();
		
		String destiny = System.currentTimeMillis() + ".html";
		FileOutputStream output = new FileOutputStream(destiny);
		
		int i;
		while((i = input.read()) != -1) {
			output.write(i);
		}
		input.close();
		output.close();
	}
	
	public static void main(String[]agrs) throws Exception{
		String[] urls = {"http://uol.com.br",
				"http://uni7.edu.br",
				"http://stackoverflow.com",
				"http://gi.globo.com",
				"http://r7.com/"};
		URLLoader loader = new URLLoader();
		
		long begin = System.currentTimeMillis();
		for(String url : urls) {
			loader.execute(url);
		}
		long end = System.currentTimeMillis();
		
		long diff = end - begin;
		System.out.println(diff);
	}
}
