/*
 * Type of the items created by the Mapper
 */
public class InputTravel {
	public long departureId;
	public long arrivalId;
	public String provider;
	public long timestamp;
	public double value;
	
	public InputTravel(long dId, long aId, String pvd, long t, double v){
		this.departureId = dId;
		this.arrivalId = aId;
		this.provider = pvd;
		this.timestamp = t;
		this.value  = v;		
	}
	
	public TravelId getTravelId(){
		return new TravelId(departureId,arrivalId,provider);
	}
}
