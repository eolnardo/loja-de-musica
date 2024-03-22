package br.senac.tialejo.controller;

import br.senac.tialejo.model.Task;
import br.senac.tialejo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
        //mv.addObject("task", new Task());
        return mv;

    }
//    @PostMapping("/login")
//    public ModelAndView create(@ModelAttribute @Validated Task task, BindingResult result) {
//        ModelAndView mv = new ModelAndView("login");
//        if (result.hasErrors()) {
//            mv.setViewName("login");
//            mv.addObject("task", task); // Adiciona o objeto Task de volta ao modelo
//            mv.addObject("errors", result.getAllErrors()); // Adiciona os erros de validação ao modelo
//            return mv;
//        }
//        taskRepository.save(task);
//        mv.setViewName("redirect:/principal");
//        return mv;
//    }
    @GetMapping("/cadastro-pessoa")
    public ModelAndView cadastro(){
       ModelAndView mv = new ModelAndView("cadastro-pessoa");
       mv.addObject("task", new Task());
        return mv;

    }
    @PostMapping("/cadastro-pessoa")
    public ModelAndView cadastrarUsuario(@ModelAttribute @Validated Task task, BindingResult result) {
        ModelAndView mv = new ModelAndView("cadastro-pessoa");

        if (!task.getSenha().equals(task.getConfirmaSenha())) {
            result.rejectValue("senha", "error.task", "As senhas não coincidem");

        }
        if (result.hasErrors()) {
            // Se houver erros de validação, retorna para a mesma página de cadastro com os erros
            return mv;
        }

        //criptografando antes de salvar
        String senhaCriptografada = passwordEncoder.encode(task.getSenha());
        task.setSenha(senhaCriptografada);

        // Lógica para salvar o usuário no banco de dados
        taskRepository.save(task);

        // Redireciona para a página principal após o cadastro bem-sucedido
        return new ModelAndView("redirect:/principal");
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
            taskToUpdate.setRole(task.getRole());
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
