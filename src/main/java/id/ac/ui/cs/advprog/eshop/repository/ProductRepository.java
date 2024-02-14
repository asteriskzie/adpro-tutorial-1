package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    /**
     *
     * @param id the id of product
     * @return index of the product with given id in productData, return productData.size() if not found
     */
    private int searchIndexById(String id) {
        int index = 0;
        Iterator<Product> it = findAll();
        while(it.hasNext()) {
            Product temp = it.next();
            if (temp.getProductId().equals(id)) {
                return index;
            }
            index++;
        }
        return productData.size();
    }
    
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    public Product getById(String id) {
        int index = searchIndexById(id);
        if (index < productData.size()) {
            return  productData.get(index);
        } else {
            return null;
        }
    }
    
    public void edit(String id, Product product) {
        int index = searchIndexById(id);
        if (index < productData.size()) {            
            product.setProductId(id);
            productData.set(index, product);
        }
    }
    
    public void delete(String id) {
        int index = searchIndexById(id);
        if (index < productData.size()) {
            productData.remove(index);
        }
    }
}
