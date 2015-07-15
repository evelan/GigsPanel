package it.coderunner.gigs.webapp.controller;

import it.coderunner.gigs.repository.artists.Artists;
import it.coderunner.gigs.repository.comments.Comments;
import it.coderunner.gigs.repository.gigs.Gigs;
import it.coderunner.gigs.repository.gigs.IGigRepository;
import it.coderunner.gigs.repository.spots.Spots;
import it.coderunner.gigs.repository.tags.Tags;
import it.coderunner.gigs.repository.users.Users;
import it.coderunner.gigs.service.artists.IArtistService;
import it.coderunner.gigs.service.comments.ICommentService;
import it.coderunner.gigs.service.gigs.IGigService;
import it.coderunner.gigs.service.spots.ISpotService;
import it.coderunner.gigs.service.tags.ITagService;
import it.coderunner.gigs.service.users.IUserService;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Log4j
@RequestMapping(value = { "/user" })
public class HomeController extends LoggedUserController{
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISpotService spotService;
	
	@Autowired
	private IArtistService artistService;
	
	@Autowired
	private IGigService gigService;
	
	@Autowired
	private ITagService tagService;
	
	@Autowired
	private ICommentService commentService;
	
	@ModelAttribute("userCounter")
	public long userCounter() {
		return userService.count(Users.findAll());
	}
	
	@ModelAttribute("spotCounter")
	public long spotCounter(){
		return spotService.count(Spots.findAll());
	}
	
	@ModelAttribute("artistCounter")
	public long artistCounter(){
		return artistService.count(Artists.findAll());
	}
	
	@ModelAttribute("gigCounter")
	public long gigCounter(){
		return gigService.count(Gigs.findAll());
	}
	
	@ModelAttribute("tagCounter")
	public long tagCounter(){
		return tagService.count(Tags.findAll());
	}
	
	@ModelAttribute("commentCounter")
	public long commentCounter(){
		return commentService.count(Comments.findAll());
	}

	@RequestMapping(value={"", "/", "index", "/index/"}, method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		log.info("Żyję!!!");
		return "index";
	}

}
