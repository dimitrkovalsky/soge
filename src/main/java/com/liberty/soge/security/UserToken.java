package com.liberty.soge.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmytro_Kovalskyi.
 * @since 13.02.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {
    private String userId;
    private String token;
}
