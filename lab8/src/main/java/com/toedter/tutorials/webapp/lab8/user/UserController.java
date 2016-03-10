package com.toedter.tutorials.webapp.lab8.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@BasePathAwareController
@RequestMapping("/users")
public class UserController implements ResourceProcessor<RepositorySearchesResource> {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PagedResourcesAssembler<User> pagedResourcesAssembler;

    @Autowired
    private EntityLinks entityLinks;
	
	@RequestMapping("/search/findByCoolLogic")
	public ResponseEntity<PagedResources<ResourceSupport>> findByCoolLogic(@RequestParam("magicString") String magicString, 
			Pageable pageable, PersistentEntityResourceAssembler assembler) {
		Page<User> result = userRepository.findByCoolLogic(magicString, pageable);
        PagedResources<ResourceSupport> pagedResources = pagedResourcesAssembler.toResource(
                result, u -> assembler.toResource(u));
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
	}

    @Override
    public RepositorySearchesResource process(RepositorySearchesResource searchesResource) {
        if (User.class.equals(searchesResource.getDomainType())) {
            LinkBuilder lb = entityLinks.linkFor(User.class);
            searchesResource.add(new Link(lb.toString() + "/search/findByCoolLogic{?magicString}",
                    "findByCoolLogic"));
        }
        return searchesResource;
    }
}
