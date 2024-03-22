package dev.nishant.productservicedtsmorning.repositories;

import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
  Product save(Product p);// attribute that are automatically generated will not be in param but will be part of the return object

  @Override
  List<Product> findAll();

  Product findByIdIs(Long id);

  List<Product> findAllByCategory(Category category);
}
