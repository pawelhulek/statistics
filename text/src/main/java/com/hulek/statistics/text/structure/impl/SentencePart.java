package com.hulek.statistics.text.structure.impl;

import com.hulek.statistics.text.structure.interfaces.AdvancedMatching;
import com.hulek.statistics.text.structure.interfaces.RealLengthCalculator;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public abstract class SentencePart implements AdvancedMatching, RealLengthCalculator {
    protected final String content;

    protected SentencePart(String content) {
        this.content = content;
    }

    // it should return linkedhashmap to control order
    @Override
    public Map<Character, Long> countMatches(Set<Character> groupingCharacters) {
        Set<Character> uniqueChars = groupingCharacters.stream()
                .map(Character::toLowerCase)
                .collect(Collectors.toSet());
        return content.chars()
                .mapToObj(i -> (char) Character.toLowerCase(i))
                .filter(uniqueChars::contains)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    @Override
    public int length() {
        return content.length();
    }

}
