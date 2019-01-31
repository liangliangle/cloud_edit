package com.lianglianglee.edit.controller;

import com.liangliagnlee.common.exception.ServiceException;
import com.liangliagnlee.common.utils.EditUtils;
import com.lianglianglee.edit.config.AppConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @ClessName CoreController
 * @Desc CoreController
 * @Author liangliang
 * @Date 2018/9/17 12:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/file")
@Api(value = "文件", consumes = "application/json")
public class FileController {




  @PostMapping("")
  @ApiOperation("上传文件")
  public String upload(@RequestParam("file") MultipartFile multipartFile) {
    try {
      String fname = multipartFile.getOriginalFilename();
      String fkey = EditUtils.getFileKey(fname, AppConfig.imageFolder);
      File file = new File(AppConfig.imageFolder + fkey);
      file.createNewFile();
      try {
        FileCopyUtils.copy(multipartFile.getInputStream(), new FileOutputStream(file));
        return "/upload"+fkey;
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new ServiceException();
    }
    return null;
  }


}
