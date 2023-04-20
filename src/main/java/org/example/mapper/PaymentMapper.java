package org.example.mapper;

import org.example.dto.PaymentDto;
import org.example.model.Payment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/*@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI, uses = {CustomerMapper.class,
        StaffMapper.class, RentalMapper.class})*/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.CDI)

public interface PaymentMapper extends BaseMapper<PaymentDto, Payment> {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    //TODO->update these attributes whenever need to insert new payment.
    @Mapping(source = "customerId", target = "customer.id")
    Payment toEntity(PaymentDto paymentDto);

    @Mapping(source = "customer.id", target = "customerId")
    PaymentDto toDto(Payment payment);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "customerId", target = "customer.id")
    void partialUpdate(@MappingTarget Payment payment, PaymentDto paymentDto);
}