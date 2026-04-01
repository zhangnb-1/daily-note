package com.ningboz.mylearnproject.authority.entity;

/**
 * @ClassName: ProjectAuthority
 * @author: Znb
 * @date: 2025/10/13
 * @description:
 */
public enum ProjectAuthorityEnum {
    UPFILE("上传文件",1),
    DOWNFILE("下载文件",2),
    UPDATE("修改",3),
    DELETE("删除",4),

    ;
//    private String authorityId;
    private String authorityName;
    private Integer index;

    ProjectAuthorityEnum( String authorityName, Integer index) {
//        this.authorityId = authorityId;
        this.authorityName = authorityName;
        this.index = index;
    }
}
