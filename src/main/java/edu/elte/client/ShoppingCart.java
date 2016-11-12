package edu.elte.client;

import elte.sportStore.model.Item;
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
public class ShoppingCart extends ArrayList<Item> implements ShoppingCartOperations, Serializable {

    private static final long serialVersionUID = 1L;
    //static
    Locale spain = new Locale("ca", "ES");
    //static
    CurrencyUnit eur = Monetary.getCurrency(spain);

    @Override
    public boolean add(Item e) {
        if (contains(e)) {
            Item st = get(indexOf(e));
            st.setAmount(st.getAmount() + 1);
            remove(e);
            return super.add(st);
        } else {
            return super.add(e);
        }
    }

    @Override
    public boolean remove(Item it) {
        return super.remove(it);
    }

    @Override
    public FastMoney getTotal() {
        FastMoney subTotal;
        FastMoney total = FastMoney.of(BigDecimal.ZERO, eur);
        for (Item it : this) {
            subTotal = FastMoney.of(it.getUnityPrice(), eur).multiply(it.getAmount());
            total = total.add(subTotal);
        }
        return total;
    }

    @Override
    public int itemNumber() {
        int i=0;
        for (Item thi : this) {
            i=i+thi.getAmount();
        }
        return i;
    }

    
    
    
    public void addSampleItems() {
        add(new Item("Yoghurt", 1, Money.of(5.94, eur).getNumber()));
        add(new Item("Mango", 2, Money.of(0.75, eur).getNumber()));
        add(new Item("Pineapple", 8, Money.of(0.69, eur).getNumber()));
        add(new Item("Oranges", 6, Money.of(5.01, eur).getNumber()));
    }

//    public static void main(String[] args) {
//        ShoppingCart sh = new ShoppingCart();
//        sh.addSampleItems();
//        Item coco = new Item("Coco", 1, Money.of(5.94, eur).getNumber());
//        sh.add(coco);
//        System.out.println(sh);
//        System.out.println(sh.getTotal().getNumber());
//        sh.remove(coco);
//        System.out.println(sh);
//    }

}
