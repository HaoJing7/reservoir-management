package com.back.reservoirmanagement.controller.common;

import com.back.reservoirmanagement.common.constant.MessageConstant;
import com.back.reservoirmanagement.common.result.Result;
import com.back.reservoirmanagement.common.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@Slf4j
@Api(tags = "文件上传相关接口")
public class CommonController {

    public static final String Picture_Format_PNG = "png";
    public static final String Picture_Format_JPEG = "jpeg";

   @Autowired
   private AliOssUtil aliOssUtil;

//    /**
//     * 文件上传到本地
//     * @param file
//     * @return
//     */
//    @PostMapping("/upload")
//    @ApiOperation("文件上传")z
//    public Result<String> commonUpload(MultipartFile file){
//        log.info("文件上传：{}",file);
//        try {
//            //原始文件名
//            String originalFilename = file.getOriginalFilename();
//            //截取原始文件名的后缀   dfdfdf.png
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            //构造新文件名称
//            String objectName = UUID.randomUUID().toString() + extension;
//
//            //文件的请求路径
//            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
//            return Result.success(filePath);
//        } catch (IOException e) {
//            log.error("文件上传失败：{}", e);
//        }
//        return Result.error(MessageConstant.UPLOAD_FAILED);
//    }

    /**
     * Base64图片文件上传
     * @param
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> base64Upload(@RequestBody Map<String, String> map){
        String file = map.get("file");
        // 获取文件后缀名

        // 设置文件名
        String fileName = UUID.randomUUID().toString().replace("-", "");
        // 获取文件后缀名
        int originalFilenameStartIndex = file.indexOf('/');
        int originalFilenameEndIndex = file.indexOf(';');
        String originalFilename = file.substring(originalFilenameStartIndex+1,originalFilenameEndIndex);
        if (Picture_Format_JPEG.equals(originalFilename)) {
            fileName += ".jpg";
        }else if (Picture_Format_PNG.equals(originalFilename)) {
            fileName += ".png";
        }else {
            Result.error(MessageConstant.UPLOAD_FAILED);
        }
        // 获取base64的文件
        file = file.split(",")[1];
        byte[] bytesFile = Base64Utils.decodeFromString(file);
        // 调用OSS服务上传图片
        String path = aliOssUtil.upload(bytesFile, fileName);

        return Result.success(path);
    }
}
