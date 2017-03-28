package com.hulek.statistics.text.structure.impl;

import com.hulek.statistics.text.structure.interfaces.AdvancedMatching;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class Word implements AdvancedMatching {
    private final String content;

    public Word(String content) {
        this.content = content.toLowerCase();
    }

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

    public int length() {
        return content.length();
    }


}
