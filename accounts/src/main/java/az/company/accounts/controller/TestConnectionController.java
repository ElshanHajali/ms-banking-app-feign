package az.company.accounts.controller;

import az.company.accounts.config.TestConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/test")
@RequiredArgsConstructor
public class TestConnectionController {
    private final TestConfiguration testConfiguration;

    @GetMapping
    public String test() {
        return testConfiguration.getTest();
    }
}
