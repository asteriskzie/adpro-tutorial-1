package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList();
    
    private int newProductId = 1; 

    private int searchIndexById(String id) {
        int lowerBound = 0;
        int upperBound = productData.size() - 1;

        while (lowerBound <= upperBound) {
            int mid = (lowerBound + upperBound) / 2;
            if (productData.get(mid).getProductId().compareTo(id) < 0) {
                lowerBound = mid + 1;
            }
            else if (productData.get(mid).getProductId().compareTo(id) > 0) {
                upperBound = mid - 1;
            }
            else {
                return mid;
            }
        }
        
        return productData.size();
    }

    public Product create(Product product) {
        product.setProductId((newProductId++) + "");
        productData.add(product);
        return product;
    }
    
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    
    public Product getById(String id) {
        int index = searchIndexById(id);
        if (index < productData.size()) {
            return productData.get(index);
        }
        else {
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
