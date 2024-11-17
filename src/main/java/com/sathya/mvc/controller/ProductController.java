package com.sathya.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.mvc.entity.ProductEntity;
import com.sathya.mvc.models.ProductModel;
import com.sathya.mvc.service.ProductService;

import jakarta.validation.Valid;


@Controller
public class ProductController {
	
	
	@Autowired
	ProductService productService;
	@GetMapping("/getproductform")
	public String getFrom(Model model)
	{
		ProductModel productModel = new ProductModel();
		  model.addAttribute("productModel", productModel );
		  model.addAttribute("page","getproductform" );
		return"index";
	}
	
	
	@PostMapping("/addProduct")
	public String saveProduct(@Valid  ProductModel productModel, BindingResult bindingResult,Model model)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("productModel", productModel);
			model.addAttribute("page", "getproductform");
			return "index";
		}
		productService.saveForm(productModel);
		model.addAttribute("page", "addProduct");
		return "index";
	}
	
	
	@GetMapping("/getdata")
	public String getProduct(Model model)
	{
		List<ProductEntity>products = productService.getAllProducts();
		model.addAttribute("product", products);
		model.addAttribute("page", "getdata");
		return "index";
	}
	
	@GetMapping("/getproduct") 
	public String getById(Model model)
	{
		ProductModel productModel = new ProductModel();
		model.addAttribute("productId", productModel );
		model.addAttribute("page", "getproduct");
		return "index";
	}
	 @PostMapping("/searchProduct")
	    public String searchProduct(@RequestParam Long id, Model model) {
	        Optional<ProductEntity> productOpt = productService.getProduct(id); 
	        if (productOpt.isPresent()) {
	            model.addAttribute("product", productOpt.get()); 
	            return "searchProduct"; 
	        } else {
	            model.addAttribute("error", "Product not found with ID: " + id); 
	            return "one-product"; 
	        }
	 }
	
	 @GetMapping("/delete")
	    public String deleteProduct(@RequestParam("id") Long proId) {
	        productService.deleteProductById(proId);
	        return "redirect:/getdata";
	 }
	 @GetMapping("/edit")
	 public String editProduct(@RequestParam("id") Long proId,Model model)
	 {
		 ProductModel productModel = productService.editProductById(proId);
		        model.addAttribute("product", productModel); 
		        model.addAttribute("proId", proId);
		       return "edit-product";

	 }
	 @PostMapping("/update")
	  public String updateProduct(@RequestParam Long proId, @ModelAttribute ProductModel productModel) {
	        productService.updateProduct(proId, productModel);
	        return "redirect:/getdata";
	    }
	 @GetMapping("/aboutus")
	 public String aboutUs(Model model)
	 {
		 model.addAttribute("page", "aboutus");
		 return "index";
	 }
	 
	 @GetMapping("/contactus")
	 public String contactUs(Model model)
	 {
		 model.addAttribute("page", "contactus");
		 return "index";
	 }
	 
	 @GetMapping("/home")
	 public String index(Model model)
	 {
		 model.addAttribute("page", "home");
		 return "index";
	 }
	 
}
