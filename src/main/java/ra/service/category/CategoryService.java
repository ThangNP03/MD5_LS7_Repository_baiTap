package ra.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.Category;
import ra.repository.ICategoryRepository;


import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
@Autowired
private ICategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable, Sort sort) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
            categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
