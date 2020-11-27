package com.model2.mvc.service.product.test;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductDaoImpl;

public class ProductTestApp11 {

	public static void main(String[] args) throws Exception{
		
		//==> SqlSessionFactoryBean.getSqlSession()¿ª ¿ÃøÎ SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.setSqlSession(sqlSession);
		System.out.println("\n");
		
	
		Product product = new Product();
		product.setProdName("æ∆π›∂º");
		product.setProdDetail("µÓ±ﬁº¯¿ß");
		product.setManuDate("¿∞¿œƒ•±∏");
		product.setPrice(Integer.parseInt("1234"));
		product.setFileName("123");

		
		//1. addProduct Test  ::
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		productDao.addProduct(product);
		System.out.println("::addProduct »Æ¿Œ:"+ product);
		System.out.println("\n");
		
		
		//2.getProduct Test ::  
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		System.out.println(":: "+productDao.getProduct(product.getProdName()));
		System.out.println("\n");
	
	
	
	//3.updateProduct Test  :: 
		product.setProdNo(10088);
		product.setProdName("BBQ »≤±›ø√∏Æ∫Í");
		product.setProdDetail("∏¿¿÷¥Á");
		product.setManuDate("123");
		product.setPrice(Integer.parseInt("1234"));
		
		System.out.println(":: 3. update(UPDATE)  ? ");
		productDao.updateProduct(product);
		System.out.println(":: updateProduct »Æ¿Œ:"+product);
		System.out.println("\n");
		
		//4. getProductList Test  ::
		Search search = new Search();
		search.setSearchCondition("0");
		search.setSearchKeyword("¿∫º∑");
		
		
		System.out.println(":: 4. getProductList(SELECT)  ? ");
		System.out.println(":: "+productDao.getProductList(search));
		System.out.println("\n");
	
		//5. removeProduct Test
		System.out.println(":: 5. removeProduct (DELETE)  ? ");
		System.out.println(":: "+productDao.removeProduct(product.getProdName()));
		System.out.println("\n");
		
		//6. getTotalCount Test
		
		System.out.println(":: 6. getTotalCount(TOTALCOUNT) ?");
		System.out.println(":: "+productDao.getTotalCount(search));	
		

		System.out.println("::END::SqlSession ¥›±‚..");
		sqlSession.close();
	
	}// end of main
}// end of class