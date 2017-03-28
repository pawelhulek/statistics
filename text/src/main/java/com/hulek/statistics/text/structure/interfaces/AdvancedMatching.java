package com.hulek.statistics.text.structure.interfaces;

import java.util.Map;
import java.util.Set;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public interface AdvancedMatching {
    Map<Character, Long> countMatches(Set<Character> groupingCharacters);
}
