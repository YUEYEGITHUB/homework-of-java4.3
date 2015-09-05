
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class InTimeLoadFile {

	static Properties properties = new Properties();
	static String fileName;


	 static class InTimeLoadFileThread implements Runnable{
		private Map<String,String> fileValue ;
		
		public void fileOutput(){
			Set<String> keys = properties.stringPropertyNames();
			fileValue = new HashMap<String,String>();
			for(String key : keys){
				String value = properties.getProperty(key);
				System.out.println(key +" is " + value);
				fileValue.put(key, value);
			}
		}

		public void updateFile(){
			Set<String> keys = properties.stringPropertyNames();
			for(String key : keys){
				String oldValue = fileValue.get(key);
				String newValue = properties.getProperty(key);
				if(!oldValue.equals(newValue)){
					System.out.println(key + " is changed to " + newValue);
					fileValue.put(key, newValue);
				}
			}
		}
		public void run() {
			try {
				properties.load(new FileInputStream(fileName));
				if (fileValue == null) {
					fileOutput();
				} else {
					updateFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	public static void main(String[] args) throws FileNotFoundException, IOException{
		if(args.length != 1){
			System.out.println("Can't find file");
			System.exit(1);
		}
		fileName = args[0];
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new InTimeLoadFileThread(), 0, 1000, TimeUnit.MILLISECONDS);
	}

}
