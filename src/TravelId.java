/*
 * Stores the information that identifies an OutputTravel
 */
public class TravelId {
	public long departureId;
	public long arrivalId;
	public String provider;

	
	public TravelId(long dId, long aId, String pvd){
		this.departureId = dId;
		this.arrivalId = aId;
		this.provider = pvd;
	}
	
}
