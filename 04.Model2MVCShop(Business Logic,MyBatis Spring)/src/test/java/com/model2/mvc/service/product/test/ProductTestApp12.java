package com.model2.mvc.service.product.test;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ProductTestApp12 {

	public static void main(String[] args) throws Exception{
		
		//==> SqlSessionFactoryBean.getSqlSession()¿ª ¿ÃøÎ SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		
		
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.setProductDAO(productDao);
		System.out.println("\n");
		
	
		Product product = new Product();
		product.setProdName("æ∆π›∂º");
		product.setProdDetail("µÓ±ﬁº¯¿ß");
		product.setManuDate("¿∞¿œƒ•±∏");
		product.setPrice(Integer.parseInt("1234"));
		product.setFileName("123");

		
		//1. addProduct Test  ::
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		productService.addProduct(product);
		System.out.println("::addProduct »Æ¿Œ:"+ product);
		System.out.println("\n");
		
		
		//2.getProduct Test ::  
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		System.out.println(":: "+productService.getProduct(product.getProdName()));
		System.out.println("\n");
	
	
	
	//3.updateProduct Test  :: 
		product.setProdNo(Integer.parseInt("1234"));
		product.setProdName("BBQ »≤±›ø√∏Æ∫Í");
		product.setProdDetail("∏¿¿÷¥Á");
		product.setManuDate("123");
		product.setPrice(Integer.parseInt("1234"));
		
		System.out.println(":: 3. update(UPDATE)  ? ");
		productService.updateProduct(product);
		System.out.println(":: updateProduct »Æ¿Œ:"+product);
		System.out.println("\n");
		
		//4. getProductList Test  ::
		Search search = new Search();
		search.setSearchCondition("0");
		search.setSearchKeyword("¿∫º∑");
		
		
		System.out.println(":: 4. getProductList(SELECT)  ? ");
		System.out.println(":: "+productService.getProductList(search));
		System.out.println("\n");
	
		//5. removeProduct Test
		System.out.println(":: 5. removeProduct (DELETE)  ? ");
		System.out.println(":: "+productService.removeProduct(product.getProdName()));
		System.out.println("\n");
		
		System.out.println("::END::SqlSession ¥›±‚..");
		sqlSession.close();
		
		
		

	
	}// end of main
}// end of class