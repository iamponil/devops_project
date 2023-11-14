package tn.esprit.devops_project.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.services_interface.ISupplierService;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SupplierServiceImpl implements ISupplierService {

	SupplierRepository supplierRepository;

	@Override
	public List<Supplier> retrieveAllSuppliers() {
		return supplierRepository.findAll();
	}


	@Override
	public Supplier addSupplier(SupplierDTO supplierDTO) {
		Supplier supplier = convertToSupplier(supplierDTO);
		return supplierRepository.save(supplier);
	}
	private Supplier convertToSupplier(SupplierDTO supplierDTO) {
		// Create a new Supplier and set its properties from the SupplierDTO
		return Supplier.builder()
				.code(supplierDTO.getCode())
				.label(supplierDTO.getLabel())
				.supplierCategory(supplierDTO.getSupplierCategory())
				.build();
	}
	@Override
	public Supplier updateSupplier(Supplier supplier) {
		return  supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplier(Long supplierId) {
		supplierRepository.deleteById(supplierId);

	}



	@Override
	public Supplier retrieveSupplier(Long supplierId) {

		return supplierRepository.findById(supplierId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + supplierId));
	}


}