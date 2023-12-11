package mh.springboot.restapi.webflux.controller;

import mh.springboot.restapi.webflux.model.Payment;
import mh.springboot.restapi.webflux.service.PaymentProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class PaymentController {

    private final Logger logger = Logger.getLogger(PaymentController.class.getName());
    private final PaymentProxy paymentProxy;

    public PaymentController(PaymentProxy paymentProxy) {
        this.paymentProxy = paymentProxy;
    }

    @PostMapping("/pay")
    public Mono<Payment> makePayment(@RequestBody Payment payment) {
        String requestId = UUID.randomUUID().toString();
        logger.info("Received payment request on : " + payment + "; Request has got the following requestId=" + requestId);
        Mono<Payment> result = paymentProxy.createPayment(requestId, payment);
        logger.info("External payment system returned the following respond body: " + result);
        return result;
    }
}
