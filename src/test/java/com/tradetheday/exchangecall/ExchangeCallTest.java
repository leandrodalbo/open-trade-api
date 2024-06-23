package com.tradetheday.exchangecall;

import com.tradetheday.exchanging.Candle;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeCallTest {

    @Test
    void willReturnAnArrayOfThreeCandles() {
        List<Object> data = List.of(
                String.valueOf(Instant.now().getEpochSecond()),
                "23.0",
                "23.0",
                "23.0",
                "23.0"
        );

        assertThat(ExchangeCall.toCandlesArray(List.of(data, data, data)))
                .isEqualTo(
                        new Candle[]{
                                Candle.of(23.0f,
                                        23.0f,
                                        23.0f,
                                        23.0f),
                                Candle.of(23.0f,
                                        23.0f,
                                        23.0f,
                                        23.0f),
                                Candle.of(23.0f,
                                        23.0f,
                                        23.0f,
                                        23.0f)
                        }
                );
    }

    @Test
    void willReturnAndArrayOfNullValues() {
        assertThat(ExchangeCall.toCandlesArray(null)).isEqualTo(new Candle[0]);
        assertThat(ExchangeCall.toCandlesArray(List.of())).isEqualTo(new Candle[0]);
    }
}
