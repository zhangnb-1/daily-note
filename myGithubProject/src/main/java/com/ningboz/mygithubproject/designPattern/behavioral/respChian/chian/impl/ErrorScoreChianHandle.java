package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.impl;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.ScoreException;
import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.RespChianHandle;
import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.ScoreChianHandle;

public class ErrorScoreChianHandle extends ScoreChianHandle {
    public ErrorScoreChianHandle(RespChianHandle next, int score) {
        super(next, score);
    }

    @Override
    protected boolean canExecute() {
        return score<0 || score >100;
    }

    @Override
    protected void execute() throws ScoreException {
        throw new ScoreException();
    }
}
