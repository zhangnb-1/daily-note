package com.ningboz.mygithubproject.designPattern.structer.bridge;

public class CommonService {
    private Dimension1Service dimension1Service;
    private Dimension2Service dimension2Service;
    private Dimension3Service dimension3Service;

    public CommonService(Dimension1Service dimension1Service, Dimension2Service dimension2Service, Dimension3Service dimension3Service) {
        this.dimension1Service = dimension1Service;
        this.dimension2Service = dimension2Service;
        this.dimension3Service = dimension3Service;
    }

    public void doSomething(){
        dimension1Service.dimensionMethod();
        dimension2Service.dimensionMethod();
        dimension3Service.dimensionMethod();
    }
}
