package ra.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ra.model.Category;

public interface ICategoryRepository extends PagingAndSortingRepository<Category, Long > {
}
