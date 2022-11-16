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
    Solfa,  // when i need money
    Ohda,   // when i get anyone money
    FilterOhda, // when filter money ohda
    Paydebt,    // سداد السلف   /// Creditor
    deficiency,  // عجز الجرد  // Creditor
    excess,       // زيادة في الجرد// Debit
    accountExport , // Creditor  Export To Account
    expensGold ,     // Creditor Expens of Gold
    importsGold ,     // Debit import mony of Sell Gold
    assetsGold,
    getLoans,  // Debit imoprt Money of Loans
    paymentLoans , // Creditor Loans
    debitToBank,  // Creditor ايداع في البنك و خصم من الخزنة
    creditFromBank // Debit سحب من البنك و ايداع في الخزنة
}
