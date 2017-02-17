package com.liberty.soge.rest;

import com.liberty.soge.model.HandlerDescriptor;
import com.liberty.soge.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dmytro_Kovalskyi.
 * @since 17.02.2017.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(path = "/handlers", method = RequestMethod.GET)
    public List<HandlerDescriptor> getHandlers() {
        return adminService.getHandlerDescriptors();
    }
}
