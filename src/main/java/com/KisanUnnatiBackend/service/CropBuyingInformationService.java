package com.KisanUnnatiBackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KisanUnnatiBackend.repo.CropSellerListingRepository;


@Service
public class CropBuyingInformationService{
	
//	@Autowired
//	private  CropSellerListingRepository cropSellerListingRepository;
//
//	public List<Object[]> buyingCropCityVice(String state, String district, String city) {
//		System.out.println(".............city..............");
//		List<Object[]> buyingCropCityVice = cropSellerListingRepository.buyingCropCityVice(city,district,state);
//		if(buyingCropCityVice!=null) {
//			for (Object[] row : buyingCropCityVice) {
//			    System.out.println("---- New Row ----");
//			    for (int i = 0; i < row.length; i++) {
//			        System.out.println("Column " + i + ": " + row[i]);
//			    }
//			}
//		}
//		return buyingCropCityVice;	
//	}
//	
//	public List<Object[]> buyingCropDistrictVice(String state, String district, String city) {
//		System.out.println(".............district..............");
//		List<Object[]> buyingCropDistrictVice = cropSellerListingRepository.buyingCropDistrictVice(city,district,state);
//		if(buyingCropDistrictVice!=null) {
//			for (Object[] row : buyingCropDistrictVice) {
//			    System.out.println("---- New Row ----");
//			    for (int i = 0; i < row.length; i++) {
//			        System.out.println("Column " + i + ": " + row[i]);
//			    }
//			}
//		}
//		return buyingCropDistrictVice;
//			
//	}
//	
//	public List<Object[]> buyingCropStateVice (String state, String district, String city) {
//		System.out.println(".............state..............");
//		List<Object[]> buyingCropStateVice = cropSellerListingRepository.buyingCropStateVice(city,district,state);
//		if(buyingCropStateVice!=null) {
//			for (Object[] row : buyingCropStateVice) {
//			    System.out.println("---- New Row ----");
//			    for (int i = 0; i < row.length; i++) {
//			        System.out.println("Column " + i + ": " + row[i]);
//			    }
//			}
//		}
//		return buyingCropStateVice;
//		
//	}
}
