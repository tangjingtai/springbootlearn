package com.jt.springbootlearn.bean.concert;

public aspect CriticAspect {
    public CriticAspect() {
    }

//    pointcut performance():execution(* perform(..));
//
//    after() returning():performance(){
//        System.out.println(criticismEngine.getCriticism());
//    }

    private CriticismEngine criticismEngine;

    public CriticismEngine getCriticismEngine() {
        return criticismEngine;
    }

    public void setCriticismEngine(CriticismEngine criticismEngine) {
        this.criticismEngine = criticismEngine;
    }
}
