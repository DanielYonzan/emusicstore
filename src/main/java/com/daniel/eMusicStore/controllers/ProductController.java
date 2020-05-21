package com.daniel.eMusicStore.controllers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daniel.eMusicStore.entities.Product;
import com.daniel.eMusicStore.repos.ProductRepository;
import com.daniel.eMusicStore.services.ProductService;

@Controller
public class ProductController {
	
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;

	@RequestMapping("/createProduct")
	public String showCreateProduct() {
		return "/admin/create_product";
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	
	@RequestMapping(value="/addNewProduct",headers=("content-type=multipart/*"), method= RequestMethod.POST )
	public String AddNewProduct(@Valid @ModelAttribute("product") Product product,MultipartFile image , Model model, HttpServletRequest request) {
		
		Product products = new Product();
		
		try {
			products.setProductImage(image.getBytes());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	productService.saveProduct(product);	
//		Product products = productRepository.save(product);
//
//	          byte[] productImage = image.getBytes();
//		
	        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
//	     //   path = Paths.get(rootDirectory + "WEB-INF/resources/images/"+product.getProductId()+".png");
	        Path path = Paths.get(rootDirectory + "/templates/images/"+product.getProductImage()+".jpg/.png");
      
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/templates/images/")
				.toUriString();
		
		String msg = "Product saved with id: " + products.getId();
		model.addAttribute("msg", msg);
		model.addAttribute("products", products);
		LOGGER.info("Product added with id: ", products.getId());
		
		return "redirect:/listProduct";
	}

	
	
	@RequestMapping(value="/listProduct", method=RequestMethod.GET)
	public String showListProduct(Model model) {
	List<Product> products = productService.getProductList();
	model.addAttribute("products", products);
		System.out.println(productRepository);
		return "/admin/list_product";
	}
	
	@RequestMapping(value="/showUpdate/{id}", method = RequestMethod.GET)
	public String showUpdateProduct(@PathVariable("id") int id, ModelMap model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "/admin/update_product";
		
	}
	
	@RequestMapping(value="/updateProduct/{id}", method=RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, ModelMap model) {
		Product products = productService.updateProduct(product);
		model.addAttribute("products", products);
		return "redirect:/listProduct";
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") int id, ModelMap model) {

//		Product product = new Product();
//		products.setId(id);
		Product product = productService.getProductById(id);
		productService.deleteProduct(product);
		return "redirect:/listProduct";
	}
}
