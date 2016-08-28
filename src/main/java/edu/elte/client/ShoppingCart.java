/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.elte.client;

import edu.elte.client.ClientRequest;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

/**
 *
 * @author Xavier
 */
public class ShoppingCart extends ArrayList<ClientRequest> implements Serializable {
    private static final long serialVersionUID = 1L;
    Locale spain = new Locale("ca", "ES");
    CurrencyUnit eur = Monetary.getCurrency(spain);

    @Override
    public boolean add(ClientRequest e) {
        if (contains(e)) {
            ClientRequest st = get(indexOf(e));
            st.setAmount(st.getAmount() + 1);
            remove(e);
            return super.add(st);
        } else {
            return super.add(e);
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof ClientRequest) {
            ClientRequest it = (ClientRequest) o;
            if (contains(it) && it.getAmount() > 1) {
                ClientRequest st = get(indexOf(o));
                st.setAmount(st.getAmount() - 1);
                return remove(o);
            }
        }
        return false;
    }

    public FastMoney getTotal() {
        FastMoney subTotal;
        FastMoney total = FastMoney.of(BigDecimal.ZERO, eur);
        for (ClientRequest it : this) {
            subTotal = FastMoney.of(it.getUnityPrice(), eur).multiply(it.getAmount());
            total = total.add(subTotal);
        }
        return total;
    }

    public void addSampleItems() {
        add(new ClientRequest("Yoghurt", 1, Money.of(5.94, eur).getNumber()));
        add(new ClientRequest("Mango", 2, Money.of(0.75, eur).getNumber()));
        add(new ClientRequest("Pineapple", 8, Money.of(0.69, eur).getNumber()));
        add(new ClientRequest("Oranges", 6, Money.of(5.01, eur).getNumber()));
    }

//    public static void main(String[] args) {
//
//        ShoppingCart sh = new ShoppingCart();
//        sh.addSampleItems();
////        System.out.println(sh);        
//        System.out.println(sh.getTotal().getNumber());
//    }
}
