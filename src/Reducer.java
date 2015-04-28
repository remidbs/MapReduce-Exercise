import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;


/*
 * The Reducer receives objects of type InputTravel. At each new reception, 
 * it updates the hashmap to increase the value of the field series  of the corresponding
 * object of type OutputTravel
 * In the hashmap, items are stored by their identifier which consists in a object with fields departureId,
 * arriveId and provider.
 * 
 * 
 * */

public class Reducer extends Thread{
    private final LinkedBlockingQueue<InputTravel> in;
    private final HashMap<TravelId, OutputTravel> outputTable;
    int processedItems;

    Reducer(LinkedBlockingQueue<InputTravel> in, HashMap<TravelId, OutputTravel> out){
    	this.in = in;
    	outputTable = out;
    	processedItems = 0;
    }
    
    void printStatus(){
    	System.out.println("Reducer : " + processedItems + " items processed");
    }
	
    @Override
    public void run(){
    	while(true) {
            try {
                InputTravel it = in.take();
                if (it.arrivalId == -1) return;//All items processed
                if(outputTable.containsKey(it.getTravelId())){
                   outputTable.get(it.getTravelId()).addOccurrence(it.timestamp, it.value);
                }else{
                	OutputTravel ot = new OutputTravel(it.getTravelId());
                	ot.addOccurrence(it.timestamp, it.value);
                	outputTable.put(it.getTravelId(), ot);
                }

                if (Thread.interrupted()) return;
                processedItems++;
            } catch (InterruptedException e) { return; }
        }
    }

}