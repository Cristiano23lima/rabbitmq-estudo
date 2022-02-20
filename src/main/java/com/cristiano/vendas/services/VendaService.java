package com.cristiano.vendas.services;

import com.cristiano.vendas.domain.Venda;

public interface VendaService {
    Venda paySale(Venda salePending);
}
