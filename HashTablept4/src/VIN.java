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
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}
	
	@Override
	public String toString()
	{
		return vehicleId;
	}
}
