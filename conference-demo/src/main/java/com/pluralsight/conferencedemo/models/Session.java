/* Na arquitetura MVC, model guarda uma representação dos dados no sistema.
* Por exemplo, existem sessões de palestras nessa aplicação, logo existe uma classe Session
* no model dessa aplicação.
*/
package com.pluralsight.conferencedemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/*
 * @Entity indica que a classe é uma entidade JPA, ou seja, a classe está associada a uma tabela.
 * Se não houvesse especificação do nome da tabela, ou seja, se não fosse @Entity(name="sessions"), mas
 * somente @Entity, haveria uma tabela no banco de dados com o nome Sessions, mas como em DBs não usamos CamelCase
 * é melhor usar a especificação do nome da tabela dessa forma: @Entity(name="sessions").
 * Também posso usar:
 * @Entity
 * @Table(name = "sessions")
 */

@Entity(name = "sessions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session {

    /*
    * Observe que os tipos são sempre a classe wrapper, pois os dados dessas variáveis serão inseridos
    * no banco de dados. E não use camelCase, use o exato nome da variável cadastrada nas colunas
    * do banco de dados, dessa forma, não precisamos fazer a anotação delas! Colocando @Column(name="nome_como_esta_no_db") em cada atributo.
    */

    /*
     * @Id é uma anotação que identificam o atributo
     * session_id como primary key do banco de dados.
     */
    @Id

    /*
     * A chave primária pode ser gerada manualmente ou automaticamente.
     * @GeneratedValue(strategy = GenerationType.IDENTITY) é uma anotação que gera a chave primária automaticamente
     * com base em quatro estratégias:
     * 
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Faz o id ser gerado automaticamente.
    private Long session_id;
    private String session_name; //Toda a String será transformada em varchar(255) no banco de dados.
    private String session_description;
    private Integer session_length;

    @ManyToMany
    @JoinTable(
            name = "session_speakers",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id"))
    private List<Speaker> speakers;

    public Session(){ }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public String getSession_name() {
        return session_name;
    }

    public void setSession_name(String session_name) {
        this.session_name = session_name;
    }

    public String getSession_description() {
        return session_description;
    }

    public void setSession_description(String session_description) {
        this.session_description = session_description;
    }

    public Integer getSession_length() {
        return session_length;
    }

    public void setSession_length(Integer session_length) {
        this.session_length = session_length;
    }
}
