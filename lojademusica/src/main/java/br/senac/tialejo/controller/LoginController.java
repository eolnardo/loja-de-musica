package br.senac.tialejo.controller;

import br.senac.tialejo.model.Cliente;
import br.senac.tialejo.model.User;
import br.senac.tialejo.repository.ClienteRepository;
import br.senac.tialejo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("landingPage")
    public String landingPage(){
        return "landingPage";
    }

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

            System.out.println("email:" + user.getEmail());
            System.out.println("senha digitada: " + senha);
            System.out.println("Senha do Usuário: " + user.getSenha());

            if (passwordEncoder.matches(senha, user.getSenha())) { // <- verificando senha criptografada
                // Autentica o usuário e redireciona para a página principal
                if(user.getRole().equalsIgnoreCase("ADMIN")) {
                    mv.setViewName("redirect:/principal2");
                    return mv;
                } else if (user.getRole().equalsIgnoreCase("ESTOQUISTA")) {
                    System.out.println("estou no estoquista");
                    mv.setViewName("redirect:/principal");
                    return mv;
                }
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

    @PostMapping("/loginCliente")
    public ModelAndView loginCliente(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        ModelAndView mv = new ModelAndView();

        // Verifica se o usuário existe no banco de dados e se a senha está correta
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            setID(cliente.getId());

            System.out.println(getID());

            System.out.println("email:" + cliente.getEmail());
            System.out.println("senha digitada: " + senha);
            System.out.println("Senha do Usuário: " + cliente.getSenha());

            if (passwordEncoder.matches(senha, cliente.getSenha())) { // <- verificando senha criptografada
                // Autentica o usuário e redireciona para a página principal
                    mv.setViewName("redirect:/landingPage");
                    return mv;
            } else {
                System.out.println("estou aqui");
                mv.setViewName("redirect:/loginCliente");
                return mv;
            }
        }

        // Se as credenciais estiverem incorretas, exibe uma mensagem de erro e retorna para a página de login
        mv.setViewName("loginCliente");
        mv.addObject("error", "Credenciais inválidas. Por favor, tente novamente.");
        return mv;
    }


}


