package sk.uteg.springdatatest.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false)
    private Campaign campaign;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "feedback")
    private List<Answer> answers;

    public Feedback (){
        this.answers = new ArrayList<>();
    }

}
