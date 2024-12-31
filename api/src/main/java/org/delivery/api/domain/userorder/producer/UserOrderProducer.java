package org.delivery.api.domain.userorder.producer;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.rabbitmq.Producer;
import org.delivery.common.message.model.UserOrderMessage;
import org.delivery.db.userorder.UserOrderEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderProducer {

    private final Producer producer;

    private static final String exchange = "delivery.exchange";
    private static final String routeKey = "delivery.key";

    public void sendOrder(UserOrderEntity userOrderEntity) {
        this.sendOrder(userOrderEntity.getId());
    }

    public void sendOrder(Long userOrderId) {
        UserOrderMessage message = UserOrderMessage.builder()
                .userOrderId(userOrderId)
                .build();

        producer.producer(exchange, routeKey, message);
    }
}
