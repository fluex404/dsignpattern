package com.fluex404.structural;

public class ProxyExample {
    public static void main(String[] args) {
        OfficeInternetAccess access = new ProxyInternetAccess("Isa");
        access.grantInternetAccess();
    }
}

interface OfficeInternetAccess{
    void grantInternetAccess();
}
class RealInternetAccess implements OfficeInternetAccess{
    private String employeeName;

    public RealInternetAccess(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public void grantInternetAccess() {
        System.out.println("Internet Access granted for employee: "+employeeName);
    }
}
class ProxyInternetAccess implements OfficeInternetAccess{
    private String employeeName;
    private RealInternetAccess realInternetAccess;

    public ProxyInternetAccess(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public void grantInternetAccess() {
        if(getRole(employeeName) > 4){
            realInternetAccess = new RealInternetAccess(employeeName);
            realInternetAccess.grantInternetAccess();
        } else {
            System.out.println("No internet access granted. Your job leve is below 5");
        }
    }
    public int getRole(String employeeName) {
        /**
         * Check role from the database based on Name and designation
         * return job level or job designation
         */
        return 9;
    }
}

