package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import rt.textbean.dev.propertyBean;

/**
 * Class for loading the training set in memory
 * @author rahultejwani
 *
 */

public class SlurpTrainingSet {
	static String sourcePath = (new propertyBean().getSourcePath());
	private BufferedReader br;
	private ArrayList<String> lines = new ArrayList<>();
	private ArrayList<Integer> polarity = new ArrayList<>();
	private ArrayList<Integer> intensity = new ArrayList<>();

	/*
	 * Loads the entire file to memory when the constructor is called
	 */
	public SlurpTrainingSet() throws IOException
	{
		br = new BufferedReader(new FileReader(sourcePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] row = line.split("\\t");
			lines.add(row[0]);
			polarity.add(Integer.parseInt(row[1]));
			intensity.add(Integer.parseInt(row[2]));
		}
	}
	public ArrayList<String> getLines()
	{
		return this.lines;
	}
	public ArrayList<Integer> getPolarityList()
	{
		return this.polarity;
	}
	public ArrayList<Integer> getIntensityList()
	{
		return this.intensity;
	}

}
