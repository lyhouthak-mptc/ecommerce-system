package co.istad.lyhout.ecommerce.customer.domain.usecase;

import co.istad.lyhout.ecommerce.core.PageResponse;
import co.istad.lyhout.ecommerce.customer.domain.dto.CustomerResult;
import co.istad.lyhout.ecommerce.customer.domain.dto.GetCustomersQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomersUseCase {
    PageResponse<CustomerResult> execute(GetCustomersQuery query) {
        log.info("Executing GetAllCustomerUseCase {}", query );
        return null;
    }
}
