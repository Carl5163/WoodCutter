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
	
	public WebLocation(String url, int depth) throws IOException {
		this.url = new URL(url.trim());
		this.depth = depth;
		emails = new Vector<String>();
		pattern = Pattern.compile("[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
	}

	public URL getURL() {
		return url;
	}

	public void setURL(URL url) {
		this.url = url;
	}
	
	public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet t, int pos) {
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
	
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet t, int pos) {
		handleSimpleTag(tag,t,pos);
	}

	public void process(PseudoQueue pseudoQueue) throws IOException{

		queue = pseudoQueue;
		Reader reader = new InputStreamReader(url.openStream());	
		findEmails(reader);
		reader = new InputStreamReader(url.openStream());
		
		parser = new ParserDelegator();
		parser.parse(reader, this, true);		
		
	}
	
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
	
	public boolean stringContains(Vector<String> vector, String item) {
		for(String s : vector) {
			if(s.toString().trim().equalsIgnoreCase(item.toString())) {
				return true;
			}
		}
		return false;
	}

	public void write(BufferedWriter writer, boolean emptyURLs) throws IOException {
		
		
		
		if(emails.size() != 0) {
			writer.write(url.toString() + "\n");
			for(String s : emails) {
				writer.write(s + "\n");	
			}
		
			writer.write("************************************************\n");
		} else if(emptyURLs){
			writer.write(url.toString() + "\n");
			for(String s : emails) {
				writer.write(s + "\n");	
			}
		
			writer.write("************************************************\n");
		}
				
	}
	

	public String toString() {
		return url.toString();
	}
}
