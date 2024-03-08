package br.senac.tialejo.controller;

import br.senac.tialejo.model.Task;
import br.senac.tialejo.repository.TaskRepository;
import net.sf.jsqlparser.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("task", new Task());
        return mv;

    }
    @PostMapping("/login")
    public ModelAndView create(@ModelAttribute @Validated Task task, BindingResult result) {
        ModelAndView mv = new ModelAndView("login");
        if (result.hasErrors()) {
            mv.setViewName("login");
            mv.addObject("task", task); // Adiciona o objeto Task de volta ao modelo
            mv.addObject("errors", result.getAllErrors()); // Adiciona os erros de validação ao modelo
            return mv;
        }
        taskRepository.save(task);
        mv.setViewName("redirect:/principal");
        return mv;
    }
    @GetMapping("principal")
    public String principal(){
        return "principal";
    }

    @GetMapping("/listar-usuarios")
    public ModelAndView listar(){
        Iterable<Task> tasks = taskRepository.findAll();
        ModelAndView mv = new ModelAndView("listar-usuarios");
        mv.addObject("tasks", tasks);
        return mv;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("edit");
        Optional<Task> taskFinder =taskRepository.findById(id);

        if(taskFinder.isPresent()) {
            Task task = taskFinder.get();
            mv.addObject("task", task);
        }

        return mv;
    }
    @PostMapping("/edit")
    public String update(Task task) {
        Optional<Task> optionalTaskToUpdate = taskRepository.findById(task.getId());
        if (optionalTaskToUpdate.isPresent()) {
            Task taskToUpdate = optionalTaskToUpdate.get();
            taskToUpdate.setName(task.getName());
            taskToUpdate.setEmail(task.getEmail());
            taskToUpdate.setTelefone(task.getTelefone());
            taskToUpdate.setGrupo(task.getGrupo());
            taskToUpdate.setStatus(task.getStatus());
            taskRepository.save(taskToUpdate);
        }
        return "redirect:/listar-usuarios";
    }
//    @PostMapping("/edit")
//    public String delete(Task task) {
//
//       taskRepository.deleteById(task.getId());
//
//        return "redirect:/listar-usuarios";
//    }



}
