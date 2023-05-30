package ra.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ra.model.Blog;

import java.util.List;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {
}
