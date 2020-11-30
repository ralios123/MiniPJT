package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
public class ProductController {

	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	
	@RequestMapping("/addProductView.do")
	public String addProductView()throws Exception{
		
		System.out.println("/addProductView.do Start...");
		
		return "redirect:/product/addProductView.jsp" ;
	}
	
	@RequestMapping("/addProduct.do")
	public String addProduct(@ModelAttribute("product")Product product)throws Exception{
		
		System.out.println("/addProduct.do Start...");
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
		
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo")int prodNo, Model model)throws Exception{
	
		System.out.println("/getProduct.do Start...");
		
		Product product = productService.getProduct(prodNo);
		System.out.println("°´Ã¼È®ÀÎ:"+product);
		
		model.addAttribute("product", product);
	
		return  "forward:/product/getProduct.jsp";
	
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo")int prodNo, Model model )throws Exception{
		
		System.out.println("/updateProductView.do Start...");
		
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
		
		@RequestMapping("/updateProduct.do")
		public String updateProduct(@ModelAttribute("product")Product product)throws Exception{
			
			System.out.println("/updateProduct.do Start...");
			
			productService.updateProduct(product);
			
			System.out.println("µð¹ö±ë:"+product);
			
			
	
		
				return "redirect:/getProduct.do?prodNo="+product.getProdNo()+"&menu=manage";
		}
			
				
			
	
		
		
		
		@RequestMapping("/listProduct.do")
		public String listProduct(@ModelAttribute("search")Search search, Model model, HttpServletRequest request)throws Exception{
			
			System.out.println("/listProduct.do Start...");
			
			if(search.getCurrentPage()==0) {
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			Map<String, Object> map = productService.getProductList(search);
			
			Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			
			model.addAttribute("list", map.get("list"));
			model.addAttribute("resultPage", resultPage);
			model.addAttribute("search", search);
			
			return "forward:/product/listProduct.jsp";
			
		
	}
}
