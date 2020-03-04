package com.sakinramazan.controller;

import com.sakinramazan.model.Movie;
import com.sakinramazan.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {

    @Autowired
    private RedisRepository redisRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/keys")
    public @ResponseBody
    Map<Object, Object> keys() {
        return redisRepository.findAllMovies();
    }

    @RequestMapping("/values")
    public @ResponseBody
    Map<String, String> findAll() {
        Map<Object, Object> aa = redisRepository.findAllMovies();
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : aa.entrySet()) {
            String key = (String) entry.getKey();
            map.put(key, aa.get(key).toString());
        }
        return map;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> add(
            @RequestParam String key,
            @RequestParam String value) {

        Movie movie = new Movie(key, value);
        redisRepository.add(movie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam String key) {
        redisRepository.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
