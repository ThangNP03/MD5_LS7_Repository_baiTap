package ra.service.category;

import ra.model.Category;
import ra.service.IGeneric;

public interface ICategoryService extends IGeneric<Category, Long> {
    Iterable<Category> findAll();
}
