package com.project.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.scripting.ScriptCompilationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.project.Model.AddBus;
import com.project.Model.Booking;
import com.project.Model.Ticket;
import com.project.Model.User;
import com.project.repositry.comRepo;
import com.project.repositry.comRepoAddinfo;
import com.project.repositry.comRepoBook;
import com.project.repositry.comRepoTicket;
import com.project.Util.DateTimeUtil;

@Controller
public class MovieController {


  
	@Autowired
    private comRepo userRepository;
	
	@Autowired
	private comRepoAddinfo  addBusRepository;
	@Autowired
	private comRepoBook bookticket;
	
	@Autowired
	private comRepoTicket Ticketrepo;
	

	
	@RequestMapping("/")
	public String Landing()
	{
		return "Home.jsp";
	}
	// singup registraction
	   @RequestMapping("/UserLogin")
	    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
	        System.out.println("Received User: " + user);
	        if (userRepository.findByEmail(user.getEmail()) != null) {
	            redirectAttributes.addFlashAttribute("error", "Email already registered!");
	            return "redirect:/UserLogin";
	        }
	        userRepository.save(user);
	        return "RegisterSuccess.jsp"; 
	    }
	   
	   @PostMapping("/Login")
	    public String login(@RequestParam String email, @RequestParam String password, Model model, RedirectAttributes redirectAttributes) {
	        User user = userRepository.findByEmail(email);

	        if (user == null || !user.getPassword().equals(password)) {
	            redirectAttributes.addFlashAttribute("error", "Invalid password or Email not registered. Please try again.");
	            return "redirect:/UserLogin"; // Redirect to login page with error message
	        } else {
	            model.addAttribute("user", user);
	            int id = user.getId();
	            return "redirect:/success?id=" + id;
	        }
	    }

	   @RequestMapping("/success")
	    public String loginsuccess(@RequestParam("id") int id, Model model) {
	        User usr = userRepository.findById(id).orElse(null);
	        model.addAttribute("userid", id);
	        if (usr != null) {
	            model.addAttribute("user", usr);
	            return "redirect:/Availablebus?userid="+id;
	        } else {
	            return "redirect:/UserLogin"; // Redirect if user not found
	        }
	    }	
	
	   @RequestMapping("/AdminLogin")
	    public String adminLogin(@RequestParam String username, @RequestParam String password ,Model model) 
	   {
		   List<Ticket> ticketList=Ticketrepo.findAll();

		   model.addAttribute("ticketList",ticketList);
		   
		   System.out.println(ticketList);


	        if ("Admin".equals(username) && "12345".equals(password)) {
	            return "AdminSuccess.jsp";
	        } else {
	            return "AdminLogin.jsp";
	        }
	    }
	
	 @GetMapping("/showDateTime")
	    public String showDateTime(@RequestParam String dateString, @RequestParam String timeString) {
	        LocalDate date = DateTimeUtil.parseDate(dateString);
	        LocalTime time = DateTimeUtil.parseTime(timeString);

	        System.out.println("Parsed Date: " + date);
	        System.out.println("Parsed Time: " + time);

	        // Pass the date and time to the view or process them further
	        return "DateTimeView"; // Make sure "DateTimeView" is a valid view name in your project
	    }
	//he page sathi mi add kealy
	 @RequestMapping("/Addinfo")
	 public String addinfo() {
		 return "Addinfo.jsp";
	 }
	//form var add bus clike kel ki he work karnar
	@RequestMapping("/insertInfo")
	public String addinfo1(@ModelAttribute AddBus addbus, RedirectAttributes redirectAttributes) {
	    System.out.println("Received AddBus info: " + addbus);

	    addBusRepository.save(addbus);

	    redirectAttributes.addFlashAttribute("message", "Bus information added successfully!");

	    return "Addinfo.jsp";
	}
	
	//date conversion sathi add kela aahe ha code me and util package pan create kelay 
	  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	    @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.addCustomFormatter(new Formatter<LocalDate>() {
	            @Override
	            public LocalDate parse(String text, Locale locale) {
	                return LocalDate.parse(text, DATE_FORMATTER);
	            }

	            @Override
	            public String print(LocalDate object, Locale locale) {
	                return DATE_FORMATTER.format(object);
	            }
	        });

	        binder.addCustomFormatter(new Formatter<LocalTime>() {
	            @Override
	            public LocalTime parse(String text, Locale locale) {
	                return LocalTime.parse(text, TIME_FORMATTER);
	            }

	            @Override
	            public String print(LocalTime object, Locale locale) {
	                return TIME_FORMATTER.format(object);
	            }
	        });
	    }
	    
	    
	    @RequestMapping("/busreport")
	    public String BusReport(Model m) 
	    {
	        List<AddBus> al = addBusRepository.findAll();
	        System.out.println("Fetched buses: " + al); 
	        m.addAttribute("data", al);
	        return "BusReport.jsp";
	    }
	    @RequestMapping("/Delete/{id}")	
	    public String Delete(@PathVariable int id)
	    {
	    	addBusRepository.deleteById(id);
	    	return "redirect:/busreport";
	    }
	    @RequestMapping("{id}")
	    public String edit(@PathVariable int id,Model m)
	    {
	    	AddBus ab=addBusRepository.findById(id).orElse(null);
	    	m.addAttribute("sdata", ab);
	    	
	    	return "Edit.jsp";
	    }
	    @RequestMapping("/update")
	    public String update(@RequestParam int id,@ModelAttribute AddBus ad)
	    {
	    	AddBus ab=addBusRepository.findById(id).orElse(null);
	    	
	    	if(ab!=null)
	    	{
	    		ab.setBusNumber(ad.getBusNumber());
	    		ab.setBusName(ad.getBusName());
	    		ab.setFromCity(ad.getFromCity());
	    		ab.setToCity(ad.getToCity());
	    		ab.setDate(ad.getDate());
	    		ab.setTime(ad.getTime());
	    		ab.setDuration(ad.getDuration());
	    		ab.setPrice(ad.getPrice());
	    		ab.setDescription(ad.getDescription());
	    		
	    		addBusRepository.save(ab);
	    	}
    		return "redirect:/busreport";
	    }
	    
	    
	    @RequestMapping("/Availablebus")
	    public String Availablebus(@RequestParam("userid") int id ) 
	    {
	    	System.out.println("Available bus"+id);
	       return "redirect:/byid?id="+id;
	    }
	    @RequestMapping("/byid")
	    public String  Availablebusgetid(Model m,@RequestParam("id") int id)
	    {
	    	 List<AddBus> al = addBusRepository.findAll();
		        System.out.println("Fetched buses: " + al); 
		        m.addAttribute("Bdata", al);
		        m.addAttribute("userid", id);
		        return "Avilablebus.jsp";
	    }
	    
	    @RequestMapping("/BookTicketBar")
	    public String bookTic(@RequestParam("userid")int id,@RequestParam ("busid") int busid)
	    {
	    	System.out.println("BookTicket"+id);
	    	return "redirect:/Bookticketbyid?userid="+id+"&busid="+busid;
	    }
	  
	    @RequestMapping("/Bookticketbyid")
	    public String BookTicket(@RequestParam("userid") int userid, @RequestParam("busid") int busid, Model model) {
	    	System.out.println("userid"+userid);
	        User usr = userRepository.getById(userid);
	        AddBus bs = addBusRepository.getById(busid);
	        model.addAttribute("user", usr);
	        model.addAttribute("addbus", bs);
	        return "Bookticket.jsp";
	    }
	    
	 
	    @RequestMapping("/Confirm")
	    public String Confirm(@RequestParam("userid") int userid, @RequestParam("busid") int busid, @RequestParam("noofperson") int persons) {
	        return "redirect:/ConfirmTicket?userid=" + userid + "&busid=" + busid + "&noofperson=" + persons;
	    }
	    
	    
	    @RequestMapping("/ConfirmTicket")
	    public String confirmTicket(@RequestParam ("userid") int userid,@RequestParam ("busid")int busid ,Model model,@RequestParam("noofperson")int persons)
	    {
	    	User usr=userRepository.getById(userid);
	    	AddBus bs=addBusRepository.getById(busid);
	    	model.addAttribute("user", usr);
	    	model.addAttribute("addbus", bs); 
	    	System.out.println(persons);
	    	
	    	int price=(int)(bs.getPrice()*persons);
	    	model.addAttribute("price", price);
	    	int person=(int) (price/bs.getPrice());
	    	model.addAttribute("persons",person);
	    	return "ConfirmDetails.jsp";
	    }
	    
	    
	    
	    @RequestMapping(value = "/paymentSuccess", method = RequestMethod.POST)
	    public String paymentSuccess(@RequestParam("userid") int userid,
	                                 @RequestParam("busid") int busid,
	                                 @RequestParam("noofperson") int noofperson, 
	                                 @RequestParam("totalPrice") int totalPrice,
	                                 Model model) {
	        User usr = userRepository.getById(userid);
	        AddBus bs = addBusRepository.getById(busid);
	        
	        Ticket tc=new Ticket();
	    	tc.setBusname(bs.getBusName());
	    	tc.setBusnumber(bs.getBusNumber());
	    	tc.setDate(bs.getDate());
	    	tc.setEmail(usr.getEmail());
	    	tc.setFromcity(bs.getFromCity());
	    	tc.setTocity(bs.getToCity());
	    	tc.setName(usr.getName());
	    	tc.setNoofperson(noofperson);
	    	tc.setPrice(totalPrice);
	    	tc.setTime(bs.getTime());
	    	
	    	Ticketrepo.save(tc);
	        model.addAttribute("user", usr);
	        model.addAttribute("addbus", bs);
	        model.addAttribute("persons", noofperson);
	        model.addAttribute("price", totalPrice);

	        return "Ticket.jsp";
	        
	        
	    }
	    
	    @RequestMapping("/showticket")
	    public String ShowTicket(Model model)
	    {
	    	 List<Ticket> ticketList=Ticketrepo.findAll();

			 model.addAttribute("ticketList",ticketList);
			 System.out.println(ticketList);

			 return"ShowTickets.jsp";

	    }
	 
	    
}