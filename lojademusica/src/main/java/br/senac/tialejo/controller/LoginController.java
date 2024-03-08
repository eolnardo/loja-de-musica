package br.senac.tialejo.controller;

import br.senac.tialejo.model.Task;
import br.senac.tialejo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        ModelAndView mv = new ModelAndView();

        // Verifica se o usuário existe no banco de dados e se a senha está correta
        Optional<Task> userOptional = taskRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            Task user = userOptional.get();
            if (user.getSenha().equals(senha)) {
                // Autentica o usuário e redireciona para a página principal
                mv.setViewName("redirect:/principal");
                return mv;
            }
        }

        // Se as credenciais estiverem incorretas, exibe uma mensagem de erro e retorna para a página de login
        mv.setViewName("login");
        mv.addObject("error", "Credenciais inválidas. Por favor, tente novamente.");
        return mv;
    }
}


