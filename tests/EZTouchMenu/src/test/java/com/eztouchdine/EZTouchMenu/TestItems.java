package com.eztouchdine.EZTouchMenu;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestItems {
	
	
	 Items category = new Items("ip26hxunoh.execute-api.us-east-1.amazonaws.com/dev");
		
	 @Test
	   public void testCategory() {
	   String itemsMessage =   category.testCategory("items");
	        assertNotNull(itemsMessage);
	   }

}
