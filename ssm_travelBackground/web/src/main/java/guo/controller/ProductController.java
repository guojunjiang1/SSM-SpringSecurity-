package guo.controller;

import com.github.pagehelper.PageInfo;
import daomain.Product;
import guo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    //查看产品
    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        List<Product> allProduct = productService.findAllProduct(page,size);
        PageInfo pageInfo=new PageInfo(allProduct);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    //添加产品
    @RequestMapping("save.do")
    public void add(Product product, HttpServletResponse response, HttpServletRequest request) throws Exception{
        productService.add(product);
        response.sendRedirect(request.getContextPath()+"/product/findAll.do");
    }
    //删除产品
    @RequestMapping("delete.do")
    public void delete(String[] ids,HttpServletResponse response, HttpServletRequest request) throws Exception {
        productService.delete(ids);
        response.sendRedirect(request.getContextPath()+"/product/findAll.do");
    }
    //根据Id查找产品
    @RequestMapping("findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView modelAndView=new ModelAndView();
        Product product=productService.findById(id);
        modelAndView.addObject("product",product);
        modelAndView.setViewName("product-update");
        return modelAndView;
    }
    //修改产品
    @RequestMapping("update.do")
    public void update(Product product,HttpServletResponse response, HttpServletRequest request) throws Exception {
        productService.update(product);
        response.sendRedirect(request.getContextPath()+"/product/findAll.do");
    }
}
