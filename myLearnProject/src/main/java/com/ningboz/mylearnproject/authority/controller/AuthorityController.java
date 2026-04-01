package com.ningboz.mylearnproject.authority.controller;

import com.ningboz.mylearnproject.authority.annotion.AuthorityCheck;
import com.ningboz.mylearnproject.authority.entity.ProjectAuthorityEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: AuthorityController
 * @author: Znb
 * @date: 2025/10/13
 * @description:
 */
@Controller
@RequestMapping("/authority")
public class AuthorityController {

    @PostMapping("/deleteFile")
    @ResponseBody
    @AuthorityCheck(authority = ProjectAuthorityEnum.DELETE)
    public String deleteFile(HttpServletRequest request){
        return null;
    }
}
