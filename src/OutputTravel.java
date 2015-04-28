import java.util.Date;

/*
 * Type of the object created by the Reducer
 */
public class OutputTravel {
	public long departureId;
	public long arrivalId;
	public String provider;
	public double[] series = new double[31];
	
	public OutputTravel(TravelId id){
		this.departureId = id.departureId;
		this.arrivalId = id.arrivalId;
		this.provider = id.provider;
	}
	
	
	public void addOccurrence(long timestamp, double value){
		@SuppressWarnings("deprecation")
		int dayOfMonth = new Date(timestamp).getDay();
		series[dayOfMonth-1]+=value;
	}
	
}
