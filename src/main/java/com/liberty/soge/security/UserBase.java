package com.liberty.soge.security;

/**
 * @author Dmytro_Kovalskyi.
 * @since 15.02.2017.
 */
public interface UserBase<T> {
    T getUserId();

    String getUserName();
}
