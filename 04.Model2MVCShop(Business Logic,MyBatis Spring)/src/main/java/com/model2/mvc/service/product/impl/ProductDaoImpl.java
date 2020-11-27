package com.model2.mvc.service.product.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDAO {

	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	 public void setSqlSession(SqlSession sqlSession) {
		 System.out.println("::"+getClass()+".sqlSession() Call..");
		this.sqlSession = sqlSession;
	}
	
	 //Constructor
	 public ProductDaoImpl() {
		 System.out.println("::"+getClass()+"default Constructor Call...");
		System.out.println(this.getClass());
	}
	 
	 
	
	//method
	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper10.addProduct", product);

	}

	@Override
	public Product getProduct(String prodName) throws Exception {
		
		return sqlSession.selectOne("ProductMapper10.getProduct", prodName);
	}

	@Override
	public List<Product> getProductList(Search search) throws Exception {
		
		return sqlSession.selectList("ProductMapper10.getProductList", search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
			sqlSession.update("ProductMapper10.updateProduct", product);
		
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		
		return sqlSession.selectOne("ProductMapper10.getTotalCount", search);
	}

	@Override
	public int removeProduct(String prodName) throws Exception {
	
		return sqlSession.delete("ProductMapper10.removeProduct", prodName);
	}

}
