package guo.service.impl;

import com.github.pagehelper.PageHelper;
import guo.dao.ProductDao;
import daomain.Product;
import guo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    //查找产品
    @Override
    public List<Product> findAllProduct(int page,int size) throws Exception {
        //添加分页插件对象(必须在sql语句前)
        PageHelper.startPage(page,size);
        return productDao.findAllProductDao();
    }
    //添加产品
    @Override
    public void add(Product product) throws Exception{
        productDao.add(product);
    }
    //删除产品
    @Override
    public void delete(String[] ids) throws Exception {
        for(int i=0;i<ids.length;i++){
            productDao.delete(ids[i]);
        }
    }
    //根据id查找产品
    @Override
    public Product findById(String id) throws Exception{
        return productDao.findByID(id);
    }
    //修改产品
    @Override
    public void update(Product product) throws Exception {
        productDao.upDate(product);
    }
}
