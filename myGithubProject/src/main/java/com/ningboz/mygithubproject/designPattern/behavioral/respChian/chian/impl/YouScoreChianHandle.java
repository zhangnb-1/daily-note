package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.impl;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.RespChianHandle;
import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.ScoreChianHandle;

public class YouScoreChianHandle extends ScoreChianHandle {
    public YouScoreChianHandle(RespChianHandle next, int score) {
        super(next, score);
    }

    @Override
    protected boolean canExecute() {
        return score<=100;
    }

    @Override
    protected void execute() {
        System.out.println("优秀");
    }
}
