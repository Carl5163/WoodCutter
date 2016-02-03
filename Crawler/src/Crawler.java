import java.io.*;

public class Crawler {

	public static String SEED_URL = "http://www2.fairmontstate.edu/users/tlarue/";
	public static final String FILE_NAME = "emailAddr.txt";
	public static final boolean VERBOSE = true;
	public static final boolean DEBUG = false;
	public static final int MAX_DEPTH = 2;
	public static final boolean SHOW_EMPTY_LINKS = false;
	

	private PseudoQueue queue;
	private boolean stopping = false;
	
	public static void main(String[] args) {
		try {
			new Crawler();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public Crawler() throws IOException {
		crawl();	
		System.out.println("Done! Output can be found in emailAddr.txtt");
	}
	
	public void crawl() throws IOException {
		
		System.out.println("Started.\nRunning...");

		queue = new PseudoQueue();
		queue.add(new WebLocation(SEED_URL, 0));
		
		while(queue.size() > 0 && !stopping) {

			if(VERBOSE) {
				System.out.println("Running... " + (queue.realSize()-queue.size()) + "|" + queue.size() + " items in queue.");
			}
			WebLocation loc = queue.deQueue();
	
			try {
				loc.process(queue);
			} catch (IOException e) {
				if(DEBUG) {
					System.out.println("Error in CrawlerThread.run(): IOException. Reason: " + e.getMessage());
				}
			}
	}
	
	BufferedWriter writer;
	try {
		writer = new BufferedWriter(new FileWriter("Emails.txt"));
		for(int i = 0; i < queue.realSize(); i++) {
			queue.get(i).write(writer);
		}
		writer.close();
	} catch (IOException e) {
		if(DEBUG) {
			System.out.println("Error in CrawlerThread.run(): IOException. Reason: " + e.getMessage());
		}
	}
	
	}
	
}
