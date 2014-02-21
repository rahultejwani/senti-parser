package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import rt.textbean.dev.propertyBean;

public class RawTextBean {
	static String sourcePath = (new propertyBean().getSourcePath());
	private BufferedReader br;
	private ArrayList<String> lines = new ArrayList<>();
	private ArrayList<Integer> classifier = new ArrayList<>();

	/*
	 * Loads the entire file to memory when the constructor is called
	 */
	public RawTextBean() throws IOException
	{
		br = new BufferedReader(new FileReader(sourcePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			classifier.add(Character.getNumericValue(line.charAt(0)));
			//	System.out.println(Character.getNumericValue(line.charAt(0)));
			line = line.substring(2);

			lines.add(line);
			//count++;
		}
	}
	public ArrayList<String> getLines()
	{
		return this.lines;
	}
	public ArrayList<Integer> getClassifier()
	{
		return this.classifier;
	}

}
