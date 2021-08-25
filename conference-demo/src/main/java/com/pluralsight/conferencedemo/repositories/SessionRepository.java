package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* O Spring JPA Repository é uma interface que nos permite acessar dados a partir queries JPA
* criadas automaticamente a partir do nome de um método.
* Observe que não há implementação da interface JpaRepository! O Spring faz a implementação dela
* por debaixo dos panos, só preciso informar qual a entidade (classe) a ser gerenciada e o tipo do
* atributo id (aquele que possui a anotação @Id).
* Qual a diferença entre JpaRepository e CrudRepository?
* A estrutura do Spring-Data é formada assim: JpaRepository > CrudRepository > PagingAndSorting
* A JpaRepository estende PagingAndSorting e CrudRepository, ou seja, é a mais genérica possível.
* Ver mais em: https://www.tutorialspoint.com/difference-between-crudrepository-and-jparepository-in-java
*/

public interface SessionRepository extends JpaRepository<Session, Long> { }