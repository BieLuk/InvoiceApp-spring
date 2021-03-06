package pl.edu.wat.wcy.invoice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wat.wcy.invoice.dto.VatTypeDTO;
import pl.edu.wat.wcy.invoice.model.VatType;
import pl.edu.wat.wcy.invoice.repository.VatTypeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VatTypeService {

    private final VatTypeRepository vatTypeRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public VatTypeDTO getVatType(Long vatTypeId) {
        VatType vatType = vatTypeRepository.findById(vatTypeId)
                .orElseThrow( () -> new ResourceNotFoundException("VatType not exist id: " + vatTypeId));
        return modelMapper.map(vatType, VatTypeDTO.class);
    }

    public List<VatTypeDTO> getAllPaymentTypes() {
        return vatTypeRepository.findAll().stream()
                .map(vatType -> modelMapper.map(vatType, VatTypeDTO.class)).collect(Collectors.toList());
    }
}
