package com.toedter.tutorials.webapp.lab8.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkDiscoverer;
import org.springframework.hateoas.LinkDiscoverers;
import org.springframework.hateoas.MediaTypes;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.toedter.tutorials.webapp.lab8.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class UserResourceIntegrationTest {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected List<LinkDiscoverers> links;
    protected MockMvc mvc;

    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).
                addFilter(new ShallowEtagHeaderFilter()).
                build();
    }

    @Test
    public void shouldGetUsersLink() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/api/")).
                andDo(MockMvcResultHandlers.print()).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaTypes.HAL_JSON)).
                andExpect(jsonPath("_links.users.href", notNullValue())).
                andReturn().
                getResponse();

        LinkDiscoverer discoverer = links.get(0).getLinkDiscovererFor(response.getContentType());
        Link link = discoverer.findLinkWithRel("users", response.getContentAsString());
        String href = link.getHref();
        String hrefWithoutTemplateParameters = href.substring(0, href.indexOf("{"));

        mvc.perform(get(hrefWithoutTemplateParameters)).
                andDo(MockMvcResultHandlers.print()).
                andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON)).
                andExpect(jsonPath("_embedded.users", notNullValue()));
    }
}
