package com.api.controllers;

import com.api.domain.UserDomain;
import com.api.dtos.UserJson;
import com.api.exceptions.ApiException;
import com.api.persistence.DaoService;
import com.api.utils.JsonFormatter;
import com.api.utils.JsonResponseFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;

public class UserController {

    public static String getUser(Request req, Response res) throws ApiException {
        try{
            Integer userId = Integer.valueOf(req.params("user_id"));
            validateUserId(userId);
            UserDomain userDomain = DaoService.INSTANCE.getUser(userId);
            if (userDomain == null)
                throw new ApiException(String.format("The UserId (%d) was not found on the DB.", userId), HttpServletResponse.SC_NOT_FOUND);

            UserJson userJson = UserJson.createFrom(userDomain);

            return JsonResponseFactory.createJsonResponse(res, HttpServletResponse.SC_OK,userJson);
        } catch (NumberFormatException e) {
            throw new ApiException(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static Object createUser(Request req, Response res) throws ApiException {
      try {
            UserJson userJson = JsonFormatter.parse(req.body(), UserJson.class);
            validateUserId(userJson.getUserId());
            validateDuplicateRecord(userJson.getUserId());
            UserDomain userDomain = UserDomain.createFrom(userJson);
            DaoService.INSTANCE.merge(userDomain);

            return JsonResponseFactory.createSuccessResponse(res, userDomain);

        } catch (JsonProcessingException e) {
          throw new ApiException(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static Object updateUser(Request req, Response res) throws ApiException {
        try {

            UserJson userJson = JsonFormatter.parse(req.body(), UserJson.class);
            validateUserId(userJson.getUserId());
            UserDomain userDomain = UserDomain.createFrom(userJson);
            DaoService.INSTANCE.merge(userDomain);

            return JsonResponseFactory.createSuccessResponse(res,userDomain);
            
        } catch (JsonProcessingException e) {
            throw new ApiException(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public static Object deleteUserById(Request req, Response res) throws ApiException {
        try{
            Integer userId = Integer.valueOf(req.params("user_id"));

            if (req.headers("X-Admin") == null || !req.headers("X-Admin").equals("true")) {
                throw new ApiException("Only admins can delete Users.", HttpServletResponse.SC_FORBIDDEN);
            }

            validateUserId(userId);
            getUserById(userId);
            DaoService.INSTANCE.deleteUser(userId);

            return JsonResponseFactory.createJsonResponse(res,HttpServletResponse.SC_OK,String.format("The CarId (%d) was removed from the DB.", userId));

        } catch (NumberFormatException e) {
            throw new ApiException(e.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
        }
    }



    public static UserDomain getUserById(Integer userId) throws ApiException {
        UserDomain userDomain = DaoService.INSTANCE.getUser(userId);
        if (userDomain == null)
            throw new ApiException(String.format("The userId (%d) was not found on the DB.", userId), HttpServletResponse.SC_NOT_FOUND);
        return userDomain;
    }

    private static void validateUserId(Integer userId) throws ApiException {
        if (userId == null || userId < 0)
            throw new ApiException(String.format("The UserId (%d) is invalid.", userId), HttpServletResponse.SC_BAD_REQUEST);
    }

    private static void validateDuplicateRecord(Integer userId) throws ApiException {
        UserDomain userDomain = DaoService.INSTANCE.getUser(userId);
        if (userDomain != null)
            throw new ApiException(String.format("The UserID (%d) already exists.", userId), HttpServletResponse.SC_CONFLICT);
    }

}