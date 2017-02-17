package com.liberty.soge.service;

import com.liberty.soge.model.HandlerDescriptor;

import java.util.List;

/**
 * @author Dmytro_Kovalskyi.
 * @since 17.02.2017.
 */
public interface AdminService {
    List<HandlerDescriptor> getHandlerDescriptors();
}
