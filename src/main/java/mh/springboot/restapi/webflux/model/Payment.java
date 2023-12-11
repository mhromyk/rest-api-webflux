package mh.springboot.restapi.webflux.model;

import java.time.LocalDateTime;

public record Payment(String paymentId, double amount, LocalDateTime paymentDate) {
}
