package rt.textbean.dev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import rt.textbean.dev.propertyBean;
public class SentiWordBean {
	private HashMap<String, val> Dictionary = new HashMap<>();
	public SentiWordBean()
	{
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new propertyBean().getLexiconPath()));
			String line;
			while((line = br.readLine()) != null)
			{
				String [] row = line.split(",");
				System.out.println(row[0]);
				System.out.println(row[1]);
				System.out.println(row[2]);
				val v = new val(row[1].charAt(0),Double.parseDouble(row[2]));
				Dictionary.put(row[0], v);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO exception");
			e.printStackTrace();
		}
		
	}
	public HashMap<String, val> getDictionary() {
		return Dictionary;
	}
	public static void main(String[] args) {
//		SentiWordBean swb = new SentiWordBean();
//		HashMap<String, val> dict =swb.getDictionary();
//		Set<String> set = dict.keySet();
//		for (String string : set) {
//			System.out.println(string);
//		}
	}
	
}
