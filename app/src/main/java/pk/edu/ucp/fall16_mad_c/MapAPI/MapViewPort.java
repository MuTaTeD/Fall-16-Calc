package pk.edu.ucp.fall16_mad_c.MapAPI;

import com.google.gson.annotations.SerializedName;

public class MapViewPort
{
	@SerializedName("northeast")
	public MapLocation northEast;

	@SerializedName("southwest")
	public MapLocation southwest;
}
