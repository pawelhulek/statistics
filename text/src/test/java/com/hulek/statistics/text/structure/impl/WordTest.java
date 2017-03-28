package com.hulek.statistics.text.structure.impl;

import com.hulek.statistics.text.structure.exceptions.NotWordException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class WordTest {

    @Test
    public void should_check_word_conditions() {
        assertThatThrownBy(() -> new Word(" Ping pong "))
                .isInstanceOf(NotWordException.class);
    }

}