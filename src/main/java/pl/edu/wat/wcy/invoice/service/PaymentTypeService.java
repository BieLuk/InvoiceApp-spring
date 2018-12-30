package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.PaymentTypeDTO;
import pl.edu.wat.wcy.invoice.model.PaymentType;
import pl.edu.wat.wcy.invoice.repository.PaymentTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentTypeService {

    private final PaymentTypeRepository paymentTypeRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public PaymentTypeDTO getPaymentType(Long paymentTypeId) {
        PaymentType paymentType = paymentTypeRepository.findById(paymentTypeId)
                .orElseThrow( () -> new ResourceNotFoundException("PaymentType not exist id: " + paymentTypeId));
        return modelMapper.map(paymentType, PaymentTypeDTO.class);
    }

    public List<PaymentTypeDTO> getAllPaymentTypes() {
        return paymentTypeRepository.findAll().stream()
                .map(paymentType -> modelMapper.map(paymentType, PaymentTypeDTO.class)).collect(Collectors.toList());
    }

}
