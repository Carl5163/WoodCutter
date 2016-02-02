package cwahler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.DTD;
import javax.swing.text.html.parser.DocumentParser;

public class WebLocation extends ParserCallback{
	
	private URL url;
	private DocumentParser parser;
	private PseudoQueue queue;
	private boolean verbose;
	private Vector<String> links;
	private Vector<String> emails;
	private boolean stopping;
	private Pattern pattern;
	
	public WebLocation(String url, boolean verbose) throws IOException {
		this.verbose = verbose;
		this.url = new URL(url);
		links = new Vector<String>();
		emails = new Vector<String>();
		pattern = Pattern.compile("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})");
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
				//System.out.println("Link found (QueueSize = " + queue.size() + "): " + ref);
				try {
					queue.add(new WebLocation(ref, verbose));
					if(!stringContains(links, ref)) {
						links.add(ref);
					}
				} catch (IOException e) {
					if(verbose) {
						System.out.println("Error in WebLocation.handleSimpleTag(): IOException. Reason: " + e.getMessage());
					}
				}
			}
		}
	}
	
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet t, int pos) {
		handleSimpleTag(tag,t,pos);
	}
	
	
	public void handleEndTag(HTML.Tag t, int pos) {
	}
	
	public void handleError(String errorMsg, int pos) {
		if(verbose) {
			System.out.println("HTML Error at position(" + pos + "):" + errorMsg);
		}
	}
	public void handleComment(char[] data, int pos) {
		if(verbose) {
			System.out.println("Comment at position(" + pos + "): " + data.toString());
		}
	}

	public void process(PseudoQueue pseudoQueue) throws IOException {

		queue = pseudoQueue;
		Reader reader = new InputStreamReader(url.openStream());	
		findEmails(reader);
		reader = new InputStreamReader(url.openStream());
		
		parser = new DocumentParser(DTD.getDTD("html"));
		parser.parse(reader, this, false);
		
		
	}
	
	public void findEmails(Reader reader) throws IOException {
		
		BufferedReader br = new BufferedReader(reader);
		
		String line = br.readLine(); 
		Matcher matcher;
		while(line != null) {
			matcher = pattern.matcher(line);
			while(matcher.find()) {
				String email = line.substring(matcher.start(), matcher.end()); 
				if(verbose) {
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

	public void write(BufferedWriter linkWriter, BufferedWriter emailWriter, Vector<String> writtenLinks, Vector<String> writtenEmails) throws IOException {

		if(!stringContains(writtenLinks, url.toString())) {
			
			linkWriter.write(url.toString() + "\n");
			writtenLinks.add(url.toString());
		
			for(String s : links) {
				if(!stringContains(writtenLinks, s)) {
					linkWriter.write("    " + s + "\n");
					writtenLinks.addElement(s);
				}
			}
		}
		
		
		if(!stringContains(writtenEmails, url.toString()) && emails.size() > 0) {
			
			emailWriter.write(url.toString() + "\n");
			writtenEmails.add(url.toString());
			
			for(String s : emails) {
				if(!stringContains(writtenEmails, s)) {
					emailWriter.write("    " + s + "\n");
					writtenEmails.addElement(s);
				}
				
			}
		
		}
		
		
	}
	

	public String toString() {
		return url.toString();
	}
}
