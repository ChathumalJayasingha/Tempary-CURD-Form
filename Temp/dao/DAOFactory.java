package dao;

import dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public enum SetEnum{
        CUSTOMER
    }
    public SuperDAO getEnum(SetEnum setEnum){
        switch (setEnum){
            case CUSTOMER:return new CustomerDAOImpl();
            default:return null;
        }
    }

}
