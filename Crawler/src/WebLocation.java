import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import javax.swing.text.*;
import javax.swing.text.html.parser.*;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.*;

public class WebLocation extends ParserCallback{
	
	private URL url;
	private ParserDelegator parser;
	private PseudoQueue queue;
	private Vector<String> emails;
	private Pattern pattern;
	private int depth;

	//******************************************************************
	public WebLocation(String url, int depth) throws IOException {
		this.url = new URL(url.trim());
		this.depth = depth;
		emails = new Vector<String>();
		parser = new ParserDelegator();
		pattern = Pattern.compile("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
	}
	//******************************************************************
	
	//******************************************************************
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet t, int pos) {
		if(tag.toString().equalsIgnoreCase("a")) {
			String ref = (String) t.getAttribute(HTML.Attribute.HREF);
			if(ref != null) {
				try {
					if(depth < Crawler.MAX_DEPTH) {
						queue.add(new WebLocation(ref, depth+1));
						if(Crawler.DEBUG) {
							System.out.println("Link added to Queue. (MyDepth: " + depth + ")");
						}
					} else {
						if(Crawler.DEBUG) {
							System.out.println("Did not add new link. Maximum depth reached.");
						}
					}
				} catch (IOException e) {
					if(Crawler.DEBUG) {
						System.out.println("Error in WebLocation.handleSimpleTag(): IOException. Reason: " + e.getMessage());
					}
				}
				
			}
		}
	}
	//******************************************************************

	//******************************************************************
	//This method is called once for every web page. It first creates a 
	//reader from the URL given when this instance was instantiated. 
	//It then finds calls findEmails(), and begins parsing the HTML.
	public void process(PseudoQueue pseudoQueue) throws IOException{

		queue = pseudoQueue;
		Reader reader = new InputStreamReader(url.openStream());	
		findEmails(reader);
		reader = new InputStreamReader(url.openStream());
		parser.parse(reader, this, true);		
		
	}
	//******************************************************************

	//******************************************************************
	//This method creates a buffered reader from a given reader and 
	//searches for all emails on the page. It then adds them to the 
	public void findEmails(Reader reader) throws IOException {
		
		BufferedReader br = new BufferedReader(reader);
		
		String line = br.readLine(); 
		Matcher matcher;
		while(line != null) {
			matcher = pattern.matcher(line);
			while(matcher.find()) {
				String email = line.substring(matcher.start(), matcher.end()); 
				if(Crawler.VERBOSE) {
					System.out.println("Email found: " + email);
				}
				if(!stringContains(emails, email)) {
					emails.add(email);
				}
			}
			line = br.readLine();
		}	
		
	}
	//******************************************************************
	
	//******************************************************************
	//This method checks a vector of strings to see if a string is 
	//contained in the vector. It uses String.equals() so the actual 
	//strings are compared instead of pointers to String.	
	public boolean stringContains(Vector<String> vector, String item) {
		for(String s : vector) {
			if(s.toString().trim().equalsIgnoreCase(item.toString())) {
				return true;
			}
		}
		return false;
	}
	//******************************************************************
	
	//******************************************************************
	//This method writes the Weblocation to a file. It ensures that links
	//containing no emails are not printed IF the SHOW_EMPTY_LINKS flag is set.
	public void write(BufferedWriter writer) throws IOException {
		
		if(emails.size() != 0 || Crawler.SHOW_EMPTY_LINKS) {
			writer.write(url.toString() + "\n");
			for(String s : emails) {
				writer.write(s + "\n");	
			}
		
			writer.write("************************************************\n");
		}
				
	}
	//******************************************************************
	
	//******************************************************************
	public String toString() {
		return url.toString();
	}
	//******************************************************************
}
