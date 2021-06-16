package com.tw.academy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CashRegisterTest {

    @Test
    void should_invoke_print_when_invoke_process() {
        //given
        SpyPrinter printer = new SpyPrinter();
        CashRegister cashRegister = new CashRegister(printer);
        Purchase purchase = new Purchase();
        //when
        cashRegister.process(purchase);
        //then
        assertTrue(printer.invoke);
    }

    @Test
    void should_print_purchase_asString_when_invoke_printer() {
        //given
        SpyPrinter printer = new SpyPrinter();
        CashRegister cashRegister = new CashRegister(printer);
        String printStr = "hello world";
        StubPurchase purchase = new StubPurchase();
        purchase.setResult(printStr);
        //when
        cashRegister.process(purchase);
        //then
        assertTrue(printer.invoke);
        assertEquals(printStr, purchase.result);

    }

    private static class SpyPrinter extends Printer {
        boolean invoke = false;

        @Override
        public void print(String content) {
            invoke = true;
        }

    }

    private static class StubPurchase extends Purchase {
        String result;

        void setResult(String result) {
            this.result = result;
        }

        @Override
        public String asString() {
            return this.result;
        }
    }
}
