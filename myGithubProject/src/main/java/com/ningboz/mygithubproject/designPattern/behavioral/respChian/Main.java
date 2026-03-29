package com.ningboz.mygithubproject.designPattern.behavioral.respChian;

import com.ningboz.mygithubproject.designPattern.behavioral.respChian.chian.impl.YouScoreChianHandle;

public class Main {
    public static void main(String[] args) throws ScoreException {
        originMethod(111);
    }

    private static void originMethod(int score) throws ScoreException {
        if(score<20 && score >=0){
            System.out.println("极差");
        }else if(score<60){
            System.out.println("不及格");
        } else if (score<80) {
            System.out.println("良好");
        } else if (score <= 100) {
            System.out.println("优秀");
        } else{
            throw new ScoreException();
        }
    }

    private static void chianMethod(){
    }
}
