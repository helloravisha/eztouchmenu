package com.eztouchdine.EZTouchMenu;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Items {
	
	
	private String hostRestURLPrefix = null;

	public Items(String hostName) {
		Client.create();
		this.hostRestURLPrefix = "https://" + hostName + "/";
	}

	  public String testCategory(String searchParam) {
		   Client client = Client.create();
		   
		WebResource webResource = client.resource(hostRestURLPrefix+searchParam);
			ClientResponse response = webResource.accept("application/json").get(
					ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("HTTP ERROR RESPONSE CODE : "
						+ response.getStatus());
			}
			String output = response.getEntity(String.class);
			System.out.println(output);
			return output;
	   }
	
	
	
	

}
