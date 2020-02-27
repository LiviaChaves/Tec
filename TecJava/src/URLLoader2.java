import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

class Worker extends Thread {
	private URLLoader2 loader;
	private String resource;
	
    public Worker(URLLoader2 loader, String resource) {
    	this.loader = loader;
    	this.resource = resource;
    }

@Override
public void run() {
	try {
		loader.execute(resource);
	} catch(Exception e) {
		e.printStackTrace();
	   }
	}
}

public class URLLoader2 {
	
	public void execute(String resource) throws Exception {
		URL url = new URL(resource);
		
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
		String[] urls = {"http://uol.com.br"};
		
		URLLoader2 loader = new URLLoader2();
		
		long begin = System.currentTimeMillis();
		for(String url : urls) {
			new Worker(loader,url).start();
		}
		
		long end = System.currentTimeMillis();
		
		long diff = end - begin;
		System.out.println(diff);
	}
}
