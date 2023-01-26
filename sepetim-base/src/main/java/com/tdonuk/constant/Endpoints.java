package com.tdonuk.constant;

public class Endpoints {
    public static final String ROOT = App.URL;
    public static final String VERSION = "v1";
    public static final String API = "api";

    public static final String AUTH = "auth";
    public static final String AUTH_PATH = String.join("/", ROOT, VERSION, API, AUTH);
    public static final String LOGIN = AUTH_PATH +"/login";
    public static final String REGISTER = AUTH_PATH+"/register";
    public static final String ME = AUTH_PATH+"/me";

    public static final String DISCOUNTS = "discounts";
    public static final String DISCOUNTS_PATH = String.join("/", ROOT, VERSION, API, DISCOUNTS);
    public static final String DISCOUNT_HIST = DISCOUNTS_PATH+"/hist";
}
