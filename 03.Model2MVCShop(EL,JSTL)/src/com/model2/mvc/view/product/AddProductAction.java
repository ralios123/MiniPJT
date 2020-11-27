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
		System.out.println("productȮ��:"+product);
		
		product.setProdName(request.getParameter("prodName"));
		System.out.println("product1Ȯ��:"+request.getParameter("prodName"));
		product.setProdDetail(request.getParameter("prodDetail"));
		System.out.println("product2Ȯ��:"+request.getParameter("prodDetail"));
		product.setManuDate(request.getParameter("manuDate"));
		System.out.println("product3Ȯ��:"+request.getParameter("manuDate"));
		product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println("product4Ȯ��:"+request.getParameter("price"));
		product.setFileName(request.getParameter("fileName"));
		System.out.println("product5Ȯ��:"+request.getParameter("fileName"));
		
		System.out.println("AddProductAction ::"+product);
		
		ProductService productService = new ProductServiceImpl();
		productService.addProduct(product);
		System.out.println("product���� Ȯ��:"+productService);
		
		
		request.setAttribute("product", product);
	
		
		return "forward:/product/addProduct.jsp";
	}
}