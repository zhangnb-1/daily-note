package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian;

public abstract class ScoreChianHandle extends RespChianHandle{
    protected int score;

    public ScoreChianHandle(RespChianHandle next, int score) {
        super(next);
        this.score = score;
    }
}
