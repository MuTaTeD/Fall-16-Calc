package pk.edu.ucp.fall16_mad_c.MapAPI;

import com.google.gson.annotations.SerializedName;

public class MapLocation
{
	@SerializedName("lat")
	public double latitude;

	@SerializedName("lng")
	public double longitude;
}
