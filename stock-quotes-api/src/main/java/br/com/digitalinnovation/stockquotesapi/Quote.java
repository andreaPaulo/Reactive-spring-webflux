package br.com.digitalinnovation.stockquotesapi;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Getter @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "QUOTE")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private Double openvalue;
    private Double closeValue;
    private Date timestamp;


}
