package bo;

import bo.custom.impl.CustomerBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }
    public enum SetEnum{
        CUSTOMER
    }
    public SuperBO getEnum(SetEnum setEnum){
        switch (setEnum){
            case CUSTOMER:return new CustomerBOImpl();
            default:return null;

        }
    }

}
