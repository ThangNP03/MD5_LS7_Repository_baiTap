package ra.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ra.model.Blog;
import ra.model.Category;
import ra.service.blog.IBlogService;
import ra.service.category.ICategoryService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private IBlogService blogService;
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("category")
    Iterable<Category> getlist(){
        return categoryService.findAll();
    };
    @GetMapping("/")
    public String showIndex(@RequestParam("sortBy") Optional<String> sortBy, ModelMap model, Pageable pageable) {
        Sort sort = null;
        if (sortBy.isPresent()) {
            switch (sortBy.get()) {
                case "postDate-ASC":
                    sort = Sort.by("postDate").ascending();
                    break;
                case "postDate-DESC":
                    sort = Sort.by("postDate").descending();
                    break;
                case "Title-ASC":
                    sort = Sort.by("title").ascending();
                    break;
                case "Title-DESC":
                    sort = Sort.by("title").descending();
                    break;
                default:
                    break;
            }

        }else {
            sort = Sort.by("title").ascending().and(Sort.by("postDate").ascending());
        }
        Page<Blog> list = blogService.findAll(pageable,sort);
        model.addAttribute("blogs", list);
        return "index";
    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetail( @PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);

        return  new ModelAndView("detail", "blog", blog.get());
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        return new ModelAndView("create", "blog", new Blog());
    }

    @PostMapping("/save")
    public String saveBlog(@Valid @ModelAttribute ("blog") Blog b, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return "create";
        }
        b.setPostDate(LocalDate.now());
        blogService.save(b);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit( @PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);

        return  new ModelAndView("edit", "blog", blog.get());
    }

    @GetMapping("/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.remove(id);
        return "redirect:/";
    }

}

