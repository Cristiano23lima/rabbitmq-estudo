package com.cristiano.vendas.services.impls;

import com.cristiano.vendas.domain.Venda;
import com.cristiano.vendas.domain.enums.StatusVenda;
import com.cristiano.vendas.services.VendaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class VendaServiceImpl implements VendaService {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue;

    @Override
    public Venda paySale(Venda salePending) {
        Venda vendaPay = Venda.builder().setAmount(salePending.getAmount()).setPrice(salePending.getPrice()).setStatus(StatusVenda.PAY).build();
        
        this.producerMessageBroker(vendaPay);
        
        return vendaPay;
    }

    @RabbitListener(queues = {"${spring.rabbitmq.template.default-receive-queue}"})
    public void consumerMessageBroker(@Payload String venda){
        System.out.println("Mensagem consumida: "+venda);
    }

    private void producerMessageBroker(Venda message){
        try{
            rabbitTemplate.convertAndSend(this.queue.getName(), this.jsonToString(message));
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    private String jsonToString(Object json) throws Exception{
        return new ObjectMapper().writeValueAsString(json);
    }

}
