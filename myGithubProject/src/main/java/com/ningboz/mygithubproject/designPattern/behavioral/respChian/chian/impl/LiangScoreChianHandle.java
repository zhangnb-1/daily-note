package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.impl;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.RespChianHandle;
import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.ScoreChianHandle;

public class LiangScoreChianHandle extends ScoreChianHandle {
    public LiangScoreChianHandle(RespChianHandle next, int score) {
        super(next, score);
    }

    @Override
    protected boolean canExecute() {
        return score<80;
    }

    @Override
    protected void execute() {
        System.out.println("良好");
    }
}
