package az.company.loans;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RequiredArgsConstructor
@SpringBootApplication
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
