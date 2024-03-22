package dev.nishant.productservicedtsmorning.repositories;

import dev.nishant.productservicedtsmorning.models.Category;
import dev.nishant.productservicedtsmorning.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

        Category findByTitle(String title);
        Category save(Category category);

        List<Category> findAll();
}
