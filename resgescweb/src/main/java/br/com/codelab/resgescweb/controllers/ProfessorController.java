package br.com.codelab.resgescweb.controllers;

import br.com.codelab.resgescweb.dto.RequisicaoNovoProfessor;
import br.com.codelab.resgescweb.models.Professor;
import br.com.codelab.resgescweb.models.StatusProfessor;
import br.com.codelab.resgescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professores")
    public ModelAndView index(){

        //tá puxando os dados do banco de dados
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView nnew(){

        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());

        return mv;
    }

    //há um problema em fazer assim, porque está passando a entidade final, remetendo a um problema de segurança
    //alguém poderia reabilitar/editar na mão um campo que vc desabilitou
    @PostMapping("/professores")
    public String create(@Valid RequisicaoNovoProfessor requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){ //caso não entre com nenhum dado (que será dado como erro)  entra nessa condição


            return "redirect:/professor/new";
        }
        else{
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);

            return "redirect:/professores";
        }
    }
}

