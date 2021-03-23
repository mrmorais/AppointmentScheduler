package br.ufrn.imd.appointmentscheduler.ms.work.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufrn.imd.appointmentscheduler.ms.work.entity.Work;
import br.ufrn.imd.appointmentscheduler.ms.work.service.impl.WorkServiceImpl;



@Controller
@RequestMapping("/works")
public class WorkController {
	private final WorkServiceImpl workService;

    public WorkController(WorkServiceImpl workService) {
        this.workService = workService;
    }

    @GetMapping("/all")
    public String showAllWorks(Model model) {
        model.addAttribute("works", workService.getAllWorks());
        return "works/list";
    }

    @GetMapping("/{workId}")
    public String showFormForUpdateWork(@PathVariable("workId") int workId, Model model) {
        model.addAttribute("work", workService.getWorkById(workId));
        return "works/createOrUpdateWorkForm";
    }

    @GetMapping("/new")
    public String showFormForAddWork(Model model) {
        model.addAttribute("work", new Work());
        return "works/createOrUpdateWorkForm";
    }

    @PostMapping("/new")
    public String saveWork(@ModelAttribute("work") Work work) {
        if (work.getId() != null) {
            workService.updateWork(work);
        } else {
            workService.createNewWork(work);
        }
        return "redirect:/works/all";
    }

    @PostMapping("/delete")
    public String deleteWork(@RequestParam("workId") int workId) {
        workService.deleteWorkById(workId);
        return "redirect:/works/all";
    }

}
