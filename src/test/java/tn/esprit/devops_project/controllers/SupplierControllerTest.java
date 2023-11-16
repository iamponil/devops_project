package tn.esprit.devops_project.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class SupplierControllerTest {

    @Mock
    private SupplierServiceImpl supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @Test
    void testGetSuppliers() {
        // Arrange
        List<Supplier> expectedSuppliers = Arrays.asList(new Supplier(), new Supplier());
        when(supplierService.retrieveAllSuppliers()).thenReturn(expectedSuppliers);

        // Act
        List<Supplier> actualSuppliers = supplierController.getSuppliers();

        // Assert
        assertThat(actualSuppliers).isEqualTo(expectedSuppliers);
    }

    @Test
    void testAddSupplier() {
        // Arrange
        SupplierDTO supplierDTO = SupplierDTO.builder()
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        Supplier expectedSupplier = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();

        when(supplierService.addSupplier(supplierDTO)).thenReturn(expectedSupplier);

        // Act
        Supplier actualSupplier = supplierController.addSupplier(supplierDTO);

        // Assert
        assertThat(actualSupplier).isEqualTo(expectedSupplier);
    }

    @Test
    void testRetrieveSupplier() {
        // Arrange
        Long supplierId = 1L;
        Supplier expectedSupplier = new Supplier();
        when(supplierService.retrieveSupplier(supplierId)).thenReturn(expectedSupplier);

        // Act
        Supplier actualSupplier = supplierController.retrieveSupplier(supplierId);

        // Assert
        assertThat(actualSupplier).isEqualTo(expectedSupplier);
    }

    @Test
    void testRemoveSupplier() {
        // Arrange
        Long supplierId = 1L;

        // Act
        supplierController.removeFournisseur(supplierId);

        // Assert
        verify(supplierService, times(1)).deleteSupplier(supplierId);
    }
    @Test
    void testHashCode() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("TestCode");
        supplier.setLabel("TestLabel");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        // Act
        int hashCode = supplier.hashCode();

        // Assert
        assertThat(hashCode).isNotZero();
    }
}
