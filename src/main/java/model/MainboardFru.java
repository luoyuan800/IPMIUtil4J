/*
 * MainboardFru.java
 * Date: 7/14/2015
 * Time: 3:52 PM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package model;

public class MainboardFru extends Fru {
    private String chassisType, chassisPartNumber, chassisSerialNum, chassisOEMField, boardMfgDateTime,
            boardManufacturer, boardProductName, boardSerialNumber, boardPartNumber, boardFRUFileID,
            boardOEMField, productOEMField, systemGUID, biosVersion;

    public MainboardFru(String name) {
        super(name);
    }

    public String getChassisType() {
        return chassisType;
    }

    public void setChassisType(String chassisType) {
        this.chassisType = chassisType;
    }

    public String getChassisPartNumber() {
        return chassisPartNumber;
    }

    public void setChassisPartNumber(String chassisPartNumber) {
        this.chassisPartNumber = chassisPartNumber;
    }

    public String getChassisSerialNum() {
        return chassisSerialNum;
    }

    public void setChassisSerialNum(String chassisSerialNum) {
        this.chassisSerialNum = chassisSerialNum;
    }

    public String getChassisOEMField() {
        return chassisOEMField;
    }

    public void setChassisOEMField(String chassisOEMField) {
        this.chassisOEMField = chassisOEMField;
    }

    public String getBoardMfgDateTime() {
        return boardMfgDateTime;
    }

    public void setBoardMfgDateTime(String boardMfgDateTime) {
        this.boardMfgDateTime = boardMfgDateTime;
    }

    public String getBoardFRUFileID() {
        return boardFRUFileID;
    }

    public void setBoardFRUFileID(String boardFRUFileID) {
        this.boardFRUFileID = boardFRUFileID;
    }

    public String getBoardOEMField() {
        return boardOEMField;
    }

    public void setBoardOEMField(String boardOEMField) {
        this.boardOEMField = boardOEMField;
    }

    public String getProductOEMField() {
        return productOEMField;
    }

    public void setProductOEMField(String productOEMField) {
        this.productOEMField = productOEMField;
    }

    public String getSystemGUID() {
        return systemGUID;
    }

    public void setSystemGUID(String systemGUID) {
        this.systemGUID = systemGUID;
    }

    public String getBiosVersion() {
        return biosVersion;
    }

    public void setBiosVersion(String biosVersion) {
        this.biosVersion = biosVersion;
    }

    public String getBoardManufacturer() {
        return boardManufacturer;
    }

    public void setBoardManufacturer(String boardManufacturer) {
        this.boardManufacturer = boardManufacturer;
    }

    public String getBoardProductName() {
        return boardProductName;
    }

    public void setBoardProductName(String boardProductName) {
        this.boardProductName = boardProductName;
    }

    public String getBoardSerialNumber() {
        return boardSerialNumber;
    }

    public void setBoardSerialNumber(String boardSerialNumber) {
        this.boardSerialNumber = boardSerialNumber;
    }

    public String getBoardPartNumber() {
        return boardPartNumber;
    }

    public void setBoardPartNumber(String boardPartNumber) {
        this.boardPartNumber = boardPartNumber;
    }
}
