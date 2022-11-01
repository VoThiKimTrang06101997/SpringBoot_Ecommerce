package com.trang.ecommerce_library.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trang.ecommerce_library.model.City;
import com.trang.ecommerce_library.repository.CityRepository;
import com.trang.ecommerce_library.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<City> getAll() {
		return cityRepository.findAll();
	}

}
