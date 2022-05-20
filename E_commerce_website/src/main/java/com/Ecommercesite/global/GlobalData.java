package com.Ecommercesite.global;

import java.util.ArrayList;
import java.util.List;

import com.Ecommercesite.model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	static {
		cart= new ArrayList<Product>();
	}

}
