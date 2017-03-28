package com.hulek.statistics.text.structure.impl;

import com.hulek.statistics.text.structure.interfaces.AdvancedMatching;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class WordTest {

    public static final long ONE = 1L;
    private static final long TWO = 2L;

    @Test
    public void should_have_zero_matches() {
        AdvancedMatching word = new Word("anna");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('s', 'd', 'c'));
        assertThat(actual).isEmpty();
    }
    @Test
    public void should_have_one_match_with_one_repeat() {
        AdvancedMatching word = new Word("analise");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('s', 'd', 'c'));
        assertThat(actual).hasSize(1).containsOnlyKeys('s').containsValue(ONE);
    }
    @Test
    public void should_have_one_match_with_multiple_repeats() {
        AdvancedMatching word = new Word("analise");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('a', 'd', 'c'));
        assertThat(actual).hasSize(1).containsOnlyKeys('a').containsValue(TWO);
    }
    @Test
    public void should_have_one_match_with_multiple_repeats_when_chars_has_different_cases() {
        AdvancedMatching word = new Word("Analise");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('a', 'd', 'c'));
        assertThat(actual).hasSize(1).containsOnlyKeys('a').containsValue(TWO);
    }
    @Test
    public void should_have_one_match_with_multiple_repeats_when_chars_has_different_cases_and_matching_set_have_different_cases() {
        AdvancedMatching word = new Word("Analise");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('a','A', 'd', 'c'));
        assertThat(actual).hasSize(1).containsOnlyKeys('a').containsValue(TWO);
    }
    @Test
    public void should_have_multiple_matches_with_single_repeats() {
        AdvancedMatching word = new Word("Fox");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('o', 't', 'c'));
        assertThat(actual).hasSize(1).containsOnlyKeys('o').containsValue(ONE);
    }
    @Test
    public void should_have_multiple_matches_with_multiple_repeats() {
        AdvancedMatching word = new Word("Foxtrot");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('o', 't', 'c'));
        assertThat(actual).hasSize(2).containsOnlyKeys('o','t').containsValue(TWO);
    }
    @Test
    public void should_have_single_match_when_char_sets_have_upper_case_chars() {
        AdvancedMatching word = new Word("fox");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('F'));
        assertThat(actual).hasSize(1).containsOnlyKeys('f').containsValue(ONE);
    }

    @Test
    public void should_have_single_match_with_multiple_repeats_when_given_string_is_lower_and_upper_and_comparable_char_sets_have_upper_and_lower_case_chars() {
        AdvancedMatching word = new Word("fF");
        Map<Character, Long> actual = word.countMatches(Sets.newLinkedHashSet('F','f'));
        assertThat(actual).hasSize(1).containsOnlyKeys('f').containsValue(TWO);
    }

}