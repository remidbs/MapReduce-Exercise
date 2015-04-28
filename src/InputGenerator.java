import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
/*
 * Generated an input of the chosen size
 */

public class InputGenerator {
	public static void main(String[] args) throws IOException{
		int numberOfItems = 1000000;
		FileWriter file = new FileWriter("input-big.json");
		Gson g = new Gson();
		String[] providers = {"Renfe", "Eurolines", "City2City", "DeinBus", "Vueling"};
		
		
		for(int i = 0; i < numberOfItems; i++){
			file.write(g.toJson(new InputTravel(
					(long) (50*Math.random()),
					(long) (50*Math.random()),
					providers[(int) (5*Math.random())],
					1430431200+(long) (2678400*Math.random()), //the timestamp of an instant between 1st may 2015 and 1st june 2015
					100*Math.random()
					)));
			if(i != numberOfItems-1){
				file.write(",\n");
			}
		}
		file.close();
	}
	
}
