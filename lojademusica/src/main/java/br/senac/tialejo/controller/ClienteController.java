package br.senac.tialejo.controller;

import br.senac.tialejo.model.Cliente;
import br.senac.tialejo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/loginCliente")
    public ModelAndView loginCliente(){
        ModelAndView mv = new ModelAndView("loginCliente");

        return mv;

    }

    @GetMapping("/cadastro-cliente")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("cadastro-cliente");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/cadastro-cliente")
    public ModelAndView cadastrarCliente(@ModelAttribute @Validated Cliente cliente, BindingResult result) {
        ModelAndView mv = new ModelAndView("cadastro-cliente");

        if (!cliente.getSenha().equals(cliente.getConfirmaSenha())) {
            result.rejectValue("confirmaSenha", "error.user", "As senhas não coincidem");
        }

        if (result.hasErrors()) {
            // Se houver erros de validação, retorna para a mesma página de cadastro com os erros
            System.out.println("o erro ta aquu");
            return mv;

        }

        //criptografando antes de salvar
        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);

        // Lógica para salvar o usuário no banco de dados
        clienteRepository.save(cliente);

        // Redireciona para a página principal após o cadastro bem-sucedido
        return new ModelAndView("redirect:/loginCliente");
    }
}
