package com.cmw.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: cmw
 * @date: 2021/11/12
 */
@Data
public class Loan {

    private BigDecimal amt;


    public static void main(String[] args) {
        List<Loan> loans = new ArrayList<>();

        for (int i= 10;i<13;i++){
            Loan loan = new Loan();
            loan.setAmt(new BigDecimal(String.valueOf(i)));
            loans.add(loan);
        }

        BigDecimal reduce = loans.stream().map(Loan::getAmt).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce);

    }

    public class MyThread implements Runnable{

        @Override
        public void run() {

        }
    }

}
