package com.example.java_core.p05_exception;

public class ApiExceptionTests {

    public static void apiExceptionHandler() {
        throw new ApiException(ResultCode.UNAUTHORIZED);
    }

    public void callUber(int day) throws ApiException {
        if (day % 2 == 0) {
            System.out.println("successfully order a Uber!");
        } else {
            throw new ApiException(ResultCode.FAILED);
        }
    }

    public static void main(String[] args) {

        try {
            System.out.println("--- call exceptionHandler() ---");
            apiExceptionHandler();
        } catch (ApiException e) {
            System.out.println(e);
            System.out.println(e.getCause()); // null
            System.out.println(e.getErrorCode()); // UNAUTHORIZED
            System.out.println(e.getMessage()); // Not logged in or token has expired!
        }

        ApiExceptionTests apiExceptionTests = new ApiExceptionTests();
        try {
            System.out.println("\n--- call Uber with 2---");
            apiExceptionTests.callUber(2);
            System.out.println("\n--- call Uber with 1---");
            apiExceptionTests.callUber(1);
        } catch(ApiException e) {
            System.out.println(e);
            System.out.println("failed to call Uber because it is too busy!");

            System.out.println(e.getErrorCode() + " --> " + e.getMessage());
        }
    }
}
