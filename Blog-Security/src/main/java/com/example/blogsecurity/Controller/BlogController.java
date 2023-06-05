package com.example.blogsecurity.Controller;

import com.example.blogsecurity.Model.Blog;
import com.example.blogsecurity.Model.User;
import com.example.blogsecurity.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    //initialization
    private final BlogService blogService;


    //-----------------CRUD---------------------------

    @GetMapping("/get")
    public ResponseEntity getBlogs(@AuthenticationPrincipal User user){
        //@AuthenticationPrincipal to get the id of the user while logging in to get the list of the specific user
        List<Blog>blogs=blogService.getBlogs(user.getId());
        return ResponseEntity.status(200).body(blogs);
    }
    @PostMapping("/add")
    public ResponseEntity addToDo(@AuthenticationPrincipal User user, @RequestBody Blog blog){
        // we have to add to do inside this special user, we take todo and myuser to add in
        blogService.addBlog(user.getId(),blog);
        return ResponseEntity.status(200).body("Blog Added!");
    }


    @PutMapping("/update/{blogid}")
    public ResponseEntity updateToDo(@AuthenticationPrincipal User user, @RequestBody Blog blog,@PathVariable Integer blogid){

        blogService.updateBlog(user.getId(), blog,blogid);
        return ResponseEntity.status(200).body("Blog Updated!");
    }

    @DeleteMapping("/delete/{blogid}")
    public ResponseEntity deleteToDo(@AuthenticationPrincipal User user, @PathVariable Integer blogid){
        blogService.deleteBlog(user.getId(),blogid); //.getId();
        return ResponseEntity.status(200).body("Blog Deleted!");
    }


    //--------------Other EndPoints-------------------

    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs(){
        List<Blog> blogs=blogService.getAllBlogs();
        return ResponseEntity.status(200).body(blogs);
    }


    @GetMapping("/get-id/{id}")
    public ResponseEntity findById(@PathVariable Integer id){
        Blog blog= blogService.findById(id);
        return ResponseEntity.status(200).body(blog);
    }


    @GetMapping("/get-title/{title}")
    public ResponseEntity getByTitle(@PathVariable String title){
        Blog blog=blogService.findByTitle(title);
        return ResponseEntity.status(200).body(blog);
    }




}
