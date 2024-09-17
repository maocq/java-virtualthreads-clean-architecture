package co.com.bancolombia.api;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.usecase.scenarios.ScenariosUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {

    private final ScenariosUseCase scenariosUseCase;

    @GetMapping(path = "/case-one")
    public User caseOne(@RequestParam(name = "latency") Optional<Integer> latency) {
        var latencyValue = latency.orElse(0);
        return scenariosUseCase.caseOne(latencyValue);
    }
}
