package com.engagepoint.labs.wizard.questions;

import com.engagepoint.labs.wizard.answers.DateAnswer;

/**
 * Created by igor.guzenko on 2/4/14.
 */
public class DateQuestion extends BasicQuestion implements Question<DateAnswer> {

    private DateAnswer dateAnswer;
    @Override
    public DateAnswer getAnswer() {
        return dateAnswer;
    }

    @Override
    public void setAnswer(DateAnswer answers) {
        this.dateAnswer = answers;

    }
}