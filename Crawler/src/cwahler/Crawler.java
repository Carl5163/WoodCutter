package cwahler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;


public class Crawler {


	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: Crawler <SeedURL>");
		} else {
			boolean verbose = false;
			if(args.length == 2) {
				if(args[1].equals("-v")) {
					verbose = true;
				}
			}
			
			try {
				System.out.println("Starting crawler with seed: " + args[0]);
				new Crawler(args[0], verbose);
			} catch(MalformedURLException e) {
				if(verbose) {
					System.out.println("Error in Crawler.main(): Malformed URL. Reason: " + e.getMessage());
				}
			} catch (IOException e) {
				if(verbose) {
					System.out.println("Error in Crawler.main(): IOException. Reason: " + e.getMessage());
				}
			}
		}
	}


	public Crawler(String seedURL, boolean verbose) throws IOException {

		
		CrawlerThread thread = new CrawlerThread(this, seedURL, verbose);
		
		
		System.out.println("Crawler ready, press any key to begin. \nOnce crawler has begun, press any key to quit.");
		System.in.read();
		thread.start();		
		System.in.skip(Long.MAX_VALUE);
		System.in.read();
		
		thread.startStopping();
		
	}
	
	
}
