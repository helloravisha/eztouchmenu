package com.eztouchdine.EZTouchMenu;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import junit.framework.TestCase;

public class TestCategory extends TestCase {
	
	 Category category = new Category("ip26hxunoh.execute-api.us-east-1.amazonaws.com/dev");
	
	 @Test
	   public void testCategory() {
	   String categoryMessage =   category.testCategory("categories");
	        assertNotNull(categoryMessage);
	   }

}
