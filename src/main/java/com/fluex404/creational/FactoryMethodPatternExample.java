package com.fluex404.creational;

/**
 - When a class does't know what sub-classes will be required to create
 - When a class wants that its sub-classes specify the objects to be created
 - When the parent classes choose the creation of objects to its sub-clases
 */


public class FactoryMethodPatternExample {

    public static void main(String... args) {
        Plan comercial = GetPlanFactory.getPlan(GetPlanFactory.PlanType.COMERCIALPLAN);
        Plan domestic = GetPlanFactory.getPlan(GetPlanFactory.PlanType.DOMESTICPLAN);
        Plan institutional = GetPlanFactory.getPlan(GetPlanFactory.PlanType.INSTITUTIONALPLAN);

        comercial.getRate();
        domestic.getRate();
        institutional.getRate();

        //
        comercial.calculateBill(2);
        domestic.calculateBill(2);
        institutional.calculateBill(2);
    }

}

abstract class Plan{
    protected Double rate;
    abstract void getRate();
    public void calculateBill(int units) {
        System.out.println(units * rate);
    }
}
class DomesticPlan extends Plan{

    void getRate() {
        super.rate = 3.50;
    }
}
class CommercialPlan extends Plan{

    void getRate() {
        rate = 7.50;
    }
}
class InstitutionalPlan extends Plan{

    void getRate() {
        rate = 5.50;
    }
}
// Create a GetPlanFactory to generate object of concreate class base on give information

class GetPlanFactory{
    // use getPlan method to getObject of type Plan
    enum PlanType{
        DOMESTICPLAN,
        COMERCIALPLAN,
        INSTITUTIONALPLAN
    }

    public static Plan getPlan(PlanType planType) {
        if(planType == null) {
            return null;
        }

        switch(planType){
            case DOMESTICPLAN:
                return new DomesticPlan();
            case COMERCIALPLAN:
                return new CommercialPlan();
            case INSTITUTIONALPLAN:
                return new InstitutionalPlan();
        }

        return null;
    }
}