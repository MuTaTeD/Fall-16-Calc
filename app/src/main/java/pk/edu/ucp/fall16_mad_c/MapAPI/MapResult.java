package pk.edu.ucp.fall16_mad_c.MapAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MapResult
{
	@SerializedName("address_components")
	public ArrayList<MapAddressComponents> addressComponents;

	@SerializedName("formatted_address")
	public String formattedAddress;

	@SerializedName("place_id")
	public String placeId;

	@SerializedName("types")
	public ArrayList<String> types;

	@SerializedName("geometry")
	public MapGeometery geometry;

	public MapResult()
	{
		addressComponents = new ArrayList<MapAddressComponents>();
		types = new ArrayList<String>();
	}
}
