package com.model2.mvc.service.product.test;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;

import junit.framework.Assert;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
															   	"classpath:config/context-aspect.xml",
															   	 "classpath:config/context-mybatis.xml",
															   	"classpath:config/context-transaction.xml"})																
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	
	//@Test
	public void testAddProduct()throws Exception{
		
		
		Product product = new Product();
		product.setProdName("k9");
		product.setProdDetail("µÓ±ﬁº¯¿ß");
		product.setManuDate("¿∞¿œƒ•±∏");
		product.setPrice(600000);
		product.setFileName("123");
		
		productService.addProduct(product);
		
		System.out.println(product);
		
		product = productService.getProduct("k9");
		
		Assert.assertEquals("k9", product.getProdName());
		Assert.assertEquals("µÓ±ﬁº¯¿ß", product.getProdDetail());
		Assert.assertEquals("¿∞¿œƒ•±∏", product.getManuDate());
		Assert.assertEquals(600000, product.getPrice());
		Assert.assertEquals("123", product.getFileName());
	}
		
	//@Test
	public void testGetProduct()throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct("k9");
		
		System.out.println(product);
		
		Assert.assertEquals("k9", product.getProdName());
		Assert.assertEquals("µÓ±ﬁº¯¿ß", product.getProdDetail());
		Assert.assertEquals("¿∞¿œƒ•±∏", product.getManuDate());
		Assert.assertEquals(600000, product.getPrice());
		Assert.assertEquals("123", product.getFileName());
		
		}
		
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product  = productService.getProduct("k9");
		System.out.println("»Æ¿Œ:"+product);
		//Assert.assertNotNull(product);
		
//		Assert.assertEquals("k9", product.getProdName());
//		Assert.assertEquals("µÓ±ﬁº¯¿ß", product.getProdDetail());
//		Assert.assertEquals("¿∞¿œƒ•±∏", product.getManuDate());
//		Assert.assertEquals(600000, product.getPrice());
		
		product.setProdName("∏¿µøªÍ");
		
		System.out.println("»Æ¿Œ1:"+product);
		product.setProdDetail("≤ŸøÔ∏¿");
		
		System.out.println("»Æ¿Œ2:"+product);
		
		product.setManuDate("¥Á¿œ∆¢±Ë");
		
		System.out.println("»Æ¿Œ3:"+product);
		
		product.setPrice(3000);
		
		System.out.println("»Æ¿Œ4:"+product);
		
		productService.updateProduct(product);
		
		System.out.println("»Æ¿Œ5:"+product);
		
		product = productService.getProduct("∏¿µøªÍ");
		System.out.println("»Æ¿Œ6:"+product);
		
		System.out.println(product);
		
		Assert.assertEquals("∏¿µøªÍ", product.getProdName());
		Assert.assertEquals("≤ŸøÔ∏¿", product.getProdDetail());
		Assert.assertEquals("¥Á¿œ∆¢±Ë", product.getManuDate());
		Assert.assertEquals(3000, product.getPrice());
	}
	
	
	//@Test
	public void testGetProductListByProdName()throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("¿∫º∑");
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
	}
	
	@Test
	 public void testGetProductListByPrice() throws Exception{
		 
		 	Search search = new Search();
		 	search.setCurrentPage(3);
		 	search.setPageSize(3);
		 	search.setSearchCondition("1");
		 	search.setSearchKeyword("11");
		 	Map<String,Object> map = productService.getProductList(search);
		 	
		 	List<Object> list = (List<Object>)map.get("list");
		 	Assert.assertEquals(1, list.size());
		 	
		 	System.out.println(list);
		
		 }	 
	}

	

		
	
	
		
	

