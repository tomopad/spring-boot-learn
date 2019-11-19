package com.friends.springbootsecurity.login.controller;

import com.friends.springbootsecurity.login.model.Foo;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Controller
@RequestMapping(value = "/api/foos")
public class FooController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Foo> findAll() {
        return Lists.newArrayList(new Foo(randomAlphabetic(6)));
    }

    // API

    // read - single
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Foo findById(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return new Foo(randomAlphabetic(6));
    }



    // write - just for test
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Foo create(@RequestBody final Foo foo) {
        return foo;
    }
}