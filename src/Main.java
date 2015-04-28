import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

/*
 * The class Main calls the Mapper and the Reducer to process data
 * Then it prints the results
 * 
 * */


public class Main {
	static void printResults(HashMap<TravelId, OutputTravel> outputTable, FileWriter fw) throws IOException{
		for(TravelId id : outputTable.keySet()){
			fw.write("{\n");
			fw.write("\t\"departureId\": " + id.departureId + ",\n");
			fw.write("\t\"arrivalId\": " + id.arrivalId + ",\n");
			fw.write("\t\"provider\": " + id.provider + ",\n");
			fw.write("\t\"series\": " + "[\n");
			double[] series = outputTable.get(id).series;
			for(int i = 0; i < 31; i++){
				fw.write("\t{\n");
				fw.write("\t\t\"dayOfMonth\": " + (i+1) + ",\n");
				fw.write("\t\t\"sumOfValues\": " + series[i] + "\n");
				fw.write("\t}\n");
			}
			fw.write("\t]\n");
			fw.write("},\n");
		}		
	}
	
	
	public static void main(String[] args) throws InterruptedException, IOException{
		BufferedReader br = new BufferedReader(new FileReader("input-big.json"));
		LinkedBlockingQueue<InputTravel> result = new LinkedBlockingQueue<InputTravel>();
		Mapper map = new Mapper(br, result);//One can use several thread as well;
		map.start();
		HashMap<TravelId, OutputTravel> outputTable = new HashMap<TravelId, OutputTravel>();
		Reducer red = new Reducer(result,outputTable);
		red.start();
		map.join();
		map.printStatus();
		red.join();
		red.printStatus();
		
		FileWriter fw = new FileWriter("output.json");
		System.out.println("writing...");
		printResults(outputTable, fw);
		fw.close();
	}
}
