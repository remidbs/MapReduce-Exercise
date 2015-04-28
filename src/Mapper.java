import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;

/*
 * The Mapper reads the input file line per line and objects of type InputTravel 
 * that she gives to the Reducer through a blocking queue.
 * 
 * 
 * */


class Mapper extends Thread{
	BufferedReader br;
    private final LinkedBlockingQueue<InputTravel> result;
    int processedItems;

    Mapper(BufferedReader b, LinkedBlockingQueue<InputTravel> r){
    	br = b;
    	result = r;
    	processedItems = 0;
    }
    
    void printStatus(){
    	System.out.println("Mapper : " + processedItems + " items processed");
    }
    
	@Override
    public void run(){
		while(true){
	    	String line = null;
	        try { line = this.br.readLine(); }
	        catch (IOException e) { }
	        
	        
	        if(line==null){//End of file was reached
	        	result.add(new InputTravel(-1,-1,"",0,0));//this instance stands for end of file
	        	return;
	        }
	        
	    	if(line.charAt(line.length()-1) == ',')
	    		line = line.substring(0, line.length()-1); //removes the comma at the end of the line if there is one
	    	Gson g = new Gson();
	    	InputTravel it = g.fromJson(line, InputTravel.class);
	    	result.add(it);
	    	processedItems++;
		}
    }
}