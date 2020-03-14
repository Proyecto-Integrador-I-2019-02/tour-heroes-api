package co.udea.heroes.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "heroes")
public class Hero {
    @Id
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "nombre")
    private String name;

    public Hero(){

    }

    public Hero(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String Name){
        this.name = Name;
    }
}
