package com.rbc.pet.petstore.exceptions;

import java.util.List;

public class BaseException extends Exception {
    List<ResponseError> errors;
}
