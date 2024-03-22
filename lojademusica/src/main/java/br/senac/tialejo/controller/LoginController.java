package br.senac.tialejo.controller;

import br.senac.tialejo.model.User;
import br.senac.tialejo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static Long ID;

    private static String role;

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        LoginController.role = role;
    }

    public static Long getID() {
        return ID;
    }

    public static void setID(Long ID) {
        LoginController.ID = ID;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        ModelAndView mv = new ModelAndView();

        // Verifica se o usuário existe no banco de dados e se a senha está correta
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            setID(user.getId());

            System.out.println(getID());

            System.out.println(user.getRole());

            setRole(user.getRole());

            System.out.println("senha digitada: " + senha);
            System.out.println("Senha do Usuário: " + user.getSenha());

            if (passwordEncoder.matches(senha, user.getSenha()) && user.getRole().equals("ADMIN")) { // <- verificando senha criptografada
                // Autentica o usuário e redireciona para a página principal
                mv.setViewName("redirect:/principal2");
                return mv;
            } else {
                System.out.println("estou aqui");
                mv.setViewName("redirect:/login");
                return mv;
            }
        }

        // Se as credenciais estiverem incorretas, exibe uma mensagem de erro e retorna para a página de login
        mv.setViewName("login");
        mv.addObject("error", "Credenciais inválidas. Por favor, tente novamente.");
        return mv;
    }
}


