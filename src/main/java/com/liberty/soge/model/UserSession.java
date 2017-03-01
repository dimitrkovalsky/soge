package com.liberty.soge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmytro_Kovalskyi.
 * @since 20.02.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSession<T> {
    private String token;
    private T userId;
}
