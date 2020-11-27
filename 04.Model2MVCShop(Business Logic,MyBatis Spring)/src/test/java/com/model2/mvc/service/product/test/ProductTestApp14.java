package com.model2.mvc.service.product.test;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductDaoImpl;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.sun.scenario.effect.impl.prism.PrDrawable;

public class ProductTestApp14 {

	public static void main(String[] args) throws Exception{
		
		//==> SqlSessionFactoryBean.getSqlSession()을 이용 SqlSession instance GET
		
		ApplicationContext context =
				new ClassPathXmlApplicationContext(
																new String[] {"/config/commonservice.xml"});			
																															
				
		
		System.out.println("\n");
		
		ProductService productService = (ProductService)context.getBean("productServiceImpl");
		
		System.out.println("\n");
		
	
		Product product = new Product();
		product.setProdName("아반떼");
		product.setProdDetail("등급순위");
		product.setManuDate("육일칠구");
		product.setPrice(Integer.parseInt("1234"));
		product.setFileName("123");

		
		//1. addProduct Test  ::
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		productService.addProduct(product);
		System.out.println("::addProduct 확인:"+ product);
		System.out.println("\n");
		
		
		//2.getProduct Test ::  
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		System.out.println(":: "+productService.getProduct(product.getProdName()));
		System.out.println("\n");
	
	
	
	//3.updateProduct Test  :: 
		
		product.setProdName("BBQ 황금올리브");
		product.setProdDetail("맛있당");
		product.setManuDate("123");
		product.setPrice(Integer.parseInt("1234"));
		
		System.out.println(":: 3. update(UPDATE)  ? ");
		productService.updateProduct(product);
		System.out.println(":: updateProduct 확인:"+product);
		System.out.println("\n");
		
		//4. getProductList Test  ::
		Search search = new Search();
		search.setSearchCondition("0");
		search.setSearchKeyword("은섭");
		
		
		System.out.println(":: 4. getProductList(SELECT)  ? ");
		System.out.println(":: "+productService.getProductList(search));
		System.out.println("\n");
	
		//5. removeProduct Test
		System.out.println(":: 5. removeProduct (DELETE)  ? ");
		System.out.println(":: "+productService.removeProduct(product.getProdName()));
		System.out.println("\n");
		
		
		
		
		

	
	}// end of main
}// end of class