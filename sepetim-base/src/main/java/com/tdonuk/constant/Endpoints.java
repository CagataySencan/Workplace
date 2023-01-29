package com.tdonuk.constant;

public class Endpoints {
    public static final String ROOT = App.URL + "/api/v1";

    public static final String AUTH = ROOT+"/auth";
    public static final String LOGIN = AUTH + "/login";
    public static final String REGISTER = AUTH + "/register";

    public static final String USERS = ROOT + "/users";
    public static final String ME = ROOT+"/me";

    public static final String DISCOUNTS = ROOT+"/discounts";
    public static final String DISCOUNT_HIST = DISCOUNTS+"/hist";

    public static final String VENDORS = ROOT + "/vendors";
    public static final String SUPPORTED_VENDORS = VENDORS + "/supported";
}
