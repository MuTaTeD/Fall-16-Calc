package pk.edu.ucp.fall16_mad_c.MapAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by awais on 25/01/2017.
 */

public class MapResponse
{
	@SerializedName("status")
	public String status;

	@SerializedName("results")
	public ArrayList<MapResult> results;

	public MapResponse()
	{
		results = new ArrayList<MapResult>();
	}
}

