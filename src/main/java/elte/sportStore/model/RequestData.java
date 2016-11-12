package elte.sportStore.model;

import com.querydsl.core.annotations.QueryEmbedded;
import com.querydsl.core.annotations.QueryEntity;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Xavier
 */
@QueryEntity
//@QueryEmbeddable
//@QuerySupertype
public class RequestData {
        static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        private String id;
        private String subOrd;
        private String name;
        private String address;
        private String phone;
        private String country;
        private String prio;
        private Date expDelivery;
        private String comments;
        private String deliverAddress;
        private Date date;
        private Integer budget;
        private int itemsNumber;
        @QueryEmbedded
        private String category;
        private Integer itemNumber;
        private Object items;

    public RequestData() {
    }

    public RequestData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getSubOrd() {
        return subOrd;
    }

    public void setSubOrd(String subOrd) {
        this.subOrd = subOrd;
    }

    public Date getExpDelivery() {
        return expDelivery;
    }

    public void setExpDelivery(Date expDelivery) {
        this.expDelivery = expDelivery;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   public int getItemsNumber() {
        return itemsNumber;
    }

    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }
    public RequestData(String id) {
        this.id = id;
    }
    
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RequestData{" + "id=" + id + ", address=" + address + ", country=" + country + ", prio=" + prio + ", expDelivery=" + dateFormat.format(expDelivery) + ", category=" + category + ", itemNumber=" + itemNumber + ", items=" + items + '}';
    }
}
        