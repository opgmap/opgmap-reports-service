package ru.opgmap.reports.rest;

public final class ApiPath {

    public static final String REPORTS = "/api/v1/reports";
    public static final String ROOT_PATH = "/";
    public static final String REPORT_ID = "/{reportId}";
    public static final String USER_ID = "/{userId}";

    private static final String STATUS = "/status";
    private static final String USER = "/user";
    private static final String RESPONSE = "/response";
    private static final String HISTORY = "/history";


    public static final String REPORT_ID_STATUS_PATH = REPORT_ID + STATUS;
    public static final String USER_ID_PATH = USER + USER_ID;
    public static final String RESPONSE_TO_REPORT_PATH = RESPONSE + REPORT_ID;
    public static final String REPORT_ID_HISTORY_PATH = REPORT_ID + HISTORY;
}
