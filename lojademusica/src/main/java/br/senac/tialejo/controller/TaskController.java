package br.senac.tialejo.controller;

import br.senac.tialejo.model.Task;
import net.sf.jsqlparser.Model;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    List<Task> tasks = new ArrayList<>();
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("task", new Task());
        return mv;

    }
    @PostMapping("/login")
    public String create(Task task){

        if(task.getId() !=null){
            Task taskFind = tasks.stream().filter(taskItem ->
                    task.getId().equals(taskItem.getId())).findFirst().get();
            tasks.set(tasks.indexOf(taskFind), task);
        }else{
            Long id = tasks.size() +1L;
            tasks.add(new Task(id, task.getName(), task.getEmail()));

        }
        return "redirect:/principal";

    }
    @GetMapping("principal")
    public String principal(){
        return "principal";
    }

    @GetMapping("/listar-usuarios")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("listar-usuarios");
        mv.addObject("tasks", tasks);
        return mv;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("edit");
        Task taskFind = tasks.stream().filter(task -> id.equals(task.getId())).findFirst().get();
        mv.addObject("task", taskFind);
        return mv;
    }
    @PostMapping("/edit")
    public String update(Task task) {
        Task taskToUpdate = tasks.stream().filter(t -> t.getId().equals(task.getId())).findFirst().orElse(null);
        if (taskToUpdate != null) {
            taskToUpdate.setName(task.getName());
            taskToUpdate.setEmail(task.getEmail());
        }
        return "redirect:/listar-usuarios";
    }



}
