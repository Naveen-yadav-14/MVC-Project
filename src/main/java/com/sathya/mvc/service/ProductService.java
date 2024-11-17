package com.sathya.mvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.models.ProductModel;
import com.sathya.mvc.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public void saveForm(ProductModel productModel)
	{
		double price = productModel.getProPrice();
		String category = productModel.getProCategory();
		double disPrice = 0.0;
		
		switch(category) {
		case "Mobile":
			disPrice = price*0.1;
			break;
		case "Computer":
			disPrice = price*0.2;
			break;
		case "CPU":
			disPrice = price*0.15;
			break;
		}
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProName(productModel.getProName());
		productEntity.setProPrice(productModel.getProPrice());
		productEntity.setDisPrice(disPrice);
		productEntity.setProBrand(productModel.getProBrand());
		productEntity.setProDescription(productModel.getProDescription());
		productEntity.setProCategory(productModel.getProCategory());
		productEntity.setCreatedAt(LocalDate.now());
		productEntity.setCreatedBy(System.getProperty("user.name"));
		
		productRepository.save(productEntity);
		
		
	}
	
	public List<ProductEntity> getAllProducts()
	{
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}
	
	public Optional<ProductEntity> getProduct(Long proId)
	{
		Optional<ProductEntity>product = productRepository.findById(proId);
		return product;
	}
	public void deleteProductById(@RequestParam Long proId)
	{
		productRepository.deleteById(proId);
	}
	public ProductModel editProductById(@RequestParam Long id)
	{
		ProductEntity productEntity = productRepository.findById(id).get();
		
		ProductModel productModel = new ProductModel();
		productModel.setProName(productEntity.getProName());
		productModel.setProPrice(productEntity.getProPrice());
		productModel.setProCategory(productEntity.getProCategory());
		productModel.setProBrand(productEntity.getProBrand());
		productModel.setProDescription(productEntity.getProDescription());
		
		return productModel;
	}
	public void updateProduct(Long proId,ProductModel productModel)
	{
		Optional<ProductEntity> existingProduct = productRepository.findById(proId);
		if(existingProduct.isPresent())
		{
			double price = productModel.getProPrice();
			String category = productModel.getProCategory();
			double disPrice = 0.0;
			
			switch(category) {
			case "Mobile":
				disPrice = price*0.1;
				break;
			case "Computer":
				disPrice = price*0.2;
				break;
			case "CPU":
				disPrice = price*0.15;
				break;
			}
			
		ProductEntity productToUpdate = existingProduct.get();
		productToUpdate.setProName(productModel.getProName());
		productToUpdate.setProPrice(productModel.getProPrice());
		productToUpdate.setDisPrice(disPrice);
		productToUpdate.setProBrand(productModel.getProBrand());
		productToUpdate.setProDescription(productModel.getProDescription());
		productToUpdate.setProCategory(productModel.getProCategory());
		
		
		productRepository.save(productToUpdate);
		}
	}

}
