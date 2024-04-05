package br.senac.tialejo.controller;

import br.senac.tialejo.model.Produto;
import br.senac.tialejo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @GetMapping("principal2")
    public String principal(){
        return "principal2";
    }

    @GetMapping("tela-produto")
    public String telaProduto(){return "tela-produto";}

    @GetMapping("/cadastrar-produto")
    public ModelAndView cadastro(){
        ModelAndView mv = new ModelAndView("cadastrar-produto");
        mv.addObject("produto", new Produto());
        return mv;

    }

    @PostMapping("/cadastrar-produto")
    public ModelAndView cadastrarProduto(@ModelAttribute @Validated Produto produto, BindingResult result) {
        ModelAndView mv = new ModelAndView("cadastrar-produto");



        if (result.hasErrors()) {
            // Se houver erros de validação, retorna para a mesma página de cadastro com os erros
            return mv;
        }

        produtoRepository.save(produto);

        // Redireciona para a página principal após o cadastro bem-sucedido


        return new ModelAndView("redirect:/principal2");
    }

    @GetMapping("/listar-produtos")
    public ModelAndView listar(@RequestParam(name = "q", required = false) String query) {
       // Por exemplo, você pode passá-la para o repositório de produtos para obter produtos correspondentes à consulta

        Iterable<Produto> produtos = produtoRepository.findAll();

        if (query != null && !query.isEmpty()) {
            produtos = produtoRepository.findAll();

            ArrayList<Produto> produtosEncontrados = new ArrayList<>();

            for (Produto produto: produtos
                 ) {
                    if(produto.getNome().contains(query)) produtosEncontrados.add(produto);
            }

            produtos = produtosEncontrados;
        } else {
            // Se não houver consulta, apenas liste todos os produtos
            produtos = produtoRepository.findAll();
        }

        ModelAndView mv = new ModelAndView("listar-produtos.html");
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/listar-produtos-estoquista")
    public ModelAndView listarEstoquista(@RequestParam(name = "q", required = false) String query) {
        // Por exemplo, você pode passá-la para o repositório de produtos para obter produtos correspondentes à consulta

        Iterable<Produto> produtos = produtoRepository.findAll();

        if (query != null && !query.isEmpty()) {
            produtos = produtoRepository.findAll();

            ArrayList<Produto> produtosEncontrados = new ArrayList<>();

            for (Produto produto: produtos
            ) {
                if(produto.getNome().contains(query)) produtosEncontrados.add(produto);
            }

            produtos = produtosEncontrados;
        } else {
            // Se não houver consulta, apenas liste todos os produtos
            produtos = produtoRepository.findAll();
        }

        ModelAndView mv = new ModelAndView("listar-produtos-estoquista.html");
        mv.addObject("produtos", produtos);
        return mv;
    }

    @GetMapping("/editProduto/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("editProduto");
        Optional<Produto> produtoFinder = produtoRepository.findById(id);

        if(produtoFinder.isPresent()) {
            Produto produto = produtoFinder.get();
            mv.addObject("produto", produto);
        }

        return mv;
    }

    @GetMapping("/editProduto-estoquista/{id}")
    public ModelAndView editEstoquista(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("editProduto");
        Optional<Produto> produtoFinder = produtoRepository.findById(id);

        if(produtoFinder.isPresent()) {
            Produto produto = produtoFinder.get();
            mv.addObject("produto", produto);
        }

        return mv;
    }

    @PostMapping("/editProduto")
    public String update(Produto produto) {
        Optional<Produto> optionalprodutoToUpdate = produtoRepository.findById(produto.getId());
        if (optionalprodutoToUpdate.isPresent()) {
            Produto produtoToUpdate = optionalprodutoToUpdate.get();
            produtoToUpdate.setNome(produto.getNome());
            produtoToUpdate.setDescricao(produto.getDescricao());
            produtoToUpdate.setAvaliacao(produto.getAvaliacao());
            produtoToUpdate.setStatus(produto.isStatus());
            produtoToUpdate.setPreco(produto.getPreco());
            produtoToUpdate.setQuantidade(produto.getQuantidade());
            produtoRepository.save(produtoToUpdate);
        }


        if (LoginController.getRole().equalsIgnoreCase("Estoquista")) return "redirect:/listar-produtos-estoquista";

        return "redirect:/listar-produtos";
    }

    @GetMapping("/toggleStatus")
    public String toggleStatus(@RequestParam Long id, @RequestParam String action) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            if ("reactivate".equals(action)) {
                produto.setStatus(true);
            } else if ("deactivate".equals(action)) {
                produto.setStatus(false);
            }
            produtoRepository.save(produto);
        }
        return "redirect:/listar-produtos";
    }

    @GetMapping("/toggleStatusEstoquista")
    public String toggleStatusEstoquista(@RequestParam Long id, @RequestParam String action) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            if ("reactivate".equals(action)) {
                produto.setStatus(true);
            } else if ("deactivate".equals(action)) {
                produto.setStatus(false);
            }
            produtoRepository.save(produto);
        }
        return "redirect:/listar-produtos-estoquista";
    }

    @GetMapping("/produto/{id}")
    public String delete(@PathVariable Long id){

        produtoRepository.deleteById(id);

        return "redirect:/listar-produtos";
    }

}
