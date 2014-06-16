package com.kikolski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFinder {
	private static final String PATTERN = 
			"[a-z0-9-\\+]+(\\.[_a-z0-9-]+)*@[a-z0-9]+(\\.[a-z0-9]+)*(\\.[a-z]+)";
	
	private Pattern pattern = Pattern.compile(PATTERN);
	private Matcher matcher;
	
	public List<String> find(final String address) throws IOException {
		Set<String> result = new HashSet<>();
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String readedLine = "";
		
		while ((readedLine = reader.readLine()) != null) {
			matcher = pattern.matcher(readedLine.toLowerCase());
			while(matcher.find())
				result.add(readedLine.substring(matcher.start(), matcher.end()));
		}
		
		reader.close();
		return new ArrayList<String>(result);
	}
	
	public void test(String tekst) {
		matcher = pattern.matcher(tekst);
		System.out.println(matcher.matches());
	}
	
	public static void main(String[] params) {
		String main = "warn..a@domena.pl";
		EmailFinder finder = new EmailFinder();
		finder.test(main);
	}
}
