package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.ProductDAO;


@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDAO productDAO;
	public void setProductDAO(ProductDAO productDAO) {
		System.out.println("::"+getClass()+".setProductDao Call....");
		this.productDAO = productDAO;
	}
	
	
	//Constructor
	public ProductServiceImpl(){
		
		System.out.println("::"+getClass()+"default Constructor Call...");
	}
	
	//Method
	@Override
	public void addProduct(Product product) throws Exception {
		productDAO.addProduct(product);
		
	}

	@Override
	public Product getProduct(String prodName) throws Exception {
		return productDAO.getProduct(prodName);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		List<Product> list = productDAO.getProductList(search);
		int totalCount = productDAO.getTotalCount(search);
		
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("list",list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
		
	}

	@Override
	public void updateProduct(Product product) throws Exception {
			productDAO.updateProduct(product);
		
	}
	
}
