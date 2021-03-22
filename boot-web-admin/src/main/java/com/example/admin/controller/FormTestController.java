package com.example.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class FormTestController {

    @Value(value = "${uploadPath}")
    String uploadPath;

    @GetMapping("/form_layouts.html")
    public String formLayouts(){
        return "form/form_layouts";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username")String username,
                         @RequestPart("headImg") MultipartFile headImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
//        log.info("上传的信息：email="+email+",username="+username+",headImg="+headImg.getSize()+",photos="+photos.length);
        log.info("上传的信息：email={},username={},headImg={},photos={}",email,username,headImg.getSize(),photos.length);
        String originalFilename = headImg.getOriginalFilename();

        if(!headImg.isEmpty()){
//            headImg.transferTo(new File("D:\\Work\\cache\\"+originalFilename));
            headImg.transferTo(new File(uploadPath+originalFilename));
        }
        if(photos.length>0){
            for (MultipartFile photo : photos) {
                String originalPhotoname = photo.getOriginalFilename();
                    if(!photo.isEmpty()){
                    photo.transferTo(new File("D:\\Work\\cache\\"+originalPhotoname));
                }
            }
        }

        return "main";
    }

}
