package com.hulek.statistics.text.structure.services;

import com.hulek.statistics.text.structure.impl.SentencePart;
import com.hulek.statistics.text.structure.impl.Word;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class VowelStatisticsService {
    private static final String WHITESPACE = " ";
    private static final Set<Character> VOWELS = new HashSet<>();

    {
        VOWELS.add('A');
        VOWELS.add('E');
        VOWELS.add('I');
        VOWELS.add('O');
        VOWELS.add('U');
        VOWELS.add('Y');
    }

    public List<String> generateStatistics(final String content) {
        String contentCopy = content.trim();
        if (contentCopy.isEmpty())
            return Collections.emptyList();
        return Stream.of(contentCopy.split(WHITESPACE))
                .map(Word::new)
                .map(this::printRowStats)
                .collect(Collectors.toList());
    }

    //arithmetic will be printed in scale 1
    private String printRowStats(SentencePart sentencePart) {
        Map<Character, Long> stats = sentencePart.countMatches(VOWELS);
        BigDecimal sum = BigDecimal.valueOf(
                stats.values()
                        .stream()
                        .mapToLong(Long::longValue)
                        .sum());
        BigDecimal length = BigDecimal.valueOf(sentencePart.length());
        return String.format("(%s, %s) -> %s", stats.keySet().toString(),
                length,
                sum.divide(length, 1, BigDecimal.ROUND_DOWN).toPlainString());
    }
}
