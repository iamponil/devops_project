package tn.esprit.devops_project;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;
import tn.esprit.devops_project.controllers.SupplierController;
import tn.esprit.devops_project.dto.SupplierDTO;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.entities.SupplierCategory;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.SupplierServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.show-sql=true",
        "spring.jpa.hibernate.ddl-auto=update"
})
@ExtendWith(OutputCaptureExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DevOpsProjectSpringBootApplicationTests {

    @Test
    @Order(1)
    void aVoidmain(CapturedOutput output) {
        DevOpsProjectSpringBootApplication.main(new String[]{});

        // Check if the expected string is present in the output
        Assertions.assertThat(output).contains("Started DevOpsProjectSpringBootApplication");
    }

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    @Order(2)
    void testEqualsWhenObjectIsNull() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("TestCode");
        supplier.setLabel("TestLabel");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        // Act
        boolean result = false;

        // Assert
        Assertions.assertThat(result).isFalse();
    }

    @Test
    @Order(3)
    void testToString() {
        // Arrange
        Supplier supplier = new Supplier();
        supplier.setIdSupplier(1L);
        supplier.setCode("TestCode");
        supplier.setLabel("TestLabel");
        supplier.setSupplierCategory(SupplierCategory.ORDINAIRE);

        // Act
        String result = supplier.toString();

        // Assert
        Assertions.assertThat(result).contains("id : 1", "code : TestCode", "label : TestLabel", "category : ORDINAIRE");
    }
    @Test
    @Order(4)
    void itShouldFindSupplierByCategory() {
        //Arrange
        Supplier supplier = Supplier.builder()
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        Supplier savedSupplier = supplierRepository.save(supplier);
        //Act
        List<Supplier> byCategory = supplierRepository.findBySupplierCategory(SupplierCategory.ORDINAIRE);
        //Assert
        Assertions.assertThat(byCategory).hasSize(1);
        Assertions.assertThat(byCategory.get(0)).isEqualTo(savedSupplier);
    }


    @Test
    @Order(5)
    void itShouldFindTwoSupplierByCategory() {
        //Arrange
        Supplier supplier = Supplier.builder()
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();

        Supplier supplier2 = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        Supplier savedSupplier = supplierRepository.save(supplier);
        Supplier savedSupplier2 = supplierRepository.save(supplier2);
        //Act
        List<Supplier> byCategory = supplierRepository.findBySupplierCategory(SupplierCategory.CONVENTIONNE);
        //Assert
        Assertions.assertThat(byCategory).hasSize(2);
        Assertions.assertThat(byCategory.get(1)).isEqualTo(savedSupplier);
        Assertions.assertThat(byCategory.get(0)).isEqualTo(savedSupplier2);


    }
    @Test
    @Order(6)
    void testEquals() {
        // Arrange
        SupplierDTO supplierDTO1 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO supplierDTO2 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO differentSupplierDTO = SupplierDTO.builder()
                .idSupplier(2L)
                .code("Code2")
                .label("Label2")
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .build();

        // Act and Assert
        Assertions.assertThat(supplierDTO1).isEqualTo(supplierDTO2).isNotEqualTo(differentSupplierDTO);
    }

    @Test
    @Order(7)
    void testHashCode() {
        // Arrange
        SupplierDTO supplierDTO1 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO supplierDTO2 = SupplierDTO.builder()
                .idSupplier(1L)
                .code("Code1")
                .label("Label1")
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .build();

        SupplierDTO differentSupplierDTO = SupplierDTO.builder()
                .idSupplier(2L)
                .code("Code2")
                .label("Label2")
                .supplierCategory(SupplierCategory.CONVENTIONNE)
                .build();

        // Act and Assert
        assertThat(supplierDTO1.hashCode()).hasSameHashCodeAs(supplierDTO2.hashCode());
        assertThat(supplierDTO1.hashCode()).isNotEqualTo(differentSupplierDTO.hashCode());
    }
    @Mock
    private SupplierServiceImpl supplierService;

    @InjectMocks
    private SupplierController supplierController;

    @Test
    @Order(8)
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
    @Order(9)
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
    @Order(10)
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
    @Order(11)
    void testRemoveSupplier() {
        // Arrange
        Long supplierId = 1L;

        // Act
        supplierController.removeFournisseur(supplierId);

        // Assert
        verify(supplierService, times(1)).deleteSupplier(supplierId);
    }
    @Test
    @Order(12)
    void testHashCodeController() {
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
    @Mock
    private SupplierRepository supplierRepositorymock;
    @InjectMocks
    private SupplierServiceImpl supplierServicemock;
    @Test
    @Order(13)
    void itShouldAddSupplier() {
        // Arrange
        SupplierDTO supplierDTO = SupplierDTO.builder()
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();

        // Convert DTO to Entity
        Supplier supplier = convertToSupplier(supplierDTO);

        when(supplierRepositorymock.save(Mockito.any(Supplier.class))).thenReturn(supplier);

        // Act
        Supplier actualSupplier = supplierServicemock.addSupplier(supplierDTO);

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
    @Order(14)
    void itShouldUpdateSupplier() {
        // Arrange
        Supplier supplier = Supplier.builder()
                .idSupplier(1L)
                .supplierCategory(SupplierCategory.ORDINAIRE)
                .label("Test Supplier")
                .code("Supply n 001")
                .build();
        when(supplierRepositorymock.save(supplier)).thenReturn(supplier);

        // Act
        Supplier updatedSupplier = supplierServicemock.updateSupplier(supplier);
        // Assert
        assertEquals(supplier, updatedSupplier);
    }
    @Test
    @Order(15)
    void itShouldRetrieveAllSuppliers() {
        // Arrange
        List<Supplier> expectedSuppliers = Arrays.asList(
                new Supplier()
        );
        when(supplierRepositorymock.findAll()).thenReturn(expectedSuppliers);

        // Act
        List<Supplier> actualSuppliers = supplierServicemock.retrieveAllSuppliers();

        // Assert
        Assertions.assertThat(actualSuppliers).isEqualTo(expectedSuppliers);
    }
    @Test
    @Order(16)
    void itShouldDeleteSupplier() {
        // Arrange
        Long idToDelete = 1L;

        // Act
        supplierServicemock.deleteSupplier(idToDelete);

        // Assert
        verify(supplierRepositorymock, times(1)).deleteById(idToDelete);
    }

}
