package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class AddProductAction extends Action {

	public String execute( HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		
		
		Product product = new Product();
		System.out.println("product확인:"+product);
		
		product.setProdName(request.getParameter("prodName"));
		System.out.println("product1확인:"+request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		System.out.println("product2확인:"+request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		System.out.println("product3확인:"+request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println("product4확인:"+request.getParameter("price"));
		product.setFileName(request.getParameter("fileName"));
		System.out.println("product5확인:"+request.getParameter("fileName"));
		
		System.out.println("AddProductAction ::"+product);
		
		ProductService productService = new ProductServiceImpl();
		productService.addProduct(product);
		System.out.println("product서비스 확인:"+productService);
		
		
		request.setAttribute("product", product);
	
		
		return "forward:/product/addProduct.jsp";
	}
}