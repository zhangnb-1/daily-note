package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.impl;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.RespChianHandle;
import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.ScoreChianHandle;

public class NonScoreChianHandle extends ScoreChianHandle {
    public NonScoreChianHandle(RespChianHandle next, int score) {
        super(next, score);
    }

    @Override
    protected boolean canExecute() {
        return score<60;
    }

    @Override
    protected void execute() {
        System.out.println("不及格");
    }
}
