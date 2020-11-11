package com.thoughtworks.capability.gtb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.vo.EventType;
import com.thoughtworks.capability.gtb.vo.EventVo;
import com.thoughtworks.capability.gtb.vo.UserVo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EventController {

  private final ObjectMapper objectMapper;
  private Map<String, EventVo> eventMapper;

  @Autowired
  public EventController(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.eventMapper = new HashMap<>();
  }

  @GetMapping("/events/{id}")
  public EventVo getEventById(@PathVariable("id") String id) { return eventMapper.get(id);}

  @PostMapping("/events")
  public void createEvent(@RequestBody EventVo event) throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(event);
    this.eventMapper.put(event.getId(), event);
    log.info("create event: {}", json);
  }
}
