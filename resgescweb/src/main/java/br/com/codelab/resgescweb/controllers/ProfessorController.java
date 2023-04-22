package br.com.codelab.resgescweb.controllers;

import br.com.codelab.resgescweb.dto.RequisicaoFormProfessor;
import br.com.codelab.resgescweb.models.Professor;
import br.com.codelab.resgescweb.models.StatusProfessor;
import br.com.codelab.resgescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/professores") //forma de evitar que coloque /professores toda hora, já que tem em todos GetMapping
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("")
    public ModelAndView index(){

        //tá puxando os dados do banco de dados
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(RequisicaoFormProfessor requisicao){

        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("listaStatusProfessor", StatusProfessor.values());

        return mv;
    }

    //há um problema em fazer assim, porque está passando a entidade final, remetendo a um problema de segurança
    //alguém poderia reabilitar/editar na mão um campo que vc desabilitou
    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult){

        //caso não entre com nenhum dado (que será dado como erro)  entra nessa condição
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        }
        else{
            Professor professor = requisicao.toProfessor();
            this.professorRepository.save(professor);

            return new ModelAndView("redirect:/professores/" + professor.getId());
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){

        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){
            Professor professor = optional.get();

            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);

            return mv;
        }
        //não achou um registro na tabela com o id informado
        else{
            return this.retornaErroProfessor( "SHOW ERROR: Professor #" +id+ " não encontrado no banco!");
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao){
        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);

            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("listaStatusProfessor", StatusProfessor.values());

            return mv;
        }
        //não achou um registro na tabela com o id informado
        else{
            return this.retornaErroProfessor( "EDIT ERROR: Professor #" +id+ " não encontrado no banco!");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor requisicao, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", id);
            mv.addObject("listaStatusProfessor", StatusProfessor.values());
            return mv;
        }
        else{
            Optional<Professor> optional = this.professorRepository.findById(id);

            if(optional.isPresent()){
                Professor professor = requisicao.toProfessor(optional.get());
                this.professorRepository.save(professor);

                return new ModelAndView("redirect:/professores/" + professor.getId());
            }
            else{
                return this.retornaErroProfessor( "UPDATE ERROR: Professor #" +id+ " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
            ModelAndView mv = new ModelAndView("redirect:/professores");

        try{
            this.professorRepository.deleteById(id);
            mv.addObject("mensagem", "Professor #" +id+ " deletado com sucesso!");
            mv.addObject("erro","false");
        }
        catch (EmptyResultDataAccessException e){
            mv = this.retornaErroProfessor( "DELETE ERRO: Professor #" +id+ " não encontrado no banco!");
        }
        return mv;
    }

    private ModelAndView retornaErroProfessor(String msg){
        ModelAndView mv = new ModelAndView("redirect:/professores");
        mv.addObject("mensagem", msg);
        mv.addObject("erro","true");
        return mv;
    }
}

