package com.open.trade.service;

import com.open.trade.model.Opportunity;
import com.open.trade.model.Speed;
import com.open.trade.repository.OpportunityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OpportunitiesServiceTest {

    @Mock
    OpportunityRepository repository;


    @InjectMocks
    OpportunitiesService service;

    @Test
    void findLatestEntries() {
        when(repository.findAll()).thenReturn(
                Flux.just(Opportunity.of(
                                "BTCUSDT",
                                Speed.HIGH,
                                true,
                                3000.00F,
                                3000.00F,
                                3000.00F,
                                false,
                                0.0f,
                                0.0f,
                                0.0f
                        ),
                        new Opportunity(
                                Opportunity.generateSimbolSpeed("BTCUSDT", Speed.HIGH),
                                true,
                                3000.00F,
                                3000.00F,
                                3000.00F,
                                false,
                                0.0f,
                                0.0f,
                                0.0f,
                                Instant.now().minus(5, ChronoUnit.HOURS).getEpochSecond(),
                                0
                        ))
        );

        Flux<Opportunity> result = service.findLatestEntries();

        StepVerifier.create(result)
                .thenConsumeWhile(it -> it.ondatetime() > Instant.now().minus(1, ChronoUnit.HOURS).getEpochSecond());

        verify(repository, times(1)).findAll();
    }
}
