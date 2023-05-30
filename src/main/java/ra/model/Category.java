package ra.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    @OneToMany(mappedBy = "category", targetEntity = Blog.class)
    private Set<Blog> blogSet;

    public Category() {
    }

    public Category(Long id, String name, Set<Blog> blogSet) {
        this.id = id;
        Name = name;
        this.blogSet = blogSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<Blog> getBlogSet() {
        return blogSet;
    }

    public void setBlogSet(Set<Blog> blogSet) {
        this.blogSet = blogSet;
    }
}
