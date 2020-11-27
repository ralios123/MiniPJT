package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;


public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String prodNo =(String)(request.getParameter("prodNo"));
		System.out.println("updateprodNo Ȯ�� :"+prodNo);
		
		
		Product product = new Product();
		
		
		 product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		 product.setProdName(request.getParameter("prodName"));
		System.out.println("prodName Ȯ�� :"+request.getParameter("prodName"));
		
		 product.setProdDetail(request.getParameter("prodDetail"));
		System.out.println("prodDetail Ȯ�� :"+request.getParameter("prodDetail"));
		
		 product.setManuDate(request.getParameter("manuDate"));
		System.out.println("manuDatel Ȯ�� :"+request.getParameter("manuDate"));
		
		 product.setPrice(Integer.parseInt(request.getParameter("price")));
		System.out.println("price Ȯ�� :"+request.getParameter("price"));
		
//		vo.setFileName(request.getParameter("fileName"));
//		System.out.println("price Ȯ�� :"+request.getParameter("fileName"));
//		
		ProductService productService = new ProductServiceImpl();
		productService.updateProduct(product);
		
		
		
		return "redirect:/getProduct.do?menu=search&prodNo="+prodNo;
	}
}
		
		
