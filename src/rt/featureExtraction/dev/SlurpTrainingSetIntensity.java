package rt.featureExtraction.dev;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import rt.textbean.dev.propertyBean;

public class SlurpTrainingSetIntensity {

	static final String sourcePath = (new propertyBean().getIntensityTraining());
	private BufferedReader br;
	private ArrayList<String> lines = new ArrayList<>();
	private ArrayList<Integer> Intensity = new ArrayList<>();
	/*
	 * Loads the entire file to memory when the constructor is called
	 */
	public SlurpTrainingSetIntensity() throws IOException
	{
		//System.out.println(sourcePath);
		br = new BufferedReader(new FileReader(sourcePath));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] row = line.split("~~#~");
		//	row[0] = row[0].replaceAll("[^a-zA-Z ]", " ").toLowerCase();
			if(row.length==2)
			{
				lines.add(row[0]);

				Intensity.add(Integer.parseInt(row[1]));
			}
		}
		br.close();
		
	}

	
public ArrayList<String> getLines()
{
	return this.lines;
}
public ArrayList<Integer> getIntensityList()
{
	return this.Intensity;
}


}
