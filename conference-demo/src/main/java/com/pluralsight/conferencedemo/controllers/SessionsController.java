/*
* Na arquitetura MVC, a classe controller direciona solicitações vindas dos usuários para a aplicação e vice versa.
* É aqui que construímos nossa API REST com os verbos http get, post (create), put (update), delete.
* Uma API REST deve conter as seguintes características:
* -Dados e funcionalidades são considerados recursos da API;
* -Os recursos são manipulados com um conjunto fixo de operações;
* -Os recursos são representados de multiplas formas (JSON, XML, HTML, ...);
* -Nada é armazenado na API, apenas no banco de dados; (stateless)
*/
package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
 * @RestController é uma anotação que indica para o Spring que essa classe deve ser usada na manipulação
 * de requisições http e que os dados a serem trocados entre cliente e servidor são do tipo JSON.
 * @EnableWebMVC é outra anotação que pode ser utilizada para informar ao Spring que o padrão MVC está sendo utilizado,
 * mas é desnecessário, visto que ao utilizar @RestController, o @EnableWebMVC é aplicado automaticamente pelo Spring.
 * @RestController = @Controller + @ResponseBody
 */
@RestController

/*
 * @RequestMapping("/") é uma anotação que provê ao Spring informações de roteamento, ou seja, quando houver alguma
 * requisição http nessa rota, o Spring saberá qual classe usar.
 */
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping("/{id}")
    //@RequestMapping("{id}")
    //@RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Session get(@PathVariable Long id){
        return sessionRepository.getById(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        //Atribuir final a uma variável, significa que não é permitido alterar a mesma no método.
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value="{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session){

        //TODO: Adicione validações para todos os atributos que serão passados, caso contrário retorna uma resposta 400 bad request do db.
        //400 bad request significa que  que o servidor não entendeu a requisição pois está com uma sintaxe inválida.

        Session existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);

    }


}
