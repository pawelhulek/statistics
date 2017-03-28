package com.hulek.statistics.text.structure.services;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class VowelStatisticsServiceTest {
    private VowelStatisticsService service = new VowelStatisticsService();

    @Test
    public void should_print_stats() {
        List<String> stats = service.generateStatistics("Lorem ipsum et dolorem");
        assertThat(stats).hasSize(4)
                .satisfies(s -> {
                    assertThat(s).element(0).isEqualTo("([e, o], 5) -> 0.4");
                    assertThat(s).element(1).isEqualTo("([u, i], 5) -> 0.4");
                    assertThat(s).element(2).isEqualTo("([e], 2) -> 0.5");
                    assertThat(s).element(3).isEqualTo("([e, o], 7) -> 0.4");
                });
    }

    @Test
    public void should_print_empty_list_when_length_of_word_is_zero() {
        List<String> stats = service.generateStatistics("");
        assertThat(stats).isEmpty();
        List<String> stats2 = service.generateStatistics(" ");
        assertThat(stats2).isEmpty();
        List<String> stats3 = service.generateStatistics("   ");
        assertThat(stats3).isEmpty();
    }

    @Test
    public void should_print_stats_without_match() {
        List<String> stats = service.generateStatistics(".");
        assertThat(stats).element(0).isEqualTo("([], 1) -> 0.0");
    }
}