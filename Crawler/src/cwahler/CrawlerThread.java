package cwahler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class CrawlerThread extends Thread {

	private PseudoQueue queue;
	private boolean verbose;
	private boolean stopping = false;
	private Crawler crawler;
	private Vector<String> writtenStrings;
	private Vector<String> writtenLinks;
	
	public CrawlerThread(Crawler crawler, String seedURL, boolean verbose) throws IOException {

		queue = new PseudoQueue(verbose);
		queue.add(new WebLocation(seedURL, verbose));		
		this.crawler = crawler;
		writtenStrings = new Vector<String>();
		writtenLinks = new Vector<String>();
		
	}
	
	
	public void run() {
		 
		while(queue.size() > 0 && !stopping) {
				System.out.println("Running. " + queue.size() + " items in queue.");
			WebLocation loc = queue.visit();

			try {
				loc.process(queue);
			} catch (IOException e) {
				if(verbose) {
					System.out.println("Error in CrawlerThread.run(): IOException. Reason: " + e.getMessage());
				}
			}
		}
		
		BufferedWriter linkWriter, emailWriter;
		try {
			linkWriter = new BufferedWriter(new FileWriter("Links.txt"));
			emailWriter = new BufferedWriter(new FileWriter("Emails.txt"));
			for(int i = 0; i < queue.realSize(); i++) {
				System.out.println("Stopping. " + (queue.realSize()-i) + " items in queue.");
				if(i == queue.size()) {
					linkWriter.write("v--------------------INCOMPLETE--------------------v\n");
				}
				queue.get(i).write(linkWriter, emailWriter, writtenStrings, writtenLinks);
			}
			linkWriter.close();
			emailWriter.close();
		} catch (IOException e) {
			if(verbose) {
				System.out.println("Error in CrawlerThread.run(): IOException. Reason: " + e.getMessage());
			}
		}
		
		

		System.out.println("Done! Output can be found in Output.txt");
	}


	public void startStopping() {
		stopping = true;
		
	}
}
