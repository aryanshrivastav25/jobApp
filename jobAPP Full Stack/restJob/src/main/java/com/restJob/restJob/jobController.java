package com.restJob.restJob;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.restJob.restJob.model.JobPost;
import com.restJob.restJob.service.jobService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
// @RestController // Used when all the methods of the Controller are meant for sending data instead of views
// Using RestController, we do not need @ResponseBody at each function name
@CrossOrigin(origins = "http://localhost:3000")
// React and spring have different origins, so to allow the React application to fetch data from spring backend, we simply have to provide the frontend URL, in @CrossOrigin
public class jobController
{
    @Autowired
    private jobService service;

    @GetMapping(path = "/jobPosts", produces = {"application/json"}) // specify that from the server side, only json is being sent. we can also send in XML format using dependency jackson-dataformat-xml
    @ResponseBody // By default, Controller looks for the view names instead of data
    // By using @ResponseBody, we can send direct data instead of view, helps in testing backend when we do not have the frontend
    public List<JobPost> viewAllJobs() {
        return service.getAllJobs();
    }


    @GetMapping("jobPost/{jobId}")
    @ResponseBody
    public JobPost getJob(@PathVariable("jobId") int jobId)
    {
        return service.getJob(jobId);
    }

    // to accept xml instead of json, we can use
    // @PostMapping(path = "jobPost" consumes = {"application/xml"})
    @PostMapping("jobPost")
    @ResponseBody
    public void addJob(@RequestBody JobPost jobPost) // RequestBody converts the json data given by client to the JobPost object
    {
        service.addJob(jobPost);
    }

    @PutMapping("jobPost") // for updating the data
    @ResponseBody
    public JobPost updateJob(@RequestBody JobPost job) 
    {
        service.updateJob(job);
        return job;
    }


    @DeleteMapping("jobPost/{id}")
    @ResponseBody
    public void deleteJob(@PathVariable int id)
    {
        service.deleteJob(id);
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    @ResponseBody
    public List<JobPost> searchByKeyWord(@PathVariable("keyword") String keyword)
    {
        return service.search(keyword);
    }

    @GetMapping("/load")
    @ResponseBody
    public String load()
    {
        service.load();
        return "Success";
    }
}