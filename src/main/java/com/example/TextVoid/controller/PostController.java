package com.example.TextVoid.controller;

import com.example.TextVoid.entity.Post;
import com.example.TextVoid.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;

@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService thePostService){
        postService = thePostService;
    }

    @Value("${genres}")
    private List<String> genres;

    private String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()) {
                        return addr.getHostAddress(); // Returns 192.168.x.x or 10.x.x.x
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    @GetMapping("/")
    public String index(Model theModel){
        List<Post> posts = postService.findAll();
        theModel.addAttribute("posts",posts);
        theModel.addAttribute("genres",genres);
        return "index";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Post thePost = new Post();
        theModel.addAttribute("post", thePost);
        theModel.addAttribute("genres",genres);
        return "new-post-form";
    }

    @PostMapping("/save")
    public String saveEmployees(Model theModel,
                                @Valid @ModelAttribute("post") Post thePost,
                                BindingResult theBindingResult){
        if (theBindingResult.hasErrors()){
            theModel.addAttribute("post", thePost);
            theModel.addAttribute("genres", genres);
            return "new-post-form";
        }
        else {
            LocalDateTime da = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String str = LocalDateTime.parse(da.toString()).format(formatter);

            thePost.setDate(str);
            thePost.setIpAddress(getLocalIpAddress());
            Post post = postService.save(thePost);
            return "redirect:/";
        }
    }

    @PostMapping("/search")
    public String searchForPosts(Model theModel, @RequestParam("keyword") String keyword){
        if (!keyword.isEmpty()){
            List<Post> result = postService.findByMessage(keyword);
            theModel.addAttribute("result", result);
            theModel.addAttribute("genres",genres);
            theModel.addAttribute("keyword", keyword);
            return "search-message-results";
        }
        else {
            theModel.addAttribute("genres",genres);
            return "redirect:/";
        }
    }

    @GetMapping("/searchByGenre")
    public String searchForPostsByGenre(Model theModel, @RequestParam("givenGenre") String genre){
        List<Post> result = postService.findByGenre(genre);
        theModel.addAttribute("result", result);
        theModel.addAttribute("genres",genres);
        theModel.addAttribute("givenGenre",genre);

        return "search-genre-results";
    }

    @GetMapping("/showLatestPosts")
    public String showLatestPosts(Model theModel){
        List<Post> result = postService.findLatestPosts();
        theModel.addAttribute("result", result);
        theModel.addAttribute("genres",genres);
        return "latest-posts";
    }

}
