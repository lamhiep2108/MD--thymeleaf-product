package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static final Map<Integer,Product > products;
    static {
        products =new HashMap<>();
        products.put(1,new Product(1,"bánh mì",100000,"ngon","china"));
        products.put(2,new Product(2,"bánh cuốn",100000,"ngon ngon","china"));
        products.put(3,new Product(3,"bánh gai",100000,"ngon ngon ngon","china"));
        products.put(4,new Product(4,"bánh bông lan",100000,"ngon ngon ngon ngon","china"));
        products.put(5,new Product(5,"bánh sữa",100000,"ngon ngon ngon ngon ngon","china"));
        products.put(6,new Product(6,"bánh táo",100000,"ngon ngon ngon ngon ngon ngon","china"));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id,product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product>products=findAll();
        List<Product>productList=new ArrayList<>();
        for(int i=0;i<products.size();i++){
            if(products.get(i).getName().toLowerCase().contains(name.toLowerCase())){
                productList.add(products.get(i));
            }
        }
        return products;
    }
}
