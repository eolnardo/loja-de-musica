package br.senac.tialejo.controller;

import br.senac.tialejo.model.Cliente;
import br.senac.tialejo.model.User;
import br.senac.tialejo.repository.ClienteRepository;
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

    @GetMapping("/editCliente/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("editCliente");
        Optional<Cliente> taskFinder = clienteRepository.findById(id);

        if(taskFinder.isPresent()) {
            Cliente cliente = taskFinder.get();
            mv.addObject("cliente", cliente);
        }

        return mv;
    }

    @PostMapping("/editCliente")
    public String update(Cliente cliente) {
        Optional<Cliente> optionalTaskToUpdate = clienteRepository.findById(cliente.getId());
        if (optionalTaskToUpdate.isPresent()) {
            Cliente userToUpdate = optionalTaskToUpdate.get();
            userToUpdate.setName(cliente.getName());
            userToUpdate.setGenero(cliente.getGenero());
            userToUpdate.setTelefone(cliente.getTelefone());
            userToUpdate.setSenha(cliente.getSenha());
            userToUpdate.setConfirmaSenha(cliente.getConfirmaSenha());
            clienteRepository.save(userToUpdate);
        }

        return "redirect:/landingPage";
    }

}