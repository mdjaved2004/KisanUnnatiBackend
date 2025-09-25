package com.KisanUnnatiBackend.service;

public class StateNumberReturn {
	public int state(String state) {
		
		switch (state) {
		 case "Andhra Pradesh": return 1;
		 case "Arunachal Pradesh": return 2;
		 case "Assam": return 3;
		 case "Bihar": return 4;
		 case "Chhattisgarh": return 5;
		 case "Goa": return 6;
		 case "Gujarat": return 7;
		 case "Haryana": return 8;
		 case "Himachal Pradesh": return 9;
		 case "Jharkhand": return 10;
		 case "Karnataka": return 11;
		 case "Kerala": return 12;
		 case "Madhya Pradesh": return 13;
		 case "Maharashtra": return 14;
		 case "Manipur": return 15;
		 case "Meghalaya": return 16;
		 case "Mizoram": return 17;
		 case "Nagaland": return 18;
		 case "Odisha": return 19;
		 case "Punjab": return 20;
		 case "Rajasthan": return 21;
		 case "Sikkim": return 22;
		 case "Tamil Nadu": return 23;
		 case "Telangana": return 24;
		 case "Tripura": return 25;
		 case "Uttar Pradesh": return 26;
		 case "Uttarakhand": return 27;
		 case "West Bengal": return 28;
		 default: return 0; 
		}
	}
}
