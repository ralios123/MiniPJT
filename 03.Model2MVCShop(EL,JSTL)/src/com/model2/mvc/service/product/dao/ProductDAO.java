package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;



public class ProductDAO {

			public ProductDAO() {
			}
			
			
			   public void insertProduct(Product product)throws Exception{
				   
				   Connection con = DBUtil.getConnection();
				   
				   String sql = "INSERT "
				   		+ "INTO product "
				   		+ "VALUES(seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";
						   
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, product.getProdName());
					stmt.setString(2, product.getProdDetail());
					stmt.setString(3, product.getManuDate());
					stmt.setInt(4, product.getPrice());
					stmt.setString(5, product.getFileName());
					stmt.executeUpdate();
					System.out.println("insert User Check");
					
					stmt.close();
					con.close();
					
					
				}
				   
				
				public Product findProduct(int prodNo) throws Exception {
					
					Connection con = DBUtil.getConnection();
					
					String sql = "SELECT " +
										"prod_no, prod_name, prod_detail, manufacture_day, price, image_file, reg_date "+
										"FROM product WHERE prod_no=?";
				
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(1, prodNo);

					ResultSet rs = stmt.executeQuery();

					Product product = null;
					
					while(rs.next()) {
						product = new Product();
						product.setProdNo(Integer.parseInt(rs.getString("PROD_NO")));
						product.setProdName(rs.getString("PROD_NAME"));
						product.setProdDetail(rs.getString("PROD_DETAIL"));
						product.setManuDate(rs.getString("MANUFACTURE_DAY"));
						product.setPrice(Integer.parseInt(rs.getString("PRICE")));
						product.setFileName(rs.getString("IMAGE_FILE"));
						product.setRegDate(rs.getDate("REG_DATE"));
					}
				
						rs.close();
						stmt.close();
						con.close();
						
						return product;
				}
				
				public Map<String, Object> getProductList(Search search)throws Exception{
					
					Map<String , Object>  map = new HashMap<String, Object>();
					
					Connection con = DBUtil.getConnection();
					
					//쿼리 구성
					String sql = "SELECT prod_no, prod_name, prod_detail, manufacture_day, price, image_file, reg_date FROM product";
					
					if (search.getSearchCondition() != null) {
						if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
							sql += " WHERE prod_name='" + search.getSearchKeyword()+"'";
						} else if (search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
							sql += " WHERE price='" + search.getSearchKeyword()+"'";
								
						}
					}
					sql += " ORDER BY prod_no";
					
					System.out.println("ProductDAO::Original SQL :: " + sql);
					
					//==>TotalCount GET
					int totalCount = this.getTotalCount(sql);
					System.out.println("ProductDAO :: totalCount  :: " + totalCount);
					
					//==> CurrentPage 게시물만 받도록 Query 다시구성
					
					sql = makeCurrentPageSql(sql, search);
					PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					System.out.println(search);
					
					List<Product> list = new ArrayList<Product>();
					
					while(rs.next()){
						Product product = new Product();
						product.setProdNo(Integer.parseInt(rs.getString("PROD_NO")));
						product.setProdName(rs.getString("PROD_NAME"));
						product.setProdDetail(rs.getString("PROD_DETAIL"));
					product.setManuDate(rs.getString("MANUFACTURE_DAY"));
						product.setPrice(Integer.parseInt(rs.getString("PRICE")));
					product.setFileName(rs.getString("IMAGE_FILE"));
					product.setRegDate(rs.getDate("REG_DATE"));
						list.add(product);
					}
					
					//==> totalCount 정보 저장
					map.put("totalCount", new Integer(totalCount));
					//==> currentPage 의 게시물 정보 갖는 List 저장
					map.put("list", list);

					rs.close();
					stmt.close();
					con.close();
					
					return map;
				}
				


				public void updateProduct(Product vo) throws Exception {
					
					Connection con = DBUtil.getConnection();

					String sql = "UPDATE product "
							+ "SET PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?, PRICE=?"
							+ " WHERE PROD_NO=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, vo.getProdName());
					stmt.setString(2, vo.getProdDetail());
					stmt.setString(3, vo.getManuDate());
					stmt.setInt(4, vo.getPrice());
					stmt.setInt(5, vo.getProdNo());
					 stmt.executeUpdate();
					
					stmt.close();
					con.close();
				}
				
				// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
				private int getTotalCount(String sql) throws Exception {
					
					sql = "SELECT COUNT(*) "+
					          "FROM ( " +sql+ ") countTable";
					
					Connection con = DBUtil.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					int totalCount = 0;
					if( rs.next() ){
						totalCount = rs.getInt(1);
					}
					
					stmt.close();
					con.close();
					rs.close();
					
					return totalCount;
				}
				
				// 게시판 currentPage Row 만  return 
				private String makeCurrentPageSql(String sql , Search search){
					sql = 	"SELECT * "+ 
								"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
												" 	FROM (	"+sql+" ) inner_table "+
												"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
								"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
					
					System.out.println("ProductDAO :: make SQL :: "+ sql);	
					
					return sql;
				}
			}
				
			