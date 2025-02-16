package com.socalMedia.socalMedia.Config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary()
    {
        Map<String,String> map=new HashMap();
        map.put("cloud_name","dm9ndtq4q");
        map.put("api_key","469597182154466");
        map.put("api_secret","KTEkeAB0oijaYqc1huOAGYrVI7o");
        map.put("secure","true");
        return new Cloudinary(map);
    }
}
