package br.com.fiap.checkpoint1.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedido")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório.")
    private String clienteNome;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dataPedido;

    @PositiveOrZero(message = "O valor total não pode ser negativo.")
    private double valorTotal;

    @PrePersist
    public void preencherDataPedido() {
        this.dataPedido = LocalDate.now();
    }
}