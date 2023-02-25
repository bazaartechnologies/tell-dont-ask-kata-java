package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class OrderCreationUseCase {
    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public void run(SellItemsRequest request) {
        it.gabrieletondi.telldontaskkata.domain.Order order = new it.gabrieletondi.telldontaskkata.domain.Order();
        order.initializeOrder();

        for (SellItemRequest itemRequest : request.getRequests()) {
            Product product = productCatalog.getByName(itemRequest.getProductName());

            if (product == null) {
                throw new UnknownProductException();
            }

            order.addItem(getOrderItem(itemRequest, product));

            final BigDecimal taxedAmount = itemRequest.getTaxedAmount(product.getUnitaryTaxedAmount());
            final BigDecimal taxAmount = itemRequest.getTaxAmount(product.getUnitaryTax());

            order.addTotal(taxedAmount);
            order.addTax(taxAmount);
        }

        orderRepository.save(order);
    }

    private it.gabrieletondi.telldontaskkata.domain.OrderItem getOrderItem(it.gabrieletondi.telldontaskkata.useCase.SellItemRequest itemRequest, it.gabrieletondi.telldontaskkata.domain.Product product) {
        final it.gabrieletondi.telldontaskkata.domain.OrderItem orderItem = new it.gabrieletondi.telldontaskkata.domain.OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(itemRequest.getQuantity());
        orderItem.setTax(itemRequest.getTaxAmount(product.getUnitaryTax()));
        orderItem.setTaxedAmount(itemRequest.getTaxedAmount(product.getUnitaryTaxedAmount()));
        return orderItem;
    }

}
