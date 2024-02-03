package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList();

    public Product create(Product product) {
        product.setProductId(String.valueOf(productData.size()));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product getById(String id) {
        int index = Integer.parseInt(id);
        if (index < productData.size())
            return productData.get(index);
        else
            return null;
    }

    public void edit(String id, Product product) {
        int index = Integer.parseInt(id);
        if (index < productData.size())
            product.setProductId(id);
            productData.set(index, product);
    }
}
