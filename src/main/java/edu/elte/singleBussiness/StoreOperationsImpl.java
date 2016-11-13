package edu.elte.singleBussiness;

import elte.sportStore.model.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import org.javamoney.moneta.Money;
import org.springframework.data.mongodb.core.MongoOperations;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

public class StoreOperationsImpl implements StoreOperations {

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(StoreOperationsImpl.class.getName());
    ;
    

    private static final String DBNAME = "tescodb";
    private MongoOperations mongoOps;

    public StoreOperationsImpl() {
        try {
            mongoOps = MongoConnection.getLocalInstance(DBNAME);
        } catch (Exception e) {
        }
    }

    @Override
    public void checkout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStockOne(ItemStore item) {
        Query q = query(where("name").is(item.getName()));
        mongoOps.updateFirst(q, update("amount", item.getAmount() + 1), ItemStore.class);

    }

    @Override
    public void reduceOne(ItemStore item) {
        if (item.getAmount() > 0) {
            Query q = query(where("name").is(item.getName()));
            mongoOps.updateFirst(q, update("amount", item.getAmount() - 1), ItemStore.class);
        }
        System.out.println("Can not reduce one item");
    }

    @Override
    public void addStock(ItemStore item, int num) {
        Query q = query(where("name").is(item.getName()));
        mongoOps.updateFirst(q, update("amount", item.getAmount() + num), ItemStore.class);
    }

    @Override
    public void reduce(ItemStore item, int num) {
        //TODO Handle with <0 elements
        if (item.getAmount() >= num) {
            Query q = query(where("name").is(item.getName()));
            mongoOps.updateFirst(q, update("amount", item.getAmount() - num), ItemStore.class);
        }
        System.out.println("Can not reduce the requested items");
    }

    @Override
    public void removeItem(ItemStore it) {
        mongoOps.remove(it);
    }

    @Override
    public void insertSampleItems(List<ItemStore> listItem) {
        listItem.stream().forEach(i -> mongoOps.insert(i));
    }

    @Override
    public void removeAllListItems() {
        mongoOps.dropCollection(ItemStore.class);
    }

    @Override
    public ItemStore findItem(String name) {
        Query q = query(where("name").is(name));
        return mongoOps.findOne(q, ItemStore.class);
    }

    @Override
    public void createSampleItems() {
        Locale spain = new Locale("ca", "ES");
        CurrencyUnit eur = Monetary.getCurrency(spain);

        List<ItemStore> listItem = new ArrayList<ItemStore>() {
            {
                add(new ItemStore("backpack", 1, Money.of(9, eur).getNumber()));
                add(new ItemStore("tent", 2, Money.of(8, eur).getNumber()));
                add(new ItemStore("soccershoes", 8, Money.of(0.69, eur).getNumber()));
                add(new ItemStore("soccerball", 6, Money.of(5.01, eur).getNumber()));
                add(new ItemStore("basketballshoes", 1, Money.of(5.02, eur).getNumber()));
                add(new ItemStore("highhills", 1, Money.of(5.03, eur).getNumber()));
                add(new ItemStore("raquet", 4, Money.of(5.04, eur).getNumber()));
                add(new ItemStore("socks", 10, Money.of(5.05, eur).getNumber()));
                add(new ItemStore("shinguards", 5, Money.of(5.06, eur).getNumber()));
                add(new ItemStore("archeryequipment", 7, Money.of(5.07, eur).getNumber()));
                add(new ItemStore("balls", 7, Money.of(5.08, eur).getNumber()));
                add(new ItemStore("bicycles", 7, Money.of(5.09, eur).getNumber()));
                add(new ItemStore("blood/urinetestapparatus", 9, Money.of(5.10, eur).getNumber()));
                add(new ItemStore("bobsleds", 8, Money.of(5.11, eur).getNumber()));
                add(new ItemStore("boomerangs", 8, Money.of(5.12, eur).getNumber()));
                add(new ItemStore("braces", 7, Money.of(5.13, eur).getNumber()));
                add(new ItemStore("calibratingdevices", 1, Money.of(5.14, eur).getNumber()));
                add(new ItemStore("canoes", 7, Money.of(5.15, eur).getNumber()));
                add(new ItemStore("climbingequipment", 10, Money.of(5.16, eur).getNumber()));
                add(new ItemStore("clubs", 9, Money.of(5.17, eur).getNumber()));
                add(new ItemStore("compasses", 10, Money.of(5.18, eur).getNumber()));
                add(new ItemStore("curlingbrooms", 4, Money.of(5.19, eur).getNumber()));
                add(new ItemStore("discuses", 6, Money.of(5.20, eur).getNumber()));
                add(new ItemStore("downhillskis", 3, Money.of(5.21, eur).getNumber()));
                add(new ItemStore("epees", 1, Money.of(5.22, eur).getNumber()));
                add(new ItemStore("ergometers", 4, Money.of(5.23, eur).getNumber()));
                add(new ItemStore("exercisebenches", 4, Money.of(5.24, eur).getNumber()));
                add(new ItemStore("eyewear", 6, Money.of(5.25, eur).getNumber()));
                add(new ItemStore("fieldhockeysticks", 6, Money.of(4.00, eur).getNumber()));
                add(new ItemStore("fieldhockeyballs", 3, Money.of(5.26, eur).getNumber()));
                add(new ItemStore("gloves", 20, Money.of(5.27, eur).getNumber()));
                add(new ItemStore("goalsposts", 2, Money.of(5.28, eur).getNumber()));
                add(new ItemStore("goals", 1, Money.of(9, eur).getNumber()));
                add(new ItemStore("grips", 2, Money.of(8, eur).getNumber()));
                add(new ItemStore("gymnasticsequipment", 8, Money.of(0.69, eur).getNumber()));
                add(new ItemStore("handballs", 6, Money.of(5.01, eur).getNumber()));
                add(new ItemStore("hang-gliders", 1, Money.of(5.02, eur).getNumber()));
                add(new ItemStore("headgear", 1, Money.of(5.03, eur).getNumber()));
                add(new ItemStore("helmets", 4, Money.of(5.04, eur).getNumber()));
                add(new ItemStore("hockeypucks", 10, Money.of(5.05, eur).getNumber()));
                add(new ItemStore("hockeysticks", 5, Money.of(5.06, eur).getNumber()));
                add(new ItemStore("horsesfordressage", 7, Money.of(5.07, eur).getNumber()));
                add(new ItemStore("hurdles", 7, Money.of(5.08, eur).getNumber()));
                add(new ItemStore("iceskates", 7, Money.of(5.09, eur).getNumber()));
                add(new ItemStore("javelins", 9, Money.of(5.10, eur).getNumber()));
                add(new ItemStore("kayaks", 8, Money.of(5.11, eur).getNumber()));
                add(new ItemStore("luges", 8, Money.of(5.12, eur).getNumber()));
                add(new ItemStore("mallets", 7, Money.of(5.13, eur).getNumber()));
                add(new ItemStore("markers", 1, Money.of(5.14, eur).getNumber()));
                add(new ItemStore("mats", 7, Money.of(5.15, eur).getNumber()));
                add(new ItemStore("nets", 10, Money.of(5.16, eur).getNumber()));
                add(new ItemStore("Nordicskis", 9, Money.of(5.17, eur).getNumber()));
                add(new ItemStore("oars", 10, Money.of(5.18, eur).getNumber()));
                add(new ItemStore("padding", 4, Money.of(5.19, eur).getNumber()));
                add(new ItemStore("paddles", 6, Money.of(5.20, eur).getNumber()));
                add(new ItemStore("poles(vaulting)", 3, Money.of(5.21, eur).getNumber()));
                add(new ItemStore("protectiveclothing", 1, Money.of(5.22, eur).getNumber()));
                add(new ItemStore("protectivegear", 4, Money.of(5.23, eur).getNumber()));
                add(new ItemStore("pucks", 4, Money.of(5.24, eur).getNumber()));
                add(new ItemStore("racehorses", 6, Money.of(5.25, eur).getNumber()));
                add(new ItemStore("raceskis", 6, Money.of(4.00, eur).getNumber()));
                add(new ItemStore("racingshells", 3, Money.of(5.26, eur).getNumber()));
                add(new ItemStore("racquetballs", 20, Money.of(5.27, eur).getNumber()));
                add(new ItemStore("racquets", 2, Money.of(5.28, eur).getNumber()));
                add(new ItemStore("ridingequipment", 1, Money.of(9, eur).getNumber()));
                add(new ItemStore("rowboats", 2, Money.of(8, eur).getNumber()));
                add(new ItemStore("rifles", 8, Money.of(0.69, eur).getNumber()));
                add(new ItemStore("runningshoes", 6, Money.of(5.01, eur).getNumber()));
                add(new ItemStore("sailboats", 1, Money.of(5.02, eur).getNumber()));
                add(new ItemStore("sails", 1, Money.of(5.03, eur).getNumber()));
                add(new ItemStore("scoreboard", 4, Money.of(5.04, eur).getNumber()));
                add(new ItemStore("scoringequipment", 10, Money.of(5.05, eur).getNumber()));
                add(new ItemStore("sculls", 5, Money.of(5.06, eur).getNumber()));
                add(new ItemStore("shoes", 7, Money.of(5.07, eur).getNumber()));
                add(new ItemStore("shot(forshotput)", 7, Money.of(5.08, eur).getNumber()));
                add(new ItemStore("showhorses", 7, Money.of(5.09, eur).getNumber()));
                add(new ItemStore("skis(forjumping)", 9, Money.of(5.10, eur).getNumber()));
                add(new ItemStore("sleddogs", 8, Money.of(5.11, eur).getNumber()));
                add(new ItemStore("sleds", 8, Money.of(5.12, eur).getNumber()));
                add(new ItemStore("sportarms", 7, Money.of(5.13, eur).getNumber()));
                add(new ItemStore("squashballs", 1, Money.of(5.14, eur).getNumber()));
                add(new ItemStore("stopwatches", 7, Money.of(5.15, eur).getNumber()));
                add(new ItemStore("strength&conditioningequipment", 10, Money.of(5.16, eur).getNumber()));
                add(new ItemStore("sulkies", 9, Money.of(5.17, eur).getNumber()));
                add(new ItemStore("surfboards", 10, Money.of(5.18, eur).getNumber()));
                add(new ItemStore("supports/braces", 4, Money.of(5.19, eur).getNumber()));
                add(new ItemStore("tapemeasures", 6, Money.of(5.20, eur).getNumber()));
                add(new ItemStore("testingdevices", 3, Money.of(5.21, eur).getNumber()));
                add(new ItemStore("timingequipment", 1, Money.of(5.22, eur).getNumber()));
                add(new ItemStore("toolsforrepairs&adjustments", 4, Money.of(5.23, eur).getNumber()));
                add(new ItemStore("uniforms", 4, Money.of(5.24, eur).getNumber()));
                add(new ItemStore("watercraft", 6, Money.of(5.25, eur).getNumber()));
                add(new ItemStore("weightliftingequipment", 6, Money.of(4.00, eur).getNumber()));
                add(new ItemStore("wheelchairsforcompetition", 3, Money.of(5.26, eur).getNumber()));
                add(new ItemStore("windsurfers", 20, Money.of(5.27, eur).getNumber()));
                add(new ItemStore("wrestlingmats&tatamis", 2, Money.of(5.28, eur).getNumber()));
            }
        };

        LOG.debug("Inserting elements on list: ");
        insertSampleItems(listItem);
    }

    @Override
    public List<ItemStore> findAllItems() {
        return mongoOps.findAll(ItemStore.class);
    }

    @Override
    public Item checkAvailableItem(Item cliReq) {
        ItemStore it = findItem(cliReq.getName());
        //TODO add bussines method to limit the quantity offered
//         Optional.of(it).get().getAmount();
        int offAmount;
        Item reqIt = new Item();
        if (it != null) {
            if (cliReq.getAmount() <= it.getAmount()) {
                offAmount = cliReq.getAmount();
            } else {
                offAmount = it.getAmount();
            }
            reqIt.setAmount(offAmount);
            reqIt.setName(it.getName());
        }
        return reqIt;

    }

    @Override
    public List<Item> checkAvailableItems(List<Item> cliReq) {
        List<Item> result = cliReq.stream()
                .map(it -> checkAvailableItem(it)).filter(it1 -> it1.getName() != null)
                .collect(Collectors.toList());
        return result;
    }

}