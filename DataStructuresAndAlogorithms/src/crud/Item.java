package crud;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.AUTO;

@Entity
//@Table(name = "items") if name different
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long itemId;

    @Column(name = "description", nullable = false)//if name different
    @NotEmpty
    private Long desc;

    @NotBlank
    @NotNull
    @Column
    private String name;
}
