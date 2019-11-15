public class VIN
{
	private String vehicleId;
	
	public VIN()
	{
		vehicleId = null;
	}
	
	public VIN(String s)
	{
		vehicleId = s;
	}
	
	public VIN(VIN v)
	{
		vehicleId = v.getId();
	}
	
	public String getId()
	{
		return vehicleId;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return vehicleId.equals( ((VIN) obj).vehicleId);
	}
	
	@Override
	public int hashCode()
	{
		
		int location = vehicleId.substring(0,1).hashCode();
		int manufacturer = vehicleId.substring(1,3).hashCode();
		int description = vehicleId.substring(3,8).hashCode();
		int securityCode = vehicleId.substring(8,9).hashCode();
		int modelYear = vehicleId.substring(9,10).hashCode();
		int plant = vehicleId.substring(10,11).hashCode();
		int serial = Integer.parseInt(vehicleId.substring(11));
		
		

		return location + manufacturer + description + securityCode + modelYear + plant + serial;
	
	
	
	}
	
	@Override
	public String toString()
	{
		return vehicleId;
	}
}
