package tn.esprit.devops_project;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.TestPropertySource;

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
class DevOpsProjectSpringBootApplicationTests {

    @Test
    void aVoidmain(CapturedOutput output) {
        DevOpsProjectSpringBootApplication.main(new String[]{});

        // Check if the expected string is present in the output
        Assertions.assertThat(output).contains("Started DevOpsProjectSpringBootApplication");
    }
}

