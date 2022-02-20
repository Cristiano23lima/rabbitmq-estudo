package com.cristiano.vendas.domain;

import com.cristiano.vendas.domain.enums.StatusVenda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class Venda {
    private Double price;
    private Integer amount;
    private StatusVenda status;
}
