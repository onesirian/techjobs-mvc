package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
    @RequestMapping(value = "results")
    public String results (Model model, @RequestParam String searchTerm, @RequestParam String searchType){

        ArrayList<HashMap<String,String>> jobs;

        if (searchType.equals("all")){
            jobs = JobData.findByValue(searchTerm);
        }
        else{
            jobs = JobData.findByColumnAndValue(searchType,searchTerm);
        }

        model.addAttribute("columns",ListController.columnChoices);

        model.addAttribute("jobs",jobs);

        return "search"; // returns the results HTML Page
    }

}