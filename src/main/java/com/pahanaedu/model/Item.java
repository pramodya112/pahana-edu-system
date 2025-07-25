package com.pahanaedu.model;

public class Item {
    private String itemId;
    private String itemName;
    private double unitPrice;

    public Item(String itemId, String itemName, double unitPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public String getItemDetails() {
        return "Item ID: " + itemId + ", Name: " + itemName + ", Unit Price: " + unitPrice;
    }
}