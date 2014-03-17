package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
 * @Author rahultejwani
 */

public class lexiconAnalysis {
	
	private HashMap<String, WordInfo> dictionary;
	Double synsetScore;
	@SuppressWarnings("resource")
	public lexiconAnalysis() 
	{
		dictionary = new HashMap<String, WordInfo>();
		HashMap<String, HashMap<Integer, Double>> tempDictionary = new HashMap<String, HashMap<Integer, Double>>();
		BufferedReader csv = null;
		try
		{
			csv = new BufferedReader(new FileReader(new propertyBean().getSentiWordPath()));
			int lineNumber = 0;
			String line;
			while ((line = csv.readLine()) != null) {
				lineNumber++;
				if (!line.trim().startsWith("#")) {
					// We use tab separation
					String[] data = line.split("\t");
					String wordTypeMarker = data[0];
					if (data.length != 6) {
						throw new IllegalArgumentException(
								"Incorrect tabulation format in file, line: "
										+ lineNumber);
					}
					synsetScore = Double.parseDouble(data[2])
							- Double.parseDouble(data[3]);
					String[] synTermsSplit = data[4].split(" ");

					// Go through all terms of current synset.
					for (String synTermSplit : synTermsSplit) {
						// Get synterm and synterm rank
						String[] synTermAndRank = synTermSplit.split("#");
						String synTerm = synTermAndRank[0] + "#"
								+ wordTypeMarker;

						int synTermRank = Integer.parseInt(synTermAndRank[1]);
						// What we get here is a map of the type:
						// term -> {score of synset#1, score of synset#2...}

						// Add map to term if it doesn't have one
						if (!tempDictionary.containsKey(synTerm)) {
							tempDictionary.put(synTerm,
									new HashMap<Integer, Double>());
						}

						// Add synset link to synterm
						tempDictionary.get(synTerm).put(synTermRank,
								synsetScore);
					}
				}
			}
			for (Map.Entry<String, HashMap<Integer, Double>> entry : tempDictionary
					.entrySet()) {
				String word = entry.getKey();
				Map<Integer, Double> synSetScoreMap = entry.getValue();

				// Calculate weighted average. Weigh the synsets according to
				// their rank.
				// Score= 1/2*first + 1/3*second + 1/4*third ..... etc.
				// Sum = 1/1 + 1/2 + 1/3 ...
				double score = 0.0;
				double sum = 0.0;
				for (Map.Entry<Integer, Double> setScore : synSetScoreMap
						.entrySet()) {
					score += setScore.getValue() / (double) setScore.getKey();
					sum += 1.0 / (double) setScore.getKey();
				}
				score /= sum;
				//putting the average score of words with multiple meanings
				
				
					dictionary.put(word, new WordInfo('n', score));
				
			}
			csv.close();
			//Trying to print out the map to a text file
			BufferedWriter bufferedWriter = new BufferedWriter(
					new FileWriter("/home/rahul/Development/SentimentAnalysis/check1_01.csv"));
			for(Map.Entry<String, WordInfo> entry: dictionary.entrySet())
			{
				
				bufferedWriter.write(entry.getKey());
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(entry.getValue().getScore()));
				bufferedWriter.write(",");
				bufferedWriter.write(String.valueOf(entry.getValue().getPos()));
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
	public HashMap<String, WordInfo> getDictionary()
	{
		return this.dictionary;
	}
	public static void main(String[] args) {
		lexiconAnalysis la = new lexiconAnalysis();
	}

}
