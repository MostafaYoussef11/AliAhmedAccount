/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Utilities.ConnectDB;
import Utilities.invoice;

/**
 *
 * @author mosta
 */
public class purchaseInvoice extends invoice{
    
    public purchaseInvoice() {
        
        super("purchaseInvoice");
    }

    @Override
    public String getLastIdItemsOnInvoice(String id_Invoic) {
        return ConnectDB.LastId("itemsonpurchaseinvoice WHERE id_purchaseInvoic="+id_Invoic, "id");
    }
    
    
    
}
