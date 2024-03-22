package br.senac.tialejo.controller;

import br.senac.tialejo.model.User;
import br.senac.tialejo.repository.UserRepository;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");

        return mv;

    }

    @GetMapping("/cadastro-pessoa")
    public ModelAndView cadastro(){
       ModelAndView mv = new ModelAndView("cadastro-pessoa");
       mv.addObject("user", new User());
        return mv;

    }
    @PostMapping("/cadastro-pessoa")
    public ModelAndView cadastrarUsuario(@ModelAttribute @Validated User user, BindingResult result) {
        ModelAndView mv = new ModelAndView("cadastro-pessoa");

        if (!user.getSenha().equals(user.getConfirmaSenha())) {
            result.rejectValue("senha", "error.user", "As senhas não coincidem");

        }
        if (result.hasErrors()) {
            // Se houver erros de validação, retorna para a mesma página de cadastro com os erros
            return mv;
        }

        //criptografando antes de salvar
        String senhaCriptografada = passwordEncoder.encode(user.getSenha());
        user.setSenha(senhaCriptografada);

        // Lógica para salvar o usuário no banco de dados
        userRepository.save(user);

        // Redireciona para a página principal após o cadastro bem-sucedido
        return new ModelAndView("redirect:/principal");
    }


    @GetMapping("principal")
    public String principal(){
        return "principal";
    }

    @GetMapping("/listar-usuarios")
    public ModelAndView listar(){
        Iterable<User> users = userRepository.findAll();
        ModelAndView mv = new ModelAndView("listar-usuarios");
        mv.addObject("users", users);
        return mv;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("edit");
        Optional<User> taskFinder = userRepository.findById(id);

        if(taskFinder.isPresent()) {
            User user = taskFinder.get();
            mv.addObject("user", user);
        }

        return mv;
    }
    @PostMapping("/edit")
    public String update(User user) {
        Optional<User> optionalTaskToUpdate = userRepository.findById(user.getId());
        if (optionalTaskToUpdate.isPresent()) {
            User userToUpdate = optionalTaskToUpdate.get();
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setTelefone(user.getTelefone());
            userToUpdate.setRole(user.getRole());
            userToUpdate.setStatus(user.getStatus());
            userRepository.save(userToUpdate);
        }
        return "redirect:/listar-usuarios";
    }




}
