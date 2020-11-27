package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;


public class GetProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	
		
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("get프로덕트no :"+prodNo);
		
		String menu = (String)request.getParameter("menu");
		System.out.println("get메뉴:"+menu);
		
		
		
		
		
		ProductService productService = new ProductServiceImpl();
		Product product=productService.getProduct(prodNo);
		System.out.println("겟프로덕트vo:"+ product);
		
		//HttpSession session =  request.getSession();
		//session.setAttribute("menu", menu);
		
		request.setAttribute("menu", menu);
		request.setAttribute("product", product);
		
		if(menu.equals("manage")) {
			System.out.println("update이프문:"+menu);
			return  "forward:/product/updateProductView.jsp";
		
		}else {
			System.out.println("get프로덕트이프문2:"+menu);
			return  "forward:/product/getProduct.jsp";
			
		}
	}
	
			
}		
	
		
		
	
		

	

