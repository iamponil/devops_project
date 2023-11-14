package tn.esprit.devops_project.services;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.entities.*;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SupplierServiceImplementationTest {
    @Mock
    private SupplierRepository supplierRepository;
    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Test
    public void itShouldAddSupplier() {
        // Arrange
        Supplier supplier = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);
        //Act
        Supplier supplier2 = supplierService.addSupplier(supplier);
        //Assert
        Assertions.assertThat(supplier2).isEqualTo(supplier);
    }
    @Test
    public void itShouldUpdateSupplier() {
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
    public void itShouldRetrieveAllSuppliers() {
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
    public void itShouldDeleteSupplier() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        supplierService.deleteSupplier(idToDelete);

        // Assert
        verify(supplierRepository, times(1)).deleteById(idToDelete);
    }

}