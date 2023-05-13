package Customer.CustomerPayment;

import Customer.CustomerOrder.Order;
import Customer.CustomerAuthentication.MainAuth;
public class CashPay extends Payment {
    
    private int usePhoneNum;

    public CashPay(Order order,int usePhoneNum) {
        super(order);
        this.usePhoneNum = usePhoneNum;
    }

    @Override
    public void processPayment() {
        //verify num
        int phone = this.usePhoneNum;
        if(MainAuth.checkPhone(phone)){
            this.paymentStatus = "Paid";
            this.saveOrder();
        }else{
            this.paymentStatus = "Cancelled";
        }
    }
}
