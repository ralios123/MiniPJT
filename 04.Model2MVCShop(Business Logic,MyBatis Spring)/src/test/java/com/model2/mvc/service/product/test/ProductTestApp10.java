package com.model2.mvc.service.product.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

import oracle.sql.DATE;






public class ProductTestApp10 {

	public static void main(String[] args) throws Exception{
		
		//==> SqlSessionFactoryBean.getSqlSession()À» ÀÌ¿ë SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		System.out.println("sqlsession:"+sqlSession);
		System.out.println("\n");

	
		Product product = new Product();
		product.setProdName("¾Æ¹Ý¶¼");
		product.setProdDetail("µî±Þ¼øÀ§");
		product.setManuDate("À°ÀÏÄ¥±¸");
		product.setPrice(Integer.parseInt("1234"));
		product.setFileName("123");

		
		
		
		
		
		
//		//1. addProduct Test  ::
	System.out.println(":: 1. addProduct(INSERT)  ? ");
		System.out.println(":: "+ sqlSession.insert("ProductMapper10.addProduct",product));
		System.out.println("\n");
	
	
		
		//2.getProduct Test ::  
	System.out.println(":: 2. getProduct(SELECT)  ? ");
	System.out.println(":: "+sqlSession.selectOne("ProductMapper10.getProduct",product.getProdName()));
	System.out.println("prod_no null°ª È®ÀÎ:"+product.getProdName());
	System.out.println("\n");

	
	
		//3.updateProduct Test  :: 
		product.setProdNo(10010);
		product.setProdName("BBQ È²±Ý¿Ã¸®ºê");
		product.setProdDetail("¸ÀÀÖ´ç");
		product.setManuDate("123");
		product.setPrice(Integer.parseInt("1234"));
		
		System.out.println(":: 3. update(UPDATE)  ? ");
		System.out.println(":: "+ sqlSession.update("ProductMapper10.updateProduct",product));
		System.out.println("\n");
		
		//4. getProductList Test  ::
		Search search = new Search();
		search.setSearchCondition("0");
		search.setSearchKeyword("Àº¼·");
		
		
		System.out.println(":: 4. getProductList(SELECT)  ? ");
		System.out.println(":: "+sqlSession.selectList("ProductMapper10.getProductList",search));
		System.out.println("\n");
//	
//		//5. removeProduct Test
	System.out.println(":: 5. removeProduct (DELETE)  ? ");
		System.out.println(":: "+sqlSession.delete("ProductMapper10.removeProduct", product.getProdName()));
		System.out.println("\n");
		
//		//6. getTotalCount Test
//		
		System.out.println(":: 6. getTotalCount(TOTALCOUNT) ?");
		System.out.println(":: "+sqlSession.selectOne("ProductMapper10.getTotalCount", search));
		
		System.out.println("::END::SqlSession ´Ý±â..");
		sqlSession.close();

	
	System.out.println("::END::SqlSession ´Ý±â..");
		sqlSession.close();
		//1. UserMapper10.getUserList Test 
//		System.out.println(":: 1. getUserList01(SELECT)  ? ");
//	SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper10.getProductList",search) );
		
		//2. UserMapper10.getUserList Test 
//		search.setSearchCondition("prodNo");
//		ArrayList<String> arrayList = new ArrayList<String>();
//		arrayList.add("user01");
//	search.setPr( arrayList );
		
//		System.out.println(":: 2. getUserList01(SELECT)  ? ");
//		SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper10.getProductList",search) );

//		//3. getProductList Test 
//		search.setSearchCondition("prodName");
//		search.set( new String[]{ "°ÅºÏ¼±"} );
//		
//		System.out.println(":: 3. getUserList01(SELECT)  ? ");
//		SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper10.getProductList",search) );
//
//		//END::SqlSession  close
//		System.out.println("::END::SqlSession ´Ý±â..");
//		sqlSession.close();
		
	}//end of main
}//end of class