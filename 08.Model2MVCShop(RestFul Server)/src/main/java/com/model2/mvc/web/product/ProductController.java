package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	public ProductController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	
	@RequestMapping(value="addProduct", method = RequestMethod.GET )
	public String addProduct()throws Exception{
		
		System.out.println("/product/addProduct : GET Start...");
		
		return "redirect:/product/addProductView.jsp" ;
	}
	
	@RequestMapping(value = "addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product")Product product)throws Exception{
		
		System.out.println("/product/addProduct : POST Start...");
		productService.addProduct(product);
		
		return "forward:/product/addProduct.jsp";
		
	}
	
	@RequestMapping(value="getProduct", method=RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo")int prodNo, Model model)throws Exception{
	
		System.out.println("/product/getProduct : GET Start...");
		
		Product product = productService.getProduct(prodNo);
		System.out.println("°´Ã¼È®ÀÎ:"+product);
		
		model.addAttribute("product", product);
	
		return  "forward:/product/getProduct.jsp";
	
	}
	
	@RequestMapping(value="updateProduct", method = RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo")int prodNo, Model model )throws Exception{
		
		System.out.println("/product/updateProduct : GET Start...");
		
		Product product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	
	
	
	
		
		@RequestMapping(value="updateProduct", method = RequestMethod.POST)
		public String updateProduct(@ModelAttribute("product")Product product)throws Exception{
			
			System.out.println("/product/updateProduct : POST Start...");
			
			productService.updateProduct(product);
			
			System.out.println("µð¹ö±ë:"+product);
			
			
	
		
				return "redirect:/product/getProduct?prodNo="+product.getProdNo()+"&menu=manage";
		}
			
				
			
	
		
		
		
		@RequestMapping(value="listProduct")
		public String listProduct(@ModelAttribute("search")Search search, Model model, HttpServletRequest request)throws Exception{
			
			System.out.println("/product/listProduct : GET/POST Start...");
			
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
