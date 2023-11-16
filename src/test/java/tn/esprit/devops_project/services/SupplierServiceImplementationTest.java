package tn.esprit.devops_project.services;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class SupplierServiceImplementationTest {
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierServiceImpl supplierService;

   @Test
   void itShouldAddSupplier() {
      // Arrange
      SupplierDTO supplierDTO = SupplierDTO.builder()
              .supplierCategory(SupplierCategory.ORDINAIRE)
              .label("Test Supplier")
              .code("Supply n 001")
              .build();

      // Convert DTO to Entity
      Supplier supplier = convertToSupplier(supplierDTO);

      when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);

      // Act
      Supplier actualSupplier = supplierService.addSupplier(supplierDTO);

      // Assert
      Assertions.assertThat(actualSupplier).isEqualTo(supplier);
   }

   // Conversion method
   private Supplier convertToSupplier(SupplierDTO supplierDTO) {
      return Supplier.builder()
              .supplierCategory(supplierDTO.getSupplierCategory())
              .label(supplierDTO.getLabel())
              .code(supplierDTO.getCode())
              .build();
   }

   @Test
     void itShouldUpdateSupplier() {
        // Arrange
        Supplier supplier = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        when(supplierRepository.save(supplier)).thenReturn(supplier);

        // Act
        Supplier updatedSupplier = supplierService.updateSupplier(supplier);
        // Assert
        assertEquals(supplier, updatedSupplier);
    }
    @Test
     void itShouldRetrieveAllSuppliers() {
        // Arrange
        List<Supplier> expectedSuppliers = Arrays.asList(
                new Supplier()
        );
        when(supplierRepository.findAll()).thenReturn(expectedSuppliers);

        // Act
        List<Supplier> actualSuppliers = supplierService.retrieveAllSuppliers();

        // Assert
        Assertions.assertThat(actualSuppliers).isEqualTo(expectedSuppliers);
    }
    @Test
     void itShouldDeleteSupplier() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        supplierService.deleteSupplier(idToDelete);

        // Assert
        verify(supplierRepository, times(1)).deleteById(idToDelete);
    }

}