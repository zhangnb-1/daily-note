package com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.ScoreException;

public abstract class RespChianHandle {
    private RespChianHandle next;

    public RespChianHandle(RespChianHandle next) {
        this.next = next;
    }

    public void handle() throws ScoreException {
        if(!canExecute()) next.canExecute();
        execute();
    }

    protected abstract boolean canExecute();
    protected abstract void execute() throws ScoreException;
}
