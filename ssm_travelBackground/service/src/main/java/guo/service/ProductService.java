package guo.service;

import daomain.Product;

import java.util.List;

//关于产品
public interface ProductService {
    //查找所有的产品
    List<Product> findAllProduct(int page,int size) throws Exception;
    //添加产品
    void add(Product product) throws Exception;
    //删除产品
    void delete(String[] ids) throws Exception;
    //通过id查找产品
    Product findById(String id) throws Exception;
    //修改产品
    void update(Product product) throws Exception;
}
