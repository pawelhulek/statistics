package com.hulek.statistics.text.structure.impl;

import com.hulek.statistics.text.structure.exceptions.NotWordException;

/**
 * @author: Paweł Hulek
 * @created: 2017-03-28.
 */
public class Word extends SentencePart {

    public Word(String content) {
        super(content.toLowerCase().trim());
        if (this.content.contains(" "))
            throw new NotWordException();
    }


}
