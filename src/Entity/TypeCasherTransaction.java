/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author mosta
 */
public enum TypeCasherTransaction {
    PosPay,     // pay of Pos   ==1   Debit
    PosSell,    // Sell of Pos  ==     Creditor
    PaymentReceipt,  //  ايصال دفع  Creditor
    Receipt,            //              Debit
    SalesInvoic,       // Sales  items   Debit
    PurchaseInvoice, // purchase items   Creditor
    Send_VF,                // Debit "Send"
    Receive_VF ,     //              - Creditor "Receive"
    Fees  ,        // Fees Creditor
    CasherTwoCredit,
    CasherTwoDebit,
    Solfa,
    Paydebt,    // سداد السلف   /// Creditor
    deficiency,  // عجز الجرد  // Creditor
    excess       // زيادة في الجرد// Debit
}
