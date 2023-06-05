package com.example.blogsecurity.Service;

import com.example.blogsecurity.APIException.APIException;
import com.example.blogsecurity.Model.Blog;
import com.example.blogsecurity.Model.User;
import com.example.blogsecurity.Repository.AuthRepository;
import com.example.blogsecurity.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;

    //----------------assign blogs to users-----------------------
    public void assignBlogToUser(Integer user_id,Integer blog_id){
        //check if they both exist
        User user=authRepository.findUserById(user_id);
        Blog blog=blogRepository.findBlogById(blog_id);
        if(user==null||blog==null){
            throw new APIException("can't assign Blog, wrong id");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    //------------End Of Assigning-------------------------------




    //---------------------CRUD----------------------------------

    public List<Blog> getBlogs(Integer userId) {
        return blogRepository.findBlogByUserId(userId);
    }


    public void addBlog(Integer userId, Blog blog){
        User user=authRepository.findUserById(userId);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Blog blog, Integer blogid){

        Blog oldBlog=blogRepository.findBlogById(blogid);
        if(oldBlog==null) {
            throw new APIException("sorry no such Blog to update");
        }

        if(oldBlog.getUser().getId()!=userId) {
            throw new APIException("unAuthorized, this Blog doesn't belong to you!");
        }

        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer userId, Integer blogid){
        //check if Blog  exist

        Blog oldBlog=blogRepository.findBlogById(blogid);
        if(oldBlog==null )
        {
            throw new APIException("sorry no such Blog to delete or user not found, try another ID");
        }

        if(oldBlog.getUser().getId()!=userId) {
            throw new APIException("unAuthorized, this Blog doesn't belong to you!");
        }


        //else, if found
        blogRepository.delete(oldBlog);
    }


    //-------------Other EndPoints---------------------------

        //Get All user blogs
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }


        //Get blog by Id

    public Blog findById(Integer id){
        Blog idBlog=blogRepository.findBlogById(id);
        if(idBlog==null){
            throw new APIException("Sorry, We Couldn't find the Blog your looking for, Try another ID!");
        }
        return idBlog;
    }


        //Get blog by title
        public Blog findByTitle(String title){
            Blog titleBlog=blogRepository.findBlogByTitle(title);
            if(title==null){
                throw new APIException("sorry no such blog with this title :(, try another title");
            }
            return titleBlog;
        }
}
