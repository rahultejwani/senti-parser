package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import rt.textbean.dev.propertyBean;

/**
 * @Author rahultejwani
 */
public class DictionaryBean{
	private HashMap<String, Double> Dictionary = new HashMap<>();
	private HashMap<String, HashMap<String, Integer>> TaggedDictionary = new HashMap<>();
	public DictionaryBean()
	{
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(new propertyBean().getLexiconPath()));
			System.out.println("Loading the Sentiword Dictionary");
			String line;
			while((line = br.readLine()) != null)
			{
				String [] row = line.split("\\t");
				Dictionary.put(row[0], Double.parseDouble(row[1]));
			}
			br.close();
			System.out.println("Sentiword Dictionary Load Complete :)");
			System.out.println("Loading tagged Dictionary");
			br = new BufferedReader(new FileReader(new propertyBean().getTaggedDictionaryPath()));
			br.readLine();
			br.readLine();
			br.readLine();
			while((line = br.readLine()) != null)
			{
				String [] row = line.split(",");
				String key = row[0].toLowerCase();
				//System.out.println(row[0]);
				//System.out.println(row.length);
				HashMap<String,Integer> f_list= new HashMap<>();
				for (int i = 2; i < 180; i++) {
					if(!row[i].equals("")){
						f_list.put(row[i], i);
					//	System.out.println(row[i]);
					}
				}
				TaggedDictionary.put(key, f_list);
			}
			System.out.println("Tagged Dictionary Load Complete :)");

		} catch (FileNotFoundException e) {

			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {

			System.out.println("IO exception");
			e.printStackTrace();
		}
	}


	public HashMap<String, Double> getDictionary() {
		return this.Dictionary;
	}


	public HashMap<String, HashMap<String, Integer>> getTaggedDictionary() {
		return TaggedDictionary;
	}



}
