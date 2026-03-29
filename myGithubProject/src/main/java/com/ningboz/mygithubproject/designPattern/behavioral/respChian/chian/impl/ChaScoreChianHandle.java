package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.impl;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.RespChianHandle;
import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.ScoreChianHandle;

public class ChaScoreChianHandle extends ScoreChianHandle {
    public ChaScoreChianHandle(RespChianHandle next, int score) {
        super(next, score);
    }

    @Override
    protected boolean canExecute() {
        return score<20 && score >=0;
    }

    @Override
    protected void execute() {
        System.out.println("极差");
    }
}
