/*
 * Frue.java
 * Date: 7/14/2015
 * Time: 3:38 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package model;

public abstract class Fru {
    private String productManufacturer;
    private String productName;
    private String productPartNumber;
    private String productVersion;
    private String productSerialNum;
    private String productAssetTag;
    private String productFRUFileID;
    private int fruSize;
    private String name;

    public Fru(String name){
        this.name = name;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPartNumber() {
        return productPartNumber;
    }

    public void setProductPartNumber(String productPartNumber) {
        this.productPartNumber = productPartNumber;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getProductSerialNum() {
        return productSerialNum;
    }

    public void setProductSerialNum(String productSerialNum) {
        this.productSerialNum = productSerialNum;
    }

    public String getProductAssetTag() {
        return productAssetTag;
    }

    public void setProductAssetTag(String productAssetTag) {
        this.productAssetTag = productAssetTag;
    }

    public String getProductFRUFileID() {
        return productFRUFileID;
    }

    public void setProductFRUFileID(String productFRUFileID) {
        this.productFRUFileID = productFRUFileID;
    }

    public int getFruSize() {
        return fruSize;
    }

    public void setFruSize(int fruSize) {
        this.fruSize = fruSize;
    }

    public String getName() {
        return name;
    }
}
