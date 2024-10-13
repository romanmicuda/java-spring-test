package sk.uteg.springdatatest.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "campaign", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Question> questions;

    public Campaign() {
        this.questions = new ArrayList<>();
    }
}
