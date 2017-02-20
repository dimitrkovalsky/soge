package com.liberty.soge.model;

import com.liberty.soge.security.UserBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dmytro_Kovalskyi.
 * @since 15.02.2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount implements UserBase<String> {
    private String userId;
    private String externalUserId;
    private String userName;

}
